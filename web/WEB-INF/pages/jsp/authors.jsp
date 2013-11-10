
<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="vertical-menu" class="sidebar" style=" width: 225px;">
    <div class="header"><spring:message code="authors_menu_header"/></div>
    <ul>
        <li><a href="<c:url value='/authors/instruction'/>"><spring:message code="authors_menu_instructions"/></a></li>
        <li><a href="<c:url value='/authors/submission/instruction'/>"><spring:message code="authors_menu_submission_instruction"/></a></li>
        <li><a href="<c:url value='/authors/previousquery'/>"><spring:message code="authors_menu_previous_submission"/></a></li>
        <li><a href="<c:url value='/authors/submission/wizard/one'/>"><spring:message code="authors_menu_Submit_manuscript"/></a></li> 
    </ul>
</div>
