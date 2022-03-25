<%--
  Created by IntelliJ IDEA.
  User: SpringBear
  Date: 2022/3/23
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>个人信息</title>
    <%@include file="/pages/common/base.jsp" %>
    <script type="text/javascript" src="static/script/tools.js"></script>
    <script type="text/javascript" src="static/script/personal.js"></script>
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
    <link rel="stylesheet" type="text/css" href="static/css/personal.css">
</head>
<body>
<%@include file="/pages/common/header.jsp" %>
<div class="middle">
    <div class="middle-container">
        <div class="head-frame">
            <div class="user-head">
<%--                <img src="static/img/Cirno.jpg" alt="头像加载失败">--%>
<%--                <label class="change-head">--%>
<%--                    <div>更换头像</div>--%>
<%--                    <input type="file" name="head_img" accept="image/jpeg,image/png" class="chooseImg"--%>
<%--                           style="opacity: 0">--%>
<%--                </label>--%>

                <label class="change-hd">
                    <img src="static/img/Cirno.jpg" alt="头像加载失败">
                    <div>更换头像</div>
                    <input type="file" name="head_img" accept="image/jpeg,image/png" class="chooseImg"
                           style="opacity: 0">
                </label>
            </div>
            <div class="user-data-frame">
                <div class="user-data-top">
                    <div class="user-data-top-username">古明地恋</div>
                    <p class="user-data-top-time">2022-05-14</p>
                    <p>加入&nbsp;Book&nbsp;House</p>
                </div>
                <div class="user-data-center">
                    <ul>
                        <li><div class="user-data-num">514</div><div class="user-data-name">积分</div><div class="sp"></div></li>
                        <li><div class="user-data-num">514</div><div class="user-data-name">下载</div><div class="sp"></div></li>
                        <li><div class="user-data-num">514</div><div class="user-data-name">上传</div><div class="sp"></div></li>
                        <li><div class="user-data-num">514</div><div class="user-data-name">收藏</div><div class="sp"></div></li>
                    </ul>
                </div>
                <div class="user-data-bottom">
                    <div class="user-Signature">个性签名</div>
                    <p class="user-Signature-data">地底蔷薇于天空绽放。我要把你的尸体挂在地灵殿门口。听得到吗？我在你身后哦。</p>
<%--                    <label><input class="user-Signature-data" value="地底蔷薇于天空绽放。我要把你的尸体挂在地灵殿门口。听得到吗？我在你身后哦。"></label>--%>
                </div>
            </div>
        </div>
        <div class="per-frame">
            <h1 class="per-title">个人信息</h1>
            <div class="per-change-check">修改资料</div>
            <div class="per-sp"></div>
            <form>
                <ul class="per-container">
                    <li><div class="per-data-name">用户昵称</div><label><input name="nickName" disabled class="per-data-text" type="text" value="古明地恋"></label></li>
                    <li>
                        <div class="per-data-name">性别</div>
                        <label>
                            <input type="radio" name="Sex" disabled checked="checked" value="male" class="per-data-sex sex-b">
                            <div class="per-data-sex">男</div>
                            <input type="radio" name="Sex" disabled value="female" class="per-data-sex sex-b">
                            <div class="per-data-sex">女</div>
                        </label>
                    </li>
                    <li><div class="per-data-name">出生日期</div><label><input name="birth" disabled class="per-data-text" type="date" value="2022-05-14"></label></li>
                    <li><div class="per-data-name">所在地区</div><label><input name="area" disabled class="per-data-text" type="text" value="地灵殿"></label></li>
                    <li id="per-data-sign"><div class="per-data-name">个性签名</div>
<%--                        如果只有一行可以用input 如果是多行就得用textarea--%>
                        <label><textarea  name="signature" disabled class="per-data-text per-data-Signature" cols="40" rows="3"
                                          placeholder="地底蔷薇于天空绽放。我要把你的尸体挂在地灵殿门口。听得到吗？我在你身后哦。"
                                          style="resize:none"></textarea></label>
                    </li>
                    <button type="submit" class="change-sure">保&nbsp;存</button>
                    <div class="change-cancel">取&nbsp;消</div>
                </ul>
            </form>
        </div>
        <div class="data-frame">
            <h1 class="per-title">账号信息</h1>
            <div class="per-sp"></div>
            <form>
                <ul class="per-container">
                    <li><div class="us-data-name">用户名</div><label><input name="username" class="us-data-text" type="text" disabled value="古明地 こいし"></label></li>
                    <li><div class="us-data-name">密码</div><label><input name="password" class="us-data-text data-pwd" type="password" disabled value="这个真的能看吗"></label>
                        <img class="pas-eye" src="static/img/eye.png" alt="图片加载失败"></li>
                    <li><div class="us-data-name">邮箱</div><label><input name="email" class="us-data-text" type="text" disabled value="koishi@touhou.com"></label></li>
                    <li class="log-record-li"><a href="#" class="log-record log-btn">登录记录</a></li>
                    <li class="log-out-li"><a href="#" class="log-out log-btn">账号注销</a><div>不准</div></li>
                </ul>
            </form>
        </div>
    </div>
</div>
<%--<img src="${requestScope.user.portraitPath}" alt="">--%>
<%--<form>--%>
<%--    用户名：<input type="text" value="${sessionScope.user.username}"><hr/>--%>
<%--    密  码：<input type="password" value="${sessionScope.user.password}"><hr/>--%>
<%--    邮  箱：<input type="text" value="${sessionScope.user.email}"><hr/>--%>
<%--    个人积分：<input type="text" disabled="disabled" value="${sessionScope.user.score}"><hr/>--%>
<%--    注册时间：<input type="text" disabled="disabled" value="${sessionScope.user.registerDate}"><hr/>--%>
<%--</form>--%>
<%@include file="/pages/common/footer.jsp" %>
</body>
</html>
