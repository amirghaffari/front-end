<%-- 
    Document   : authors_profiles
    Created on : Jul 20, 2011, 12:18:46 AM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<p class="header" ><spring:message code="authors_profiles_header"/></p>
<p>
    <table class="grideTable">
        <tr>
            <th><spring:message code="authors_profiles_name"/></th>
            <th><spring:message code="authors_profiles_affiliation"/></th>
            <th><spring:message code="authors_profiles_degree"/></th>
        </tr>
        <c:forEach var="authorItem" items="${authorList}">
            <tr >
                <td>${authorItem.name}</td>
                <td>${authorItem.affiliation}</td><td>${authorItem.degree}</td>
            </tr> 
        </c:forEach>
    </table>
</p>
