<%-- 
    Document   : editorial_tracking_publication
    Created on : Aug 11, 2011, 3:10:36 AM
    Author     : amir
--%>


<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div class="item">

    <h3 class="headerOfTable"> Publication</h3>

    <table class="grideTable" style=" width: 95%;">
        <tr>
            <th>Title</th>
            <th>Publication Type</th>
            <th>Status</th>
        </tr>
        <tr>
            <td>${publication.title} </td>
            <td>${publication.publicationType} </td>
            <td>
                <c:if test="${publication.reviewStatus==1}"> Public</c:if> 
                <c:if test="${publication.reviewStatus==2}"> Reviewed</c:if> 
                <c:if test="${publication.reviewStatus==3}"> Rejected</c:if> 
                <c:if test="${publication.reviewStatus==4}"> In completed</c:if> 
                <c:if test="${publication.reviewStatus==5}"> Being Reviewed</c:if> 
            </td>
        </tr>  
    </table>

    <h3 class="headerOfTable" >Paper Group ${publication.paperGroup.title}</h3>
    <table class="grideTable" style=" width: 95%;">
        <tr>
            <th>Title</th>
            <th>Description</th>
            <th>Is Blind Review?</th>
        </tr>
        <tr>
            <td>${publication.paperGroup.title} </td>
            <td>${publication.paperGroup.description} </td>
            <td>
                <c:if test="${publication.paperGroup.blind_review==0}"> NO</c:if> 
                <c:if test="${publication.paperGroup.blind_review==1}"> Yes</c:if> 
            </td>
        </tr>  
    </table>

    <h3 class="headerOfTable">Paper Group's Referees</h3>
    <table class="grideTable" style=" width: 95%;">
        <tr>
            <th>User Name</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Email</th>
            <th>Research Area</th>
        </tr>
        <c:forEach var="referee" items="${publication.paperGroup.referees}">
            <tr>
                <td>${referee.userName} </td>
                <td>${referee.firstName} </td>
                <td>${referee.lastName} </td>
                <td>${referee.email}</td>
                <td>
                    <c:forEach var="researchArea" items="${referee.researchAreaList}">
                        ${researchArea.title},&nbsp;
                    </c:forEach>
                </td>
            </tr>  
        </c:forEach>
    </table>
    
    <h3 class="headerOfTable">Referee's Feedback</h3>
    <table class="grideTable" style=" width: 95%;">
        <tr>
            <th>User Name</th>
            <th>Referee</th>
            <th>Title</th>
            <th>Review</th>
        </tr>
        <c:forEach var="peerReview" items="${peerReviewList}">
            <tr>
                <td>${peerReview.referee.userName} </td>
                <td>${peerReview.referee.firstName}&nbsp;${peerReview.referee.lastName} </td>
                <td>${peerReview.peerReviewTemplate.title} </td>
                <td>${peerReview.review}</td>
            </tr>  
        </c:forEach>
    </table>
    
    <div class="clear" style="margin-top:30px; "></div>
    <a href="<c:url value='/editorial'/>"> Back to editorial </a>
    <div class="clear"></div>

</div>
