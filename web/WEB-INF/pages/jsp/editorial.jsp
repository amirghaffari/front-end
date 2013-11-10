<%-- 
    Document   : editorial
    Created on : Aug 6, 2011, 9:02:51 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<c:if test="${login.lastLoginStatus!='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/login.jsp" %>
</c:if>

<c:if test="${login.lastLoginStatus=='ok'}">

    <%@ include file="/WEB-INF/pages/jsp/logout.jsp" %>
    <p></p>

    <div class="item">

            <table class="grideTable" style=" width: 95%;" >

                    <tr>
                        <th><spring:message code="authors_submission_wizard_last_table_title"/></th>
                        <th><spring:message code="authors_submission_wizard_last_table_publication_type"/></th>
                        <th><spring:message code="authors_submission_wizard_last_table_lastState"/></th>
                        <th>Track the last Condition</th>
                        <th>Paper group</th>
                    </tr>

                    <c:forEach var="publication" items="${publicationList}">
                        <form:form method="POST" action="${pageContext.request.contextPath}/editorial/assignPaperGroup" >
                        <tr >
                            <td>${publication.title}</td>
                            <td>${publication.publicationType}</td>
                            <td>
                                <select name="reviewStatus">
                                    <option value="1" <c:if test="${publication.reviewStatus==1}"> selected</c:if> >Public</option>
                                    <option value="2" <c:if test="${publication.reviewStatus==2}"> selected</c:if> >Reviewed</option>
                                    <option value="3" <c:if test="${publication.reviewStatus==3}"> selected</c:if> >Rejected</option>
                                    <option value="4" <c:if test="${publication.reviewStatus==4}"> selected</c:if> >In completed</option>
                                    <option value="5" <c:if test="${publication.reviewStatus==5}"> selected</c:if> >Being Reviewed</option>
                                </select>
                            </td>
                            <td><a href="<c:url value='/editorial/tracking?publicationId=${publication.id}'/>"> Track </a></td>
                            <td>
                                <select id="paperGroupId" name="paperGroupId" class="pageGroupSelectTag">
                                    <c:if test="${publication.paperGroup==null}">
                                        <option value="0" selected> Unassigned Paper Group </option>
                                    </c:if>
                                    <optgroup label="Built-In Paper Groups">
                                        <c:forEach var="paperGroup" items="${paperGroupList}">
                                            <option value="${paperGroup.id}" <c:if test="${publication.paperGroup!=null && publication.paperGroup.id==paperGroup.id}"> selected</c:if> >${paperGroup.title}</option>
                                            <c:if test="${paperGroup.id==3}">
                                                </optgroup>
                                                <optgroup label="User's Paper Groups">
                                            </c:if>
                                        </c:forEach>
                                    </optgroup>
                                    <option style="font-weight: bold; color: #046a14" class="${publication.id}" value="-1">Add New Paper Group </option>
                                </select>
                                <div class="hidden"> <input type="text" name="publicationId" value="${publication.id}" /> </div>
                            </td>
                            <td>
                                <input id="Save" class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="Save" alt="Save" title="Save" />
                            </td>
                        </tr>
                        </form:form>        
                    </c:forEach>

            </table>
            <c:if test="${CountOfPublication=='0'}">
                <p style=" margin: 20px; padding:20px; display: inline-block; border-style:outset;border-color:#98bf21; font-size: 14px; font-weight: bold; color: darkcyan">
                    There is not any manuscript under review
                </p>
            </c:if>
    </div>
    <div id="addpapergroup" class="popup_block inputform">
        <form:form method="POST" action="${pageContext.request.contextPath}/editorial/addPaperGroup" commandName="paperGroup" >   
        <fieldset> 
        <legend><b>Add New Paper Group</b></legend>
            <table>
                <tr>
                    <td>Title</td>
                    <td><form:input path="title" /></td>
                </tr>
                <tr>
                    <td>Description</td>
                    <td><form:textarea path="description" rows="3" cols="20" /></td>
                </tr>
                <tr>
                    <td>
                        Review Type
                    </td>
                    <td>
                        <form:select path="blind_review">
                            <form:option value="1" label="Blind Review" />
                            <form:option value="2" label="Single Review" />
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>
                        Reviewers(Multi Select)
                    </td>
                    <td>
                        <select id="selectedReviewers" name="selectedReviewers" multiple="multiple">
                            <c:forEach var="reviewer" items="${reviewers}">
                                <option value="${reviewer.id}">${reviewer.firstName} &nbsp; ${reviewer.lastName}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="hidden">
                        <input type="text" name="publicationId" id="publicationId" value="0"/>
                    </td>
                    <td>
                        <input id="Save" onclick="var r1= checkSelectIsSelected('selectedReviewers','Please select one reviewer at least');var r2= textBoxNotNull('title'); return (r1&&r2) " class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="Save" alt="Save" title="Save" />
                    </td>
                </tr>
            </table>
        </fieldset>
        </form:form>
    </div>
</c:if>
