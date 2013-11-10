<%-- 
    Document   : statisticalview
    Created on : Aug 6, 2011, 7:59:40 PM
    Author     : amir
--%>

<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<p calss="item">
<div  id="statictic_tagcloud">
        <img border="0" src="${pageContext.request.contextPath}/<spring:theme code="image"/>tag_cloud.jpg" alt="Tag Cloud" width="130" height="130" />
        <c:forEach items="${tagCloud}" var="tag">
            <span id="${tag.id}" class="wrd tagcloud${tag.groupId}"><a href="<c:url value='/search/tag?tagName=${tag.name}'/>">${tag.name}<span class="freq">&nbsp;(${tag.frequency})</span></a></span> 
        </c:forEach>
    </div>
</p>

<div id="statictic_rating_header">
    Ten Top Rated Publication
</div>

<div id="statictic_rating_body_list">
    <c:forEach items="${ratedList}" var="article">
            <div class="itemBoundery"> 
                <a href="<c:url value='/publication/abstract/${article.id}'/>">${article.title}</a>
                <br/>by
                <c:forEach items="${article.authors}" var="author">
                     <c:out value="${author.name}"/> <br/>
                </c:forEach>
                ${article.year} ${article.month}: ${article.edition}.
                <br/>
                <p>
                    Average of rate(<b><c:out value="${article.averageOfRates}"/></b>)
                </p>
                <p>
                    Number of votes(<b><c:out value="${article.countOfRates}"/></b>)
                </p>
                <a href="<c:url value='/publication/abstract/${article.id}'/>">Abstract</a> |
                <c:if test="${article.fullText=='true'}">
                    <a target="_blank" href="<c:url value='/pdf/${article.id}.pdf'/>">Full Text</a>  | 
                </c:if>
                <a id="${article.id}_1" OnClick="return Appear('${article.id}');" href="#">QUICK ABSTRACT</a>|
                <a id="${article.id}_2" OnClick="return Hide('${article.id}');" class="hidden" href="#">HIDE ABSTRACT</a>
                <br/>
                <div id="${article.id}_3" class="hidden" >
                    <span id="first_page_${article.id}">First Page of the Article<br/></span>
                    <img  src="${pageContext.request.contextPath}/style/article_images/${article.id}.png" width="300" height="300" alt="${article.note}" onerror="$('#first_page_'+${article.id}).hide(); $(this).hide();$('#alternative_${article.id}').show();"/>
                    <p class="hidden" id="alternative_${article.id}">
                        ${article.abstracts}
                    </p>
                    <p>
                        <a href="<c:url value='/publication/abstract/${article.id}'/>"> Read More &#187;</a>
                    </p>
                </div>
            </div>
    </c:forEach>
</div>
