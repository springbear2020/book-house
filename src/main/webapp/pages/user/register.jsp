<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="zh_CN">
<head>
    <meta charset="UTF-8">
    <title>用户注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <%@include file="/pages/common/base.jsp" %>
    <link rel="stylesheet" type="text/css" href="static/css/register.css">
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
    <script type="text/javascript" src="static/script/tools.js"></script>
    <script type="text/javascript" src="static/script/register.js"></script>
</head>
<body>
<%@include file="/pages/common/header.jsp" %>
<c:if test="${ not empty requestScope.registerErrorMsg}">
    <div class="alert">${requestScope.registerErrorMsg}</div>
    <div class="grey"></div>
</c:if>
<div class="middle">
        <div class="frame-register">
            <div class="register-title">欢&nbsp;迎&nbsp;注&nbsp;册</div>
            <%--  注册表单  --%>
            <form class="register-box" action="userServlet" method="post">
                <input type="hidden" name="action" value="register">
                <div class="register-input"><label>
                    <input type="text" name="username" placeholder="用户名" class="register-username register-text"
                           value="${ empty requestScope.user.username? "" : requestScope.user.username}">
                    <div class="little-tips tips-username">* 由字母、数字、下划线组成且以字母开头，长度为 1~16</div>
                </label></div>
                <div class="register-input"><label>
                    <input type="password" name="password" placeholder="密码"
                           class="register-password register-text">
                    <img class="pas-eye" src="static/img/eye.png" alt="图片加载失败">
                    <div class="little-tips tips-password">* 至少一个数字、字母、字符（@#$%&），长度为 6~16</div>
                </label></div>
                <div class="register-input"><label>
                    <input type="email" name="email" placeholder="邮箱" class="register-email register-text"
                           value="${ empty requestScope.user.email? "" : requestScope.user.email}">
                    <div class="little-tips tips-email">* e.g bookhouse@bookhouse.com</div>
                </label></div>
                <div class="register-input"><label>
                    <input type="text" name="emailVerifyCode" placeholder="邮箱验证码"
                           class="emailVerifyCode verifyCode img-text"
                           value="${empty requestScope.emailCode? "" : requestScope.emailCode}">
                    <input type="button" class="emailCodeBtn" id="emailCodeBtn" value="获取"/>
                    <div class="little-tips tips-verifyCode tips-emailCode">* 点击获取邮件会发送至您的邮箱</div>
                </label>
                </div>
                <div class="register-input"><label>
                    <input type="text" name="imgVerifyCode" placeholder="图片验证码"
                           class="imgVerifyCode verifyCode img-text">
                    <img id="imgCodeBtn" alt="验证码加载失败，点击刷新" src="imgVerifyCode.jpg"/>
                    <div class="little-tips tips-verifyCode tips-imgCode">* 点击图片可刷新验证码</div>
                </label>
                </div>
                <div class="register-button">
                    <button type="submit" class="register-btn">注&nbsp;&nbsp;册</button>
                </div>
            </form>
        </div>
</div>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>