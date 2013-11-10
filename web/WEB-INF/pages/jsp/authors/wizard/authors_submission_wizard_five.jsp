<%-- 
    Document   : authors_submission_wizard_five
    Created on : Jul 12, 2011, 4:19:04 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<div id="authors_submit_form" class="inputform">
    <p><h2><spring:message code="authors_submission_wizard_five_header"/></h2></p>
    <form:form method="POST"  action="${pageContext.request.contextPath}/authors/submission/wizard/five" commandName="authorSubmissionWizard" >
        <br />
        <br />
        <fieldset>
        <legend><b><spring:message code="authors_submission_wizard_five_applicant_legend"/> </b></legend>
            <table>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_title"/></td>
                    <td><form:input readonly="true" path="applicant.title" /> <span class="error"> <form:errors path="applicant.title" /> </span> </td>
                </tr>                
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_firstname"/></td>
                    <td><form:input readonly="true" path="applicant.firstName" /> <span class="error"> <form:errors path="applicant.firstName" /> </span> </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_lastname"/></td>
                    <td><form:input readonly="true" path="applicant.lastName" /> <span class="error"> <form:errors path="applicant.lastName" /> </span> </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_degree"/></td>
                    <td><form:input readonly="true" path="applicant.degree" /> <span class="error"> <form:errors path="applicant.degree" /> </span>  </td>
                </tr>                
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_expert"/></td>
                    <td><form:input readonly="true" path="applicant.expert" /> <span class="error"> <form:errors path="applicant.expert" /> </span> </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_position"/></td>
                    <td><form:input readonly="true" path="applicant.position" /> <span class="error"> <form:errors path="applicant.position" /> </span> </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_institution"/></td>
                    <td><form:input readonly="true" path="applicant.institution" />  <span class="error"> <form:errors path="applicant.institution" /> </span>  </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_address"/></td>
                    <td><form:textarea readonly="true" path="applicant.address" rows="3" cols="20" /> <span class="error"> <form:errors path="applicant.address" /> </span> </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_phone"/></td>
                    <td><form:input readonly="true" path="applicant.phone" />  <span class="error"> <form:errors path="applicant.phone" /> </span>  </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_faxNumber"/></td>
                    <td><form:input readonly="true" path="applicant.faxNumber" /> <span class="error"> <form:errors path="applicant.faxNumber" /> </span>  </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_one_email"/></td>
                    <td><form:input readonly="true" path="applicant.email" />  <span class="error"> <form:errors path="applicant.email" /> </span>  </td>
                </tr>
            </table>
        </fieldset>
        <br />
        <br />
        <fieldset>
        <legend><b><spring:message code="authors_submission_wizard_five_author_legend"/> </b></legend>
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
                    </tr> 
                </c:forEach>
            </table>
        </fieldset>
        <br />
        <br />
        <fieldset>
            <legend><b><spring:message code="authors_submission_wizard_five_other_legend"/></b></legend>
            <table>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_type_caption"/></td>
                    <td>
                        <form:input readonly="true" path="publication.publicationType" />
                        <span class="error"> <form:errors path="publication.publicationType" /> </span> 
                    </td>
                </tr>
                 <tr>
                    <td><spring:message code="authors_submission_wizard_three_title_caption"/></td>
                    <td>
                        <form:textarea path="publication.title" readonly="true" rows="3" cols="50" /> 
                        <span class="error"> <form:errors path="publication.title" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_abstract_caption"/></td>
                    <td>
                        <form:textarea path="publication.abstracts" readonly="true" rows="3" cols="50" /> 
                        <span class="error"> <form:errors path="publication.abstracts" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_keywords_caption"/></td>
                    <td>
                        <form:textarea path="publication.keyword" readonly="true" rows="3" cols="50" /> 
                        <span class="error"> <form:errors path="publication.keyword" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_comment_caption"/></td>
                    <td>
                        <form:textarea path="publication.note" readonly="true" rows="3" cols="50" /> 
                        <span class="error"> <form:errors path="publication.note" /> </span> 
                    </td>
                </tr>
            </table> 
        </fieldset>
        <br />
        <fieldset>
        <legend><b><spring:message code="authors_submission_wizard_five_files_legend"/></b></legend>
        <table class="grideTable">
            <tr>
                <th><spring:message code="authors_submission_wizard_four_table_description"/></th>
                <th><spring:message code="authors_submission_wizard_four_table_filename"/></th>
                <th><spring:message code="authors_submission_wizard_four_table_filesize"/></th>
            </tr>
            <c:forEach var="uploadedItem" items="${authorSubmissionWizard.uploadFiles}">
                <tr id="tr_${uploadedItem.localFileName}">
                    <td>${uploadedItem.fileType}</td><td>${uploadedItem.originalFileName}</td><td>${uploadedItem.fileSize}</td>
                </tr> 
            </c:forEach>
        </table>
        </fieldset>
        <br />
        <br />
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
                

                <input id="acceptbtn" 
                onclick="return setSelectedActionByConfirm('acceptbtn'            
                ,'<spring:message code="accept.confirmation.title"/>','<spring:message code="accept.confirmation.message"/>',
                '<spring:message code="accept.confirmation.accept"/>','<spring:message code="accept.confirmation.deny"/>','authorSubmissionWizard');" 
                onselect="return setSelectedActionByConfirm('acceptbtn'
                ,'<spring:message code="accept.confirmation.title"/>','<spring:message code="accept.confirmation.message"/>',
                '<spring:message code="accept.confirmation.accept"/>','<spring:message code="accept.confirmation.deny"/>','authorSubmissionWizard');" 
                class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>accept.png" value="finish" alt="<spring:message code="accept.caption"/>" title="<spring:message code="accept.caption"/>" />
                
                <input id="selected_action" type="hidden" name="selected_action" value="" />
        </div>  
    </form:form>
</div>