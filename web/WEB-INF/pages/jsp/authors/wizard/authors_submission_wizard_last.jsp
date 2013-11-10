<%-- 
    Document   : authors_submission_wizard_last
    Created on : Jul 12, 2011, 11:05:29 PM
    Author     : amir
--%>
<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<p class="item"><h2><spring:message code="authors_submission_wizard_last_header"/> ${authorSubmissionWizard.publication.title}</h2></p>

<form:form method="POST">
    <fieldset style="width: 70%; background: #E5E1D8;">
        <legend><b><spring:message code="authors_submission_wizard_last_legend"/> </b></legend>
        <table class="grideTable" style=" width: 90%;" >
                <tr>
                    <th><spring:message code="authors_submission_wizard_last_table_title"/></th>
                    <th><spring:message code="authors_submission_wizard_last_table_publication_type"/></th>
                    <th><spring:message code="authors_submission_wizard_last_table_lastState"/></th>
                    <th>Applicant</th>
                </tr>
                <tr >
                    <td>${submission.publication.title}</td>
                    <td>${submission.publication.publicationType}</td>
                    <td>
                        <select disabled="disabled">
                            <option value="1" <c:if test="${submission.publication.reviewStatus==1}"> selected</c:if> >Public</option>
                            <option value="2" <c:if test="${submission.publication.reviewStatus==2}"> selected</c:if> >Reviewed</option>
                            <option value="3" <c:if test="${submission.publication.reviewStatus==3}"> selected</c:if> >Rejected</option>
                            <option value="4" <c:if test="${submission.publication.reviewStatus==4}"> selected</c:if> >In completed</option>
                            <option value="5" <c:if test="${submission.publication.reviewStatus==5}"> selected</c:if> >Being Reviewed</option>
                        </select>
                    </td>
                    <td>${submission.applicant.firstName} &nbsp; ${submission.applicant.lastName}</td>
                </tr> 
            </table>
        </fieldset>
</form:form>

<p class="item"><a href="${pageContext.request.contextPath}/authors"> <spring:message code="authors_submission_wizard_last_link"/></a> </p>
