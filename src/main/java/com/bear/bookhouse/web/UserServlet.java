package com.bear.bookhouse.web;

import com.bear.bookhouse.pojo.LoginLog;
import com.bear.bookhouse.pojo.User;
import com.bear.bookhouse.pojo.UserInfo;
import com.bear.bookhouse.service.RecordService;
import com.bear.bookhouse.service.UserService;
import com.bear.bookhouse.service.impl.RecordServiceImpl;
import com.bear.bookhouse.service.impl.UserServiceImpl;
import com.bear.bookhouse.util.*;
import com.google.code.kaptcha.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author Spring-_-Bear
 * @datetime 2022/3/14 17:25
 */
public class UserServlet extends BaseServlet {
    /**
     * 用户注册时邮箱验证码
     */
    private static String registerEmailCode;
    /**
     * 用户找回密码时邮箱验证码
     */
    private static String passwordFindEmailCode;

    private final UserService userService = new UserServiceImpl();
    private final RecordService recordService = new RecordServiceImpl();

    /**
     * 用户注册
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 将注册表单中的各参数封装成 User
        User user = WebUtil.copyParamsToBean(new User(), req.getParameterMap());
        // 获取客户端输入的图片验证码
        String imgVerifyCode = req.getParameter("imgVerifyCode");
        // 获取由系统生成的图片验证码
        Object imgVerifyCodeByGoogle = req.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        // 获取客户端输入的邮箱验证码
        String emailVerifyCode = req.getParameter("emailVerifyCode");

        if (!emailVerifyCode.equalsIgnoreCase(UserServlet.registerEmailCode)) {
            req.setAttribute("user", user);
            req.setAttribute("noticeMsg", "邮箱验证码有误");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            return;
        }
        if (!imgVerifyCodeByGoogle.equals(imgVerifyCode)) {
            req.setAttribute("user", user);
            req.setAttribute("emailCode", emailVerifyCode);
            req.setAttribute("noticeMsg", "图片验证码有误");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
            return;
        }

        // 用户注册默认积分 100，保存用户信息到数据库
        user.setScore(User.REGISTER_SCORE);
        user.setRegisterDate(new Date());
        if (userService.saveUser(user)) {
            req.setAttribute("noticeMsg", "恭喜您注册成功！");
            req.getRequestDispatcher("/pages/user/register.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/pages/error/500.jsp");
        }
    }


    /**
     * 用户登录
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String usernameOrEmail = req.getParameter("usernameOrEmail");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user;
        if (userService.isUsernameAndPasswordCorrect(usernameOrEmail, password) || userService.isEmailAndPasswordCorrect(usernameOrEmail, password)) {
            // 通过用户名或邮箱查询用户信息
            if ((user = userService.getUserByEmail(usernameOrEmail)) == null) {
                user = userService.getUserByUsername(usernameOrEmail);
            }
            // 设置用户登录日志信息
            session.setAttribute("user", user);
            String ip = WebUtil.getIpAddress(req);
            String location = WebUtil.parseIp(ip) == null ? LoginLog.IP_ERROR : WebUtil.parseIp(ip);
            // 保存用户登录日志
            if (recordService.saveLoginLog(new LoginLog(null, user.getId(), user.getUsername(), ip, location, new Date()))) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                resp.sendRedirect(req.getContextPath() + "/pages/error/500.jsp");
            }
        } else {
            session.setAttribute("loginMsg", "用户名不存在或密码错误");
            resp.sendRedirect(req.getContextPath() + "/pages/user/login.jsp");
        }
    }

    /**
     * 注销登录
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().removeAttribute("user");
        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }

    /**
     * 修改用户密码
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void updatePassword(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String emailVerifyCode = req.getParameter("emailVerifyCode");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();

        // 用户输入的邮箱验证码错误，返回修改界面
        if (!emailVerifyCode.equalsIgnoreCase(UserServlet.passwordFindEmailCode)) {
            session.setAttribute("noticeMsg", "邮箱验证码错误");
            req.getRequestDispatcher("/pages/user/pwdFind.jsp").forward(req, resp);
            return;
        }
        if (userService.updatePasswordByEmail(password, email)) {
            session.setAttribute("noticeMsg", "密码重置成功!");
            req.getRequestDispatcher("/pages/user/pwdFind.jsp").forward(req, resp);
        } else {
            resp.sendRedirect(req.getContextPath() + "/pages/error/500.jsp");
        }
    }


    /**
     * 保存用户个人信息
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void saveUserInfo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        // TODO 使用 beanutils ？
        String userId = req.getParameter("userId");
        String nickname = req.getParameter("nickname");
        String sex = req.getParameter("sex");
        String birth = req.getParameter("birth");
        String location = req.getParameter("location");
        String signature = req.getParameter("signature");
        UserInfo userInfo = new UserInfo(null, NumberUtil.objectToInteger(userId, User.ERROR), nickname, sex, DateUtil.stringFormatDate(birth), location, signature, new Date());
        if (userService.updateUserInfo(userInfo)) {
            session.setAttribute("noticeMsg", "个人信息修改成功");
        } else {
            session.setAttribute("noticeMsg", "个人信息修改失败，请稍后重试");
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 显示用户个人信息
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void showPersonal(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        HttpSession session = req.getSession();
        // 从域中获取的用户 id 确保了 id 不会非法
        int userId = NumberUtil.objectToInteger(user.getId(), User.ERROR);
        session.setAttribute("userInfo", userService.getUserInfoByUserId(userId));
        req.getRequestDispatcher("/pages/user/personal.jsp").forward(req, resp);
    }

    /**
     * AJAX 请求验证用户名是否已使用
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void ajaxVerifyUsername(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String username = req.getParameter("username");
        if (userService.isUsernameExists(username)) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    /**
     * AJAX 请求验证邮箱地址是否已使用
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void ajaxVerifyEmail(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        if (userService.isEmailExists(email)) {
            resp.getWriter().write("true");
        } else {
            resp.getWriter().write("false");
        }
    }

    /**
     * AJAX 注册请求发送随机验证码到指定邮箱
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void ajaxSendRegisterCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        EmailUtil instance = EmailUtil.getInstance();
        try {
            instance.sendEmail(email);
            UserServlet.registerEmailCode = instance.getVerifyCode();
            resp.getWriter().write("true");
        } catch (Exception e) {
            resp.getWriter().write("false");
            e.printStackTrace();
        }
    }

    /**
     * AJAX 找回密码请求发送随机验证码到指定邮箱
     *
     * @param req  HttpServletRequest
     * @param resp HttpServletResponse
     */
    protected void ajaxSendPwdFindCode(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        EmailUtil instance = EmailUtil.getInstance();
        try {
            instance.sendEmail(email);
            UserServlet.passwordFindEmailCode = instance.getVerifyCode();
            resp.getWriter().write("true");
        } catch (Exception e) {
            resp.getWriter().write("false");
            e.printStackTrace();
        }
    }

}
