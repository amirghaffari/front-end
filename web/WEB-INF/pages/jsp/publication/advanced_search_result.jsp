<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="article" class="lbox" style="width:295px;">
    <p class="bigHeader">
        <spring:message code="search_publication_result_title"/> 
        <b> <c:out value="(${searchResultCount})"/> </b>
    </p>

    <c:forEach items="${searchResult}" var="publication">
        <div class="itemBoundery"> 
            <a href="<c:url value='/publication/abstract/${publication.id}'/>">${publication.title}</a>
            <br/>by
            <c:forEach items="${publication.authors}" var="author">
                <c:out value="${author.name}"/> <br/>
            </c:forEach>
            ${publication.year} ${publication.month}: ${publication.edition}.
            <br/>
            <a href="<c:url value='/publication/abstract/${publication.id}'/>">Abstract</a> |
            <c:if test="${publication.fullText=='true'}">
                <a target="_blank" href="<c:url value='/pdf/${publication.id}.pdf'/>">Full Text</a>  | 
            </c:if>
            <a id="${publication.id}_1" OnClick="return Appear('${publication.id}');" href="#">QUICK ABSTRACT</a>|
            <a id="${publication.id}_2" OnClick="return Hide('${publication.id}');" class="hidden" href="#">HIDE ABSTRACT</a>
            <br/>
            <div id="${publication.id}_3" class="hidden" >
                <span id="first_page_${publication.id}">First Page of the Article<br/></span>
                <img  src="${pageContext.request.contextPath}/style/article_images/${publication.id}.png" width="300" height="300" alt="${publication.note}" onerror="$('#first_page_'+${publication.id}).hide(); $(this).hide();$('#alternative_${publication.id}').show();"/>
                <p class="hidden" id="alternative_${publication.id}">
                    ${publication.abstracts}
                </p>
                <p>
                    <a href="<c:url value='/publication/abstract/${publication.id}'/>"> Read More &#187;</a>
                </p>
            </div>
        </div>
    </c:forEach>    
</div>
