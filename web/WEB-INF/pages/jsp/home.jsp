<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="vertical-menu" class="sidebar">
    <div class="inputform" >
        <form:form method="POST" commandName="searchItems" action="${pageContext.request.contextPath}/search">
            <fieldset>
            <legend><spring:message code="home_qsearch"/></legend>
                <spring:message code="home_qsearch_title"/> 
                <form:input path="allCategories" size="20"/>
                <br />
                <input type="submit" value="<spring:message code="home_submit_caption"/>" />
            </fieldset>
        </form:form>
    </div>
    <!--
    <div class="header"> <spring:message code="home_menu_title"/></div>
    <ul>
            <li><a href="<c:url value='/authors/profiles'/>"> <spring:message code="home_menu_author_profiles"/></a></li>
            <li><a href="<c:url value='#'/>"><spring:message code="home_menu_peer_reviewed_articles"/></a></li>
            <li><a href="<c:url value='#'/>"><spring:message code="home_menu_theses"/></a></li>
            <li><a href="<c:url value='#'/>"><spring:message code="home_menu_books"/></a></li>
            <li><a href="<c:url value='#'/>"><spring:message code="home_menu_contributors"/></a></li>
            <li><a href="<c:url value='#'/>"><spring:message code="home_menu_legal"/></a></li>
    </ul>
    -->
</div>

<div id="content">
    
    <div id="article" class="lbox" style="width:295px;">
        <p class="bigHeader"><spring:message code="home_recent_article"/> </p>

        <c:forEach items="${articles}" var="article">
            <div class="itemBoundery"> 
                <a href="<c:url value='/publication/abstract/${article.id}'/>">${article.title}</a>
                <br/>by
                <c:forEach items="${article.authors}" var="author">
                    <c:out value="${author.name}"/> <br/>
                </c:forEach>
                ${article.year} ${article.month}: ${article.edition}.
                <br/>
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

    <div id="book" class="lbox" style=" width:295px;">
        <p class="bigHeader"><spring:message code="home_recent_book"/></p>
        
        <c:forEach items="${books}" var="book">
            <div class="itemBoundery"> 
                <a href="<c:url value='/publication/abstract/${book.id}'/>">${book.booktitle}</a>
                <br/>by
                <c:forEach items="${book.authors}" var="author">
                     <c:out value="${author.name}"/> <br/>
                </c:forEach>
                ${book.year} ${book.month}: ${book.edition}.
                <br/>
                <a href="<c:url value='/publication/abstract/${book.id}'/>">Abstract</a> |
                <c:if test="${book.fullText=='true'}">
                    <a target="_blank" href="<c:url value='/pdf/${book.id}.pdf'/>">Full Text</a>  | 
                </c:if>
                <a id="${book.id}_1" OnClick="return Appear('${book.id}');" href="#">QUICK ABSTRACT</a>|
                <a id="${book.id}_2" OnClick="return Hide('${book.id}');" class="hidden" href="#">HIDE ABSTRACT</a>
                <br/>
                <div id="${book.id}_3" class="hidden" >
                    <span id="first_page_${book.id}">First Page of the Article<br/></span>
                    <img  src="${pageContext.request.contextPath}/style/article_images/${book.id}.png" width="300" height="300" alt="${book.note}"  onerror="$('#first_page_'+${book.id}).hide(); $(this).hide();$('#alternative_${book.id}').show();"/>
                    <p class="hidden" id="alternative_${book.id}">
                        ${book.abstracts}
                    </p>
                    <p>
                        <a href="<c:url value='/publication/abstract/${book.id}'/>"> Read More &#187;</a>
                    </p>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

