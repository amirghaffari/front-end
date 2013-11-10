<%-- 
    Document   : login
    Created on : Aug 15, 2011, 3:44:28 PM
    Author     : amir
--%>

<div id="publication_abstract_login_form" class="inputform">
    <p><h2><spring:message code="publication_abstract_loginform_header"/></h2></p>
    <form:form method="POST"  action="${pageContext.request.contextPath}/publication/login" commandName="login" >
        <fieldset>
        <legend><b><spring:message code="publication_abstract_signin"/>:</b>&nbsp;<spring:message code="publication_abstract_loginform_header2"/></legend>
            <table>
                <tr>
                    <td><spring:message code="publication_abstract_loginform_username"/></td>
                    <td><form:input path="username" onBlur="if(this.value=='') this.value='User Name'" onfocus="if(this.value=='User Name') this.value=''" value="User Name" /></td>
                </tr> 
                <tr>
                    <td><spring:message code="publication_abstract_loginform_password"/></td>
                    <td><form:input path="password" type="password" onBlur="if(this.value=='') this.value='Password'" onfocus="if(this.value=='Password') this.value=''" value="Password" /></td>
                </tr>
                <tr>
                    <td>
                        <input id="signin" class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>admin.png" value="post" alt="<spring:message code="publication_abstract_loginform_submit"/>" title="<spring:message code="publication_abstract_loginform_submit"/>" />
                    </td> 
                    <td class="error">
                        <c:if test="${login.lastLoginStatus=='failed'}">
                            <spring:message code="publication_abstract_invalid_login"/>
                        </c:if>
                        <c:if test="${login.lastLoginStatus=='inconsistent'}">
                            <spring:message code="publication_abstract_inconsistent_login"/>
                        </c:if>
                    </td>
                </tr>
                <tr class="hidden">
                    <td></td>
                    <td >
                        <input type="text" name="backAddress" value="${login.backAddress}"/> 
                        <form:input path="pageGroup" /> 
                    </td>
                </tr>
            </table>
        </fieldset>
    </form:form>
    <a class="left poplight" rel="login_message" href="#?w=300" onclick="this.blur(); return false;"> <!--<spring:message code="publication_abstract_loginform_forgot"/>-->Help </a>
    <a class="right " rel="login_message" href="<c:url value='/home/createUser?backAddress=${login.backAddress}&pageGroup=${login.pageGroup}'/>" > <spring:message code="publication_abstract_loginform_signup"/> </a>
    <div id="login_message" class="popup_block">
        <span style="font:bold x-large serif;" >Notice </span>
        <img class="right" width="70" height="70" src="${pageContext.request.contextPath}/<spring:theme code="image"/>info.jpg" alt=""/>
        <p><b>This is demo version. In demo version you can create a new user or login with following Info:</b></p>  
        <p>
            user name: reader<br/>
            password: 123<br/>
            role(s): reader
        </p>
        <p>
            user name: author<br/>
            password: 123<br/>
            role(s): reader, author
        </p>
        <p>
            user name: referee1<br/>
            password: 123<br/>
            role(s): reader, referee
        </p>
        <p>
            user name: referee2<br/>
            password: 123<br/>
            role(s): reader, referee
        </p>
        <p>
            user name: editorial<br/>
            password: 123<br/>
            role(s): reader, editorial
        </p>
    </div>
</div>
