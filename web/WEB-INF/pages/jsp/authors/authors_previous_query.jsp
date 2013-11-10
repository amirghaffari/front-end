<%-- 
    Document   : authors_previous_query
    Created on : Jul 20, 2011, 3:46:11 AM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/authors.jsp" %>
<c:if test="${login.lastLoginStatus!='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/login.jsp" %>
</c:if>

<c:if test="${login.lastLoginStatus=='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/logout.jsp" %>
   
    <div class="item">
        <form:form method="POST">
            <fieldset style="width: 60%; background: #e6f1f5; padding-top:20px;">
                <legend><b><spring:message code="authors_submission_wizard_last_legend"/> </b></legend>
                <br/>
                <table class="grideTable" style=" width: 95%;" >
                        <tr>
                            <th><spring:message code="authors_submission_wizard_last_table_title"/></th>
                            <th><spring:message code="authors_submission_wizard_last_table_publication_type"/></th>
                            <th><spring:message code="authors_submission_wizard_last_table_lastState"/></th>
                        </tr>

                        <c:forEach var="publication" items="${publications}">
                            <tr >
                                <td>${publication.title}</td>
                                <td>${publication.publicationType}</td>
                                <td>
                                    <select disabled="disabled">
                                        <option value="1" <c:if test="${publication.reviewStatus==1}"> selected</c:if> >Public</option>
                                        <option value="2" <c:if test="${publication.reviewStatus==2}"> selected</c:if> >Reviewed</option>
                                        <option value="3" <c:if test="${publication.reviewStatus==3}"> selected</c:if> >Rejected</option>
                                        <option value="4" <c:if test="${publication.reviewStatus==4}"> selected</c:if> >In completed</option>
                                        <option value="5" <c:if test="${publication.reviewStatus==5}"> selected</c:if> >Being Reviewed</option>
                                    </select>
                                </td>
                            </tr> 
                        </c:forEach>
                    </table>
                </fieldset>
        </form:form>
    </div>
</c:if>
