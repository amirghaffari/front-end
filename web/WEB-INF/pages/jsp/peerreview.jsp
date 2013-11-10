<%-- 
    Document   : peerreview
    Created on : Aug 6, 2011, 8:31:04 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="vertical-menu" class="sidebar" style=" width: 225px;">
    <div class="header"><spring:message code="peerreview_header"/></div>
    <ul>
        <li><a href="<c:url value='/authors/'/>"><spring:message code="peerreview_author"/></a></li>
        <li><a href="<c:url value='/reviewers/'/>"><spring:message code="peerreview_reviewer"/></a></li>
        <li><a href="<c:url value='/editorial'/>"><spring:message code="peerreview_editorial"/></a></li>
    </ul>
</div>

<div  class="item" id="author-instruction" style=" text-align: justify;">
    <p class="bigHeader">Peer Review Definition </p>
    <p class="justify">Peer review is the evaluation of creative work or performance by other people in the same 
        field in order to maintain or enhance the quality of the work. In the case of peer reviewed journals, 
        which are usually academic and scientific periodicals, peer review generally refers to the evaluation 
        of articles prior to publication. The Internet is beginning to have a major effect on peer review. One 
        way has been to increase the speed and lower the cost of the communications involved in reviewing works, 
        such as articles prior to publication. In addition, the movement of publications from hard copy format to 
        online format, which is still in its early stages, will further increase the speed of publishing, reduce the 
        cost of publishing, and make the publications much more widely available, thereby facilitating the 
        post-publication review of articles by a larger number and greater variety of people.
    </p>
    <p class="bigHeader">Peer Review Procedure</p>
    <p>
        <ul>
            <li>
                The first step is to be taken by the author. It is able to submit manuscripts with supplementary file such as Meta data, etc according to
                the instructions issued by the journal editor. Track the submission in review and editorial process.
            </li>
            <li> 
                Editor is permitted to supervise all process such as referee and editing. It is able to assign reviewer, copy editor and typography for manuscript. 
                Editor works becomes difficult only when there is significant disagreement in the reviewer?s suggestions. 
                In such case the editor may make a final decision based on the own opinion or after consulting additional referees. 
            </li>
            <li> 
                Reviewer are responsible for reviewing and evaluation submitted manuscript and supplementary files. Also it is permitted to
                submit the result of review for editor and put comment for editor and author. Each referee independently advises the editor whether to accept or
                to reject the paper.
            </li>
        </ul>
    </p>
</div>
