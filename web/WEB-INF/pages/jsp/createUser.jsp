<%-- 
    Document   : createUser
    Created on : Aug 26, 2011, 2:47:03 AM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div class="inputform">
    <p><h2><spring:message code="create_user_header"/></h2></p>
    <form:form method="POST"  action="${pageContext.request.contextPath}/home/addUser" commandName="user" >
            <fieldset>
            <legend><b><spring:message code="create_user_personal_info"/> </b></legend>
                <table>
                    <tr>
                        <td><spring:message code="create_user_personal_info_firstName"/></td>
                        <td><form:input path="firstName" /> <span class="error"> <form:errors path="firstName" /> </span> </td>
                    </tr>   
                    <tr>
                        <td><spring:message code="create_user_personal_info_lastName"/></td>
                        <td><form:input path="lastName" /> <span class="error"> <form:errors path="lastName" /> </span> </td>
                    </tr> 
                    <tr>
                        <td><spring:message code="create_user_personal_info_email"/></td>
                        <td><form:input path="email" /> <span class="error"> <form:errors path="email" /> </span> </td>
                    </tr> 
                    <tr>
                        <td><spring:message code="create_user_personal_info_userName"/></td>
                        <td><form:input path="userName" /> <span class="error"> <form:errors path="userName" /> </span> </td>
                    </tr> 
                    <tr>
                        <td><spring:message code="create_user_personal_info_password"/></td>
                        <td><form:input path="password" /> <span class="error"> <form:errors path="password" /> </span> </td>
                    </tr> 
                    <tr>
                        <td><spring:message code="create_user_personal_info_degree"/></td>
                        <td><form:input path="degree" /> <span class="error"> <form:errors path="degree" /> </span> </td>
                    </tr> 
                    <tr>
                        <td><spring:message code="create_user_personal_info_institution"/></td>
                        <td><form:input path="institution" /> <span class="error"> <form:errors path="institution" /> </span> </td>
                    </tr> 
                    <tr>
                        <td>
                            <spring:message code="create_user_personal_info_role"/>
                        </td>
                        <td>
                            <select id="roles" name="roles" multiple="multiple">
                                <c:forEach var="role" items="${roleList}">
                                    <option value="${role.id}">${role.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <spring:message code="create_user_personal_info_researchAreas"/>
                        </td>
                        <td>
                            <select id="researchAreas" name="researchAreas" multiple="multiple">
                                <c:forEach var="researchArea" items="${researchAreaList}">
                                    <option value="${researchArea.id}">${researchArea.title}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr class="hidden">
                        <td >
                            <input type="text" name="backAddress" value="${backAddress}"/> 
                            <input type="text" name="pageGroup" value="${pageGroup}"/> 
                        </td>
                    </tr>
                </table>
            </fieldset>
            <div class="button_panel">
                <input id="register" onclick="var r1=checkSelectIsSelected('roles','Please select one role at least'); var r2=checkSelectIsSelected('researchAreas','Please select one research area at least'); if(r1&&r2) return true; else return false;" class="keepScrollPosition button_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="register" alt="<spring:message code="publication_abstract_loginform_submit"/>" title="<spring:message code="publication_abstract_loginform_submit"/>" />
            </div>
    </form:form>
</div>
