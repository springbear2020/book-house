<%--
  Created by IntelliJ IDEA.
  User: SpringBear
  Date: 2022/3/23
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>收藏夹</title>
    <%@include file="/WEB-INF/pages/common/base.jsp" %>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" type="text/css" href="static/css/favorite.css">
    <link rel="stylesheet" type="text/css" href="static/css/common.css">
    <script type="text/javascript" src="static/script/tools.js"></script>

    <%-- 提示信息 --%>
    <%@include file="/WEB-INF/pages/common/notice.jsp" %>
</head>
<body>
<%@include file="/WEB-INF/pages/common/header.jsp" %>
<div class="middle">
    <div class="middle-container">
        <div class="fav-frame">
            <div class="fav-title">收藏夹</div>
            <div class="fav-box">
                <c:forEach items="${requestScope.userFavoritesList}" var="favorite">
                    <div class="fav-book">
                        <a title="取消收藏" href="record?action=deleteFavorite&userId=${sessionScope.user.id}&bookId=${favorite.bookId}" class="fav-close"><img class="fav-close-img" src="static/img/icon_no_deep.png" alt=""></a>
                        <div class="fav-data">
                            <a title="查看详情" href="book?action=showBookDetails&bookId=${favorite.bookId}"><img class="fav-img" src="${favorite.coverPath}" alt=""></a>
                            <p class="fav-bk-t">${favorite.title}</p>
                            <p class="fav-author">${favorite.author}</p>
                            <a title="立即下载" class="fav-download" href="transfer?action=downloadBook&bookId=${favorite.bookId}&userId=${sessionScope.user.id}">立即下载</a>
                        </div>
                        <div class="fav-bottom">
                            <span>收藏于：</span><p>${favorite.collectTime}</p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/pages/common/footer.jsp" %>
</body>
</html>
