<%-- 
    Document   : logout
    Created on : Aug 23, 2011, 8:45:35 PM
    Author     : amir
--%>

<p class="right" >
    <a class="right tTip" title="<spring:message code="publication_abstract_logout"/> <p> User name = ${login.username} </p>
        <p> User role(s):</p>
        <UL>
        <c:forEach var="role" items="${login.groupList}">
            <LI>${role.name}
        </c:forEach>
        </UL>
        " 
        href="<c:url value='/publication/logout?backAddress=${login.backAddress}'/>"> <img width="30px" height="30px" src="${pageContext.request.contextPath}/<spring:theme code="image"/>lock.png"/> </a>
</p>

