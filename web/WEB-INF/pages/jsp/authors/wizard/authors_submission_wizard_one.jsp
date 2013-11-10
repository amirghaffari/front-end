<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<c:if test="${login.lastLoginStatus!='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/login.jsp" %>
</c:if>

<c:if test="${login.lastLoginStatus=='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/logout.jsp" %>

    <div id="authors_submit_form" class="inputform">
        <p><h2><spring:message code="authors_submission_wizard_one_header"/></h2></p>

        <form:form method="POST" commandName="applicant">
            <fieldset>
            <legend><b><spring:message code="authors_submission_wizard_one_info"/> </b></legend>
                <table>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_title"/></td>
                        <td><form:input path="title" /> <span class="error"> <form:errors path="title" /> </span> </td>
                    </tr>                
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_firstname"/></td>
                        <td><form:input path="firstName" /> <span class="error"> <form:errors path="firstName" /> </span> </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_lastname"/></td>
                        <td><form:input path="lastName" /> <span class="error"> <form:errors path="lastName" /> </span> </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_degree"/></td>
                        <td><form:input path="degree" /> <span class="error"> <form:errors path="degree" /> </span>  </td>
                    </tr>                
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_expert"/></td>
                        <td><form:input path="expert" /> <span class="error"> <form:errors path="expert" /> </span> </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_position"/></td>
                        <td><form:input path="position" /> <span class="error"> <form:errors path="position" /> </span> </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_institution"/></td>
                        <td><form:input path="institution" />  <span class="error"> <form:errors path="institution" /> </span>  </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_address"/></td>
                        <td><form:textarea path="address" rows="3" cols="20" /> <span class="error"> <form:errors path="address" /> </span> </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_phone"/></td>
                        <td><form:input path="phone" />  <span class="error"> <form:errors path="phone" /> </span>  </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_faxNumber"/></td>
                        <td><form:input path="faxNumber" /> <span class="error"> <form:errors path="faxNumber" /> </span>  </td>
                    </tr>
                    <tr>
                        <td><spring:message code="authors_submission_wizard_one_email"/></td>
                        <td><form:input path="email" />  <span class="error"> <form:errors path="email" /> </span>  </td>
                    </tr>
                </table>
            </fieldset>
            <div class="button_panel">
                <input id="cancelbtn" 
                       onclick="return setSelectedActionByConfirm('cancelbtn'            
                        ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                        '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','applicant');" 
                        onselect="return setSelectedActionByConfirm('cancelbtn'
                        ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                        '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','applicant');" 
                        class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>cancel.png" value="cancel" alt="<spring:message code="cancel.caption"/>" title="<spring:message code="cancel.caption"/>" />
                        <input id="nextbtn"  onclick="setSelectedAction('nextbtn')" onselect="setSelectedAction('nextbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="next.picture"/>.png" value="next" alt="<spring:message code="next.caption"/>" title="<spring:message code="next.caption"/>" />
                        <input id="selected_action" type="hidden" name="selected_action" value="" />
            </div>  
        </form:form>
    </div>
</c:if>