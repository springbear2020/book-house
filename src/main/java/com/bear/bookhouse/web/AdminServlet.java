package com.bear.bookhouse.web;

import com.bear.bookhouse.pojo.*;
import com.bear.bookhouse.service.BookService;
import com.bear.bookhouse.service.RecordService;
import com.bear.bookhouse.service.UserService;
import com.bear.bookhouse.service.impl.BookServiceImpl;
import com.bear.bookhouse.service.impl.RecordServiceImpl;
import com.bear.bookhouse.service.impl.UserServiceImpl;
import com.bear.bookhouse.util.NumberUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/26 23:50
 */
public class AdminServlet extends BaseServlet {
    private final UserService userService = new UserServiceImpl();
    private final RecordService recordService = new RecordServiceImpl();
    private final BookService bookService = new BookServiceImpl();

    /**
     * 管理员登录
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void adminLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession();
        // 管理员已登录直接转发到管理员界面
        if (session.getAttribute("admin") != null) {
            req.getRequestDispatcher("picture?action=showBackground").forward(req, resp);
            return;
        }
        // 验证管理员账户密码
        Admin admin = userService.getAdminByUsernameAndPassword(req.getParameter("username"), req.getParameter("password"));
        if (admin != null) {
            session.setAttribute("admin", admin);
            req.getRequestDispatcher("picture?action=showBackground").forward(req, resp);
        } else {
            session.setAttribute("noticeMsg", "The administrator does not exist or the password is wrong");
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 管理员注销登录
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void adminLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        req.getSession().removeAttribute("admin");
        req.getRequestDispatcher("/picture?action=showPixabay").forward(req, resp);
    }

    /**
     * 处理一条用户的图书上传记录
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void processUpload(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        int userId = NumberUtil.objectToInteger(req.getParameter("userId"), User.ERROR);
        int uploadId = NumberUtil.objectToInteger(req.getParameter("uploadId"), Upload.ERROR);
        String bookPath = req.getParameter("bookPath");
        String coverPath = req.getParameter("coverPath");
        Book book = (Book) req.getAttribute("bookByAdmin");
        HttpSession session = req.getSession();
        // 保存图书记录到数据库、删除磁盘用户上传的文件、增加用户积分、修改上传记录状态为已处理
        File bookFile = new File(getServletContext().getRealPath("/" + bookPath));
        File coverFile = new File(getServletContext().getRealPath("/" + coverPath));
        // 删除磁盘文件
        if (bookFile.exists() && coverFile.exists() && bookFile.delete() && coverFile.delete()) {
            // 保存图书、修改上传记录状态为已处理、增加用户积分
            if (bookService.saveBook(book) && recordService.updateUploadState(uploadId) && userService.addUserScore(User.SCORE_CHANGE, userId)) {
                session.setAttribute("noticeMsg", "Save book, delete files, update state, issue points successfully");
            }
        } else {
            session.setAttribute("noticeMsg", "Delete the book or the cover file failed");
        }
        req.getRequestDispatcher("/WEB-INF/pages/admin/book.jsp").forward(req, resp);
    }
}