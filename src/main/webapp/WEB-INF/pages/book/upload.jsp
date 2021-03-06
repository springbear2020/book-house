<%--
  Created by IntelliJ IDEA.
  User: SpringBear
  Date: 2022/3/22
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>图书上传</title>
    <%@include file="/WEB-INF/pages/common/base.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" type="text/css" href="static/css/upload.css">
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
    <script type="text/javascript" src="static/script/tools.js"></script>
    <script type="text/javascript" src="static/script/upload.js"></script>

    <%-- 提示信息 --%>
    <%@include file="/WEB-INF/pages/common/notice.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/pages/common/header.jsp" %>
<div class="middle">
    <div class="middle-container">
        <h2 class="up-title">图书上传</h2>
        <%-- 用户上传图书表单 --%>
        <form action="transfer?action=uploadBook" method="post" enctype="multipart/form-data">
            <input type="hidden" name="userId" value="${sessionScope.user.id}">
            <%-- 选择图书 --%>
            <div class="bookFile upload-label"><label class="bookFile-true"><span>选择图书</span>
                <input type="file" name="book" accept="application/pdf" class="chooseFile" style="display:block;opacity: 0"></label>
                <div class="fileInf">请选择 pdf 图书文件</div>
            </div>
            <%-- 选择封面 --%>
            <div class="bookCover upload-label">
                <div class="bookCover-left">
                    <label class="chooseImg-true">
                        <div>选择封面</div><input type="file" name="cover" accept="image/jpeg,image/png" class="chooseImg" style="display:block;opacity: 0">
                    </label>
                </div>
                <div class="img-border">
                    <div>图 片 封&nbsp;面</div>
                    <img class="bookCover-img" alt="" src="">
                </div>
            </div>
            <label><input type="submit" value="立即上传" class="upload-btn" id="userUploadBook"></label>
        </form>
    </div>
</div>
</body>
<%@include file="/WEB-INF/pages/common/footer.jsp" %>
</html>