
<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<c:if test="${login.lastLoginStatus!='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/login.jsp" %>
</c:if>

<c:if test="${login.lastLoginStatus=='ok'}">
    
    <%@ include file="/WEB-INF/pages/jsp/logout.jsp" %>
    <p></p>

    <table class="grideTable" style=" width: 85%;" >
        <tr>
            <th>Title</th>
            <th>Type</th>
            <th>Status</th>
            <th>Details</th>
            <th>Reason</th>
            <th>Save</th>
        </tr>
        <c:forEach var="publication" items="${publicationList}">
            <form:form method="POST" action="${pageContext.request.contextPath}/reviewers" >
                <tr >
                    <td>${publication.title}</td>
                    <td>${publication.publicationType}</td>
                    <td>
                        <select disabled="disabled">
                            <option value="1" <c:if test="${publication.reviewStatus==1}"> selected</c:if> >Public</option>
                            <option value="2" <c:if test="${publication.reviewStatus==2}"> selected</c:if> >Reviewed</option>
                            <option value="3" <c:if test="${publication.reviewStatus==3}"> selected</c:if> >Rejected</option>
                            <option value="4" <c:if test="${publication.reviewStatus==4}"> selected</c:if> >In completed</option>
                            <option value="5" <c:if test="${publication.reviewStatus==5}"> selected</c:if> >Being Reviewed</option>
                        </select>
                    </td>
                    <td>
                        <a target="_blank" href="<c:url value='/publication/abstract/${publication.id}'/>">More Info</a>
                    </td>
                    <td>
                         <select id="template" name="template">
                            <option title=""  value="0" selected >Please select an option</option>
                            <c:forEach var="template" items="${templates}">
                                <option value="${template.id}" title="${template.fullTemplate}">${template.title}</option>
                            </c:forEach>
                        </select>
                        <textarea id="review" name="review" rows="7" cols="35"> </textarea>
                    </td>
                    <td>
                        <input id="Save" class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>save.png" value="Save" alt="Save" title="Save" />
                    </td>
                </tr>
                <tr class="hidden">
                    <td>
                        <input type="text" name="publicationID" value="${publication.id}"/>
                    </td>
                </tr>
            </form:form>
        </c:forEach>
    </table>
    <c:if test="${CountOfPublication=='0'}">
        <p style=" margin: 20px; padding:20px; display: inline-block; border-style:outset;border-color:#98bf21; font-size: 14px; font-weight: bold; color: darkcyan">
            There is not any manuscript to review
        </p>
    </c:if>
</c:if>
