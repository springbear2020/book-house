<%--
  Created by IntelliJ IDEA.
  User: SpringBear
  Date: 2022/3/23
  Time: 18:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>个人记录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="/pages/common/base.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
    <link rel="stylesheet" type="text/css" href="static/css/record.css">
    <script type="text/javascript" src="static/script/tools.js"></script>
    <script type="text/javascript" src="static/script/record.js"></script>
</head>
<body>
<%@include file="/pages/common/header.jsp" %>
<div class="middle">
    <div class="middle-container">
        <ul class="table-choose">
            <a href="recordServlet?action=showRecord&type=upload&userId=${sessionScope.user.id}"
               class="choose-upload choose-li">上传</a>
            <a href="recordServlet?action=showRecord&type=download&userId=${sessionScope.user.id}"
               class="choose-download choose-li">下载</a>
            <a href="recordServlet?action=showLoginLog&userId=${sessionScope.user.id}"
               class="choose-log choose-li">登录</a>
        </ul>
        <%-- 上传和下载记录 --%>
        <c:if test="${not empty requestScope.recordPage.pageData}">
            <table class="pure-table">
                <thead>
                <tr>
                    <th>类型</th>
                    <th>积分</th>
                    <th>时间</th>
                    <th>书名</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.recordPage.pageData}" var="record">
                    <tr class="pure-table-odd">
                        <td>${record.operation}</td>
                        <td>${record.scoreChange}</td>
                        <td>${record.time}</td>
                        <td class="tb-title">${record.title}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%-- 分页条 --%>
            <nav aria-label="Page navigation" class="pag-chs">
                <ul class="pagination">
                        <%-- 非第一页显示上一页 --%>
                    <c:if test="${requestScope.recordPage.pageNum!=1}">
                        <li>
                            <a href="recordServlet?action=showRecord&type=upload&userId=${sessionScope.user.id}&pageNum=${requestScope.recordPage.pageNum - 1}"
                               aria-label="Previous" class="pag-num"><span aria-hidden="true">&laquo;</span></a></li>
                    </c:if>
                    <li><span aria-hidden="true" class="pag-this">${requestScope.recordPage.pageNum}</span></li>
                        <%-- 非最后一页显示下一页 --%>
                    <c:if test="${requestScope.recordPage.pageNum!=requestScope.recordPage.pageTotal}">
                        <li>
                            <a href="recordServlet?action=showRecord&type=upload&userId=${sessionScope.user.id}&pageNum=${requestScope.recordPage.pageNum + 1}"
                               aria-label="Next" class="pag-num"><span aria-hidden="true">&raquo;</span></a></li>
                    </c:if>
                    <div style="clear:both"></div>
                </ul>
            </nav>
        </c:if>

        <%-- 登录记录 --%>
        <c:if test="${not empty requestScope.loginLogPage.pageData}">
            <table class="pure-table">
                <thead>
                <tr>
                    <th>用户名</th>
                    <th>IP</th>
                    <th>时间</th>
                    <th>地点</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.loginLogPage.pageData}" var="login">
                    <tr class="pure-table-odd">
                        <td>${login.username}</td>
                        <td>${login.ip}</td>
                        <td>${login.time}</td>
                        <td class="tb-title">${login.location}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
            <%-- 分页条 --%>
            <nav aria-label="Page navigation" class="pag-chs">
                <ul class="pagination">
                        <%-- 非第一页显示上一页 --%>
                    <c:if test="${requestScope.loginLogPage.pageNum!=1}">
                        <li>
                            <a href="recordServlet?action=showLoginLog&userId=${sessionScope.user.id}&pageNum=${requestScope.loginLogPage.pageNum - 1}"
                               aria-label="Previous" class="pag-num"><span aria-hidden="true">&laquo;</span></a></li>
                    </c:if>
                    <li><span aria-hidden="true">${requestScope.loginLogPage.pageNum}</span></li>
                        <%-- 非最后一页显示下一页 --%>
                    <c:if test="${requestScope.loginLogPage.pageNum!=requestScope.loginLogPage.pageTotal}">
                        <li>
                            <a href="recordServlet?action=showLoginLog&userId=${sessionScope.user.id}&pageNum=${requestScope.loginLogPage.pageNum + 1}"
                               aria-label="Next" class="pag-num"><span aria-hidden="true">&raquo;</span></a></li>
                    </c:if>
                    <div style="clear:both"></div>
                </ul>
            </nav>
        </c:if>
    </div>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
