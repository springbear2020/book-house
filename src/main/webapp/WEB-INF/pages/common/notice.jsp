<%--
  Created by IntelliJ IDEA.
  User: accelerater
  Date: 2022/3/26
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<div class="notice">
    ${sessionScope.noticeMsg}
</div>
<c:if test="${not empty sessionScope.noticeMsg}">
    <script type="text/javascript">noticeShow()</script>
    <% session.removeAttribute("noticeMsg"); %>
</c:if>