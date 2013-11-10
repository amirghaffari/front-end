<%-- 
    Document   : author_submission_wizard_two
    Created on : Jun 29, 5011, 10:40:40 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<div id="authors_submit_form" class="inputform">
    
    <p class="item" ><h2><spring:message code="authors_submission_wizard_three_header"/></h2></p>
    <p class="error item">
         <c:forEach var="errorCode" items="${Error_List}">
             <spring:message code="${errorCode}"/>
         </c:forEach>
    </p>
    
    <form:form method="POST"  action="${pageContext.request.contextPath}/authors/submission/wizard/three" commandName="publication" >
        <fieldset>
            <legend><b><spring:message code="authors_submission_wizard_three_legend"/></b></legend>
            <table>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_type_caption"/></td>
                    <td>
                        <form:select path="publicationType" items="${PublicationType}"/>
                        <span class="error"> <form:errors path="publicationType" /> </span> 
                    </td>
                </tr>
                 <tr>
                    <td><spring:message code="authors_submission_wizard_three_title_caption"/></td>
                    <td>
                        <form:textarea path="title" rows="5" cols="50" /> 
                        <span class="error"> <form:errors path="title" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_abstract_caption"/></td>
                    <td>
                        <form:textarea path="abstracts" rows="5" cols="50" /> 
                        <span class="error"> <form:errors path="abstracts" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_keywords_caption"/></td>
                    <td>
                        <form:textarea path="keyword" rows="5" cols="50" /> 
                        <span class="error"> <form:errors path="keyword" /> </span> 
                    </td>
                </tr>
                <tr>
                    <td><spring:message code="authors_submission_wizard_three_comment_caption"/></td>
                    <td>
                        <form:textarea path="note" rows="5" cols="50" /> 
                        <span class="error"> <form:errors path="note" /> </span> 
                    </td>
                </tr>
            </table> 
        </fieldset>
        <div class="button_panel">
            
                <input id="cancelbtn" 
                onclick="return setSelectedActionByConfirm('cancelbtn'            
                ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','publication');" 
                onselect="return setSelectedActionByConfirm('cancelbtn'
                ,'<spring:message code="cancel.confirmation.title"/>','<spring:message code="cancel.confirmation.message"/>',
                '<spring:message code="cancel.confirmation.accept"/>','<spring:message code="cancel.confirmation.deny"/>','publication');" 
                class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>cancel.png" value="cancel" alt="<spring:message code="cancel.caption"/>" title="<spring:message code="cancel.caption"/>" />

                <input id="backbtn" onclick="setSelectedAction('backbtn')" onselect="setSelectedAction('backbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="back.picture"/>.png" value="back" alt="<spring:message code="back.caption"/>" title="<spring:message code="back.caption"/>" />
                <input id="nextbtn" onclick="setSelectedAction('nextbtn')" onselect="setSelectedAction('nextbtn')" class="button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/><spring:message code="next.picture"/>.png" value="next" alt="<spring:message code="next.caption"/>" title="<spring:message code="next.caption"/>" />
                <input id="selected_action" type="hidden" name="selected_action" value="" />
        </div>  
    </form:form>
</div>
