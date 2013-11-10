<%-- 
    Document   : authors_submission_wizard_four
    Created on : Jul 10, 2011, 11:36:51 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<div id="authors_submit_form" class="inputform">
    <p class="item" ><h2><spring:message code="authors_submission_wizard_four_header"/></h2></p>
    <p class="error item">
         <c:forEach var="errorCode" items="${Error_List}">
             <spring:message code="${errorCode}"/>
         </c:forEach>
    </p>
    <form:form method="POST" action="${pageContext.request.contextPath}/authors/submission/wizard/four" commandName="uploadFile" enctype="multipart/form-data" >
        <fieldset>
        <legend><b><spring:message code="authors_submission_wizard_four_legend"/></b></legend>
        <table>
            <tr>
                <td>
                    <spring:message code="authors_submission_wizard_four_filetype_caption"/>
                </td>
                <td>
                    <form:select path="fileType" items="${fileTypes}"/>
                </td>
            </tr>
            <tr>
                <td>
                    <spring:message code="authors_submission_wizard_four_filename_caption"/>
                </td>
                <td>
                    <form:input path="fileData" type="file"/>
                    <span class="error"> <form:errors path="fileData" /> </span> 
                </td>
            </tr>
        </table>
        <div style=" width: 30%; margin:5px auto;">
            <input id="savebtn" onclick="setSelectedAction('savebtn')" onselect="setSelectedAction('savebtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="save" alt="<spring:message code="save.caption"/>" title="<spring:message code="save.caption"/>" />
            <input id="clearbtn" onclick="setSelectedAction('clearbtn')" onselect="setSelectedAction('clearbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>undo.png" value="reset" alt="<spring:message code="reset.caption"/>" title="<spring:message code="reset.caption"/>" />
        </div>
        <table class="grideTable">
            <tr>
                <th><spring:message code="authors_submission_wizard_four_table_description"/></th>
                <th><spring:message code="authors_submission_wizard_four_table_filename"/></th>
                <th><spring:message code="authors_submission_wizard_four_table_filesize"/></th>
                <th><spring:message code="authors_submission_wizard_four_table_remove"/></th>
            </tr>
            <c:forEach var="uploadedItem" items="${authorSubmissionWizard.uploadFiles}">
                <c:url var="deleteUrl" value="/authors/submission/wizard/four/delete">
                    <c:param name="localFileName" value="${uploadedItem.localFileName}" />
                </c:url>

                <tr id="tr_${uploadedItem.localFileName}">
                    <td>${uploadedItem.fileType}</td><td>${uploadedItem.originalFileName}</td><td>${uploadedItem.fileSize}</td>
                    <td><a id="td_${uploadedItem.localFileName}" onclick="return confirmdelete('${uploadedItem.localFileName}','<spring:message code="delete.confirmation.title"/>'
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
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','uploadFile');" 
                onselect="return setSelectedActionByConfirm('cancelbtn'
                ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','uploadFile');" 
                class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>cancel.png" value="cancel" alt="<spring:message code="cancel.caption"/>" title="<spring:message code="cancel.caption"/>" />

                <input id="backbtn" onclick="setSelectedAction('backbtn')" onselect="setSelectedAction('backbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="back.picture"/>.png" value="back" alt="<spring:message code="back.caption"/>" title="<spring:message code="back.caption"/>" />
                <input id="nextbtn" onclick="setSelectedAction('nextbtn')" onselect="setSelectedAction('nextbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="next.picture"/>.png" value="next" alt="<spring:message code="next.caption"/>" title="<spring:message code="next.caption"/>" />
                <input id="selected_action" type="hidden" name="selected_action" value="" />
        </div>  

    </form:form>
</div>
