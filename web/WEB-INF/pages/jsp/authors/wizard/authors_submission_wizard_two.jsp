<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="authors_submit_form" class="inputform">
    <p class="item" ><h2><spring:message code="authors_submission_wizard_two_header"/></h2></p>
    <p class="item" >
        <b><spring:message code="authors_submission_wizard_two_note1"/> </b> 
        <i>
            <c:out value="${authorSubmissionWizard.applicant.firstName}" /> 
            <c:out value="${authorSubmissionWizard.applicant.lastName}" />
        </i>
        <spring:message code="authors_submission_wizard_two_note2"/>
    </p>

    <p class="error item">
         <c:forEach var="errorCode" items="${Error_List}">
             <spring:message code="${errorCode}"/>
         </c:forEach>
    </p>

    <form:form method="POST"  action="${pageContext.request.contextPath}/authors/submission/wizard/two" commandName="author" >

        <form:hidden path="id"/>
        <fieldset>
        <legend><b><spring:message code="authors_submission_wizard_two_legend"/></b></legend>
            <p class="error item">
                <form:errors path="*" cssClass="error" />
            </p>
            <table>
                <tr>
                    <td><spring:message code="authors_submission_wizard_two_fname"/></td>
                    <td><form:input path="firstName" /></td>
                </tr> 
                <tr>
                    <td><spring:message code="authors_submission_wizard_two_lname"/></td>
                    <td><form:input path="lastName" /></td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_two_degree"/></td>
                    <td><form:input path="degree" /></td>
                    <td>
                        <span class="space notice" >
                            <spring:message code="authors_submission_wizard_two_status_title"/>
                        </span> 
                        <spring:message code="authors_submission_wizard_two_status_text_${status}"/> 
                        <c:if test="${status =='edit'}" > ${author.id} </c:if> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_two_affiliation"/></td>
                    <td><form:input path="affiliation" /></td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_two_email"/></td>
                    <td><form:input path="email" /></td>
                </tr>
            </table>
            <div style=" width: 30%; margin:5px auto;">
                <input id="savebtn" onclick="setSelectedAction('savebtn')" onselect="setSelectedAction('savebtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="save" alt="<spring:message code="save.caption"/>" title="<spring:message code="save.caption"/>" />
                <input id="clearbtn" onclick="setSelectedAction('clearbtn')" onselect="setSelectedAction('clearbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>undo.png" value="reset" alt="<spring:message code="reset.caption"/>" title="<spring:message code="reset.caption"/>" />
            </div>
            <table class="grideTable">
                <tr>
                    <th><spring:message code="authors_submission_wizard_two_table_firstName"/></th>
                    <th><spring:message code="authors_submission_wizard_two_table_lastName"/></th>
                    <th><spring:message code="authors_submission_wizard_two_table_degree"/></th>
                    <th><spring:message code="authors_submission_wizard_two_table_affiliation"/></th>
                    <th><spring:message code="authors_submission_wizard_two_table_email"/></th>
                </tr>
                <c:forEach var="authorItem" items="${authorSubmissionWizard.authors}">
                    <c:url var="editUrl" value="/authors/submission/wizard/two/edit">
                        <c:param name="id" value="${authorItem.id}" />
                    </c:url>
                    <c:url var="deleteUrl" value="/authors/submission/wizard/two/delete">
                        <c:param name="id" value="${authorItem.id}" />
                    </c:url>

                    <tr id="tr_${authorItem.id}">
                        <td>${authorItem.firstName}</td><td>${authorItem.lastName}</td><td>${authorItem.degree}</td>
                        <td>${authorItem.affiliation}</td><td>${authorItem.email}</td>
                        <td><a href='<c:out value="${editUrl}"/>'><spring:message code="authors_submission_wizard_two_editlable"/></a></td>
                        <td><a id="td_${authorItem.id}" onclick="return confirmdelete('${authorItem.id}','<spring:message code="delete.confirmation.title"/>'
                            ,'<spring:message code="delete.confirmation.message"/>','<spring:message code="delete.confirmation.accept"/>',
                            '<spring:message code="delete.confirmation.deny"/>');" 
                            href="#" rev='<c:out value="${deleteUrl}"/>'  ><spring:message code="authors_submission_wizard_two_deletelable"/></a></td>
                    </tr> 

                </c:forEach>
            </table>
        </fieldset>
        <div class="button_panel">
                <input id="cancelbtn" 
                onclick="return setSelectedActionByConfirm('cancelbtn'            
                ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','author');" 
                onselect="return setSelectedActionByConfirm('cancelbtn'
                ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','author');" 
                class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>cancel.png" value="cancel" alt="<spring:message code="cancel.caption"/>" title="<spring:message code="cancel.caption"/>" />

                <input id="backbtn" onclick="setSelectedAction('backbtn')" onselect="setSelectedAction('backbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="back.picture"/>.png" value="back" alt="<spring:message code="back.caption"/>" title="<spring:message code="back.caption"/>" />
                <input id="nextbtn" onclick="setSelectedAction('nextbtn')" onselect="setSelectedAction('nextbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="next.picture"/>.png" value="next" alt="<spring:message code="next.caption"/>" title="<spring:message code="next.caption"/>" />
                <input id="selected_action" type="hidden" name="selected_action" value="" />
        </div>  
    </form:form>

</div>
