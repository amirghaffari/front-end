<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>


<div id="publication_abstract_lbox" class="lbox">
    <p class="header">
        <b><spring:message code="publication_abstract_title"/>&nbsp;</b>  ${publication.title}<br/>
    </p>
    <div class="item">
        <b><spring:message code="publication_abstract_author"/>&nbsp;</b><br/>
        <c:forEach items="${publication.authors}" var="author">
            <c:out value="${author.name}"/> 
            <a id="${author.id}_1" OnClick="return Appear('${author.id}');" href="#"> <spring:message code="publication_references_moreinfo"/> </a> 
            <a id="${author.id}_2" OnClick="return Hide('${author.id}');" href="#" class="hidden" ><spring:message code="publication_references_hideinfo"/></a>
            <br/>
            <div id="${author.id}_3" class="hidden" >
                <p>
                    Affiliation: ${author.affiliation}<br/>
                    Degree: ${author.degree}<br/>
                    Email: ${author.email}<br/>
                    Address: ${author.address}<br/>
                </p>
            </div>             
            <br/>
        </c:forEach>
        <b><spring:message code="publication_abstract_issue_date"/>&nbsp;</b>  ${publication.year} ${publication.month} <br/>
        <b><spring:message code="publication_abstract_pages"/>&nbsp;</b> ${publication.pages} <br/>
        <b><spring:message code="publication_abstract_isbn"/> &nbsp;</b> ${publication.isbn} <br/>
        <b><spring:message code="publication_abstract_identifier"/> &nbsp;</b> ${publication.id} <br/>
        <b><spring:message code="publication_abstract_publisher"/> &nbsp;</b> ${publication.publisher} <br/>
    </div>

    <p class="header">
        <b><spring:message code="publication_abstract_references"/>&nbsp;</b> <br/>
    </p>
    <div class="item">
        <c:forEach items="${publication.references}" var="reference">
            <div class="itemBoundery">
                <c:out value="${reference.name}"/> <br/>
                <a id="ref${reference.id}_1" OnClick="return Appear('ref${reference.id}');" href="#"> <spring:message code="publication_references_moreinfo"/> </a> 
                <a id="ref${reference.id}_2" OnClick="return Hide('ref${reference.id}');" href="#" class="hidden" ><spring:message code="publication_references_hideinfo"/></a>
                <br/>
                <div id="ref${reference.id}_3" class="hidden" >
                    <p>${reference.notes}</p>
                </div>
            </div>
        </c:forEach>
    </div>
    <p class="header">
        <b><spring:message code="publication_abstract_tags"/>&nbsp;</b> <br/>
    </p>
    <div class="itemBoundery overflow">
        <div class="overflow">
            <c:forEach items="${publication.tags}" var="tag">
                <a href="#?w=400" rel="popup_tag${tag.id}" class="tagbutton poplight tTip" title="${tag.description}" onclick="this.blur(); return false;"><span><c:out value="${tag.name}"/></span></a> 
                <div id="popup_tag${tag.id}" class="popup_block">
                    <span style="font:bold x-large serif;" >Tag Information </span>
                    <img class="right" width="70" height="70" src="${pageContext.request.contextPath}/<spring:theme code="image"/>info.jpg" alt=""/>
                    <p><b>Number of use at knowledge platform:${tag.frequency}</b></p>                
                    <p>${tag.description}</p>
                </div>
            </c:forEach>
        </div>
        <a id="add_new_tag_1" class="right" OnClick="return Appear('add_new_tag');" href="#" title='<spring:message code="publication_abstract_add_tag"/>' ><img width="30px" height="10px" src="${pageContext.request.contextPath}/<spring:theme code="image"/>add.png" alt="<spring:message code="publication_abstract_add_tag"/>" /></a> 
        <div id="add_new_tag_3" class="hidden">
            <a id="add_new_tag_2" class="right" OnClick="return Hide('add_new_tag');"   href="#" title='<spring:message code="publication_abstract_hide_add_tag_form"/>' ><img width="30px" height="10px" src="${pageContext.request.contextPath}/<spring:theme code="image"/>remove.png" alt='<spring:message code="publication_abstract_hide_add_tag_form"/>' /></a> 
            <br/>
            <c:if test="${login.lastLoginStatus=='ok'}">
            <form:form method="POST"  action="${pageContext.request.contextPath}/publication/tag/add" >
                <table>
                    <tr>
                        <td><spring:message code="publication_abstract_tag_name_textbox"/></td>
                        <td><input type="text" name="tagName" /></td>
                    </tr>
                    <tr>
                        <td><spring:message code="publication_abstract_tag_description_textbox"/></td>
                        <td><textarea rows="3" cols="20" name="tagDescription"></textarea></td>
                    </tr>
                    <tr>
                        <td class="hidden">
                            <input type="text" name="backAddress" value="${login.backAddress}"/>
                            <input type="text" name="publicationId" value="${publication.id}"/>
                        </td>
                        <td><input class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>accept.png" value="post" alt="<spring:message code="abstract.add.button.caption"/>" title="<spring:message code="abstract.add.button.caption"/>" /></td>
                    </tr>
                </table>
            </form:form>
            </c:if>
            <c:if test="${login.lastLoginStatus!='ok'}">
                <b class="error"><spring:message code="publication_abstract_no_login"/>&nbsp;</b>
            </c:if>
        </div>
        <br/>
        <br/>
    </div>
    <p class="header">
        <b><spring:message code="publication_abstract_keywords"/>&nbsp;</b> <br/>
    </p>
    <div class="itemBoundery">
        <ul>
        <c:forEach items="${publication.keyWords}" var="keyword">
            <li>
                <a href="<c:url value='/search/keyword?keyword=${keyword.keyWord}'/>">${keyword.keyWord}</a>
            </li>
        </c:forEach>
        </ul>   
    </div>
    <div class="item" id="htmltagcloud">
        <c:forEach items="${tagCloud}" var="tag">
            <span id="${tag.id}" class="wrd tagcloud${tag.groupId}"><a href="<c:url value='/search/tag?tagName=${tag.name}'/>">${tag.name}<span class="freq">&nbsp;(${tag.frequency})</span></a></span> 
        </c:forEach>
    </div>
    <div class="item">
        <img  src="${pageContext.request.contextPath}/style/article_images/${publication.id}.png" width="300" height="300" onerror="$(this).hide();"/>
    </div>
</div>

<c:if test="${login.lastLoginStatus!='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/login.jsp" %>
</c:if>

<c:if test="${login.lastLoginStatus=='ok'}">
    <%@ include file="/WEB-INF/pages/jsp/logout.jsp" %>
    <p></p>
</c:if>

<div id="publication_abstract_abstract" class="rbox">
    <p style="font-style: italic; font-size: 20px;">
        <b><spring:message code="publication_abstract_abstract"/>&nbsp;</b> <br/>
    </p>
    <div style=" text-align: justify;" class="item">
        ${publication.abstracts}
    </div>
</div>



<div id="publication_abstract_rating">
    <form:form method="POST" action="${pageContext.request.contextPath}/publication/rating/add" onSubmit="return checkRadioButtons();">
            <c:if test="${rating ==''}">
                <div class="rating_group" >
                    <b><spring:message code="publication_abstract_rating"/></b>
                </div>
            </c:if>
            <div id="publication_abstract_rating_average" class="${publication.averageOfRates}" >
                <div id="publication_abstract_rating_average1" class="rating_star rating_star_empty"></div>
                <div id="publication_abstract_rating_average2" class="rating_star rating_star_empty "></div>
                <div id="publication_abstract_rating_average3" class="rating_star rating_star_empty"></div>
                <div id="publication_abstract_rating_average4" class="rating_star rating_star_empty"></div>
                <div id="publication_abstract_rating_average5" class="rating_star rating_star_empty"></div>
                <div 
                    style="padding-top:5px;"> Average rating(<c:out value="${publication.countOfRates}"/> votes).
                    <c:if test="${rating !=''}">
                        <p>
                            You rated this <b><c:out value="${rating}"/></b> stars.
                        </p>
                    </c:if>  
                </div>
            </div>

            <c:if test="${rating ==''}">
                <br/>
                <div id="rating_group_1" class="rating_group" >
                    <div class="rating_star"> <input id="rating_group_1_choose" class="ratingRadio" type="radio" name="group" value="1" /></div>
                    <div id="rating_group_1_1" class="rating_star rating_star_full"></div>
                    <div id="rating_group_1_2" class="rating_star rating_star_empty "></div>
                    <div id="rating_group_1_3" class="rating_star rating_star_empty"></div>
                    <div id="rating_group_1_4" class="rating_star rating_star_empty"></div>
                    <div id="rating_group_1_5" class="rating_star rating_star_empty"></div>
                    <div style="padding-top:5px;"> 1 star </div>
                </div>
                <div id="rating_group_2" class="rating_group" >
                    <div class="rating_star"> <input id="rating_group_2_choose" class="ratingRadio" type="radio" name="group" value="2" /></div>
                    <div id="rating_group_2_1" class="rating_star rating_star_full"></div>
                    <div id="rating_group_2_2" class="rating_star rating_star_full "></div>
                    <div id="rating_group_2_3" class="rating_star rating_star_empty"></div>
                    <div id="rating_group_2_4" class="rating_star rating_star_empty"></div>
                    <div id="rating_group_2_5" class="rating_star rating_star_empty"></div>
                    <div style="padding-top:5px;"> 2 stars </div>
                </div>
                <div id="rating_group_3" class="rating_group" >
                    <div class="rating_star"> <input id="rating_group_3_choose" class="ratingRadio" type="radio" name="group" value="3" /></div>
                    <div id="rating_group_3_1" class="rating_star rating_star_full"></div>
                    <div id="rating_group_3_2" class="rating_star rating_star_full "></div>
                    <div id="rating_group_3_3" class="rating_star rating_star_full"></div>
                    <div id="rating_group_3_4" class="rating_star rating_star_empty"></div>
                    <div id="rating_group_3_5" class="rating_star rating_star_empty"></div>
                    <div style="padding-top:5px;"> 3 stars </div>
                </div>
                <div id="rating_group_4" class="rating_group" >
                    <div class="rating_star"> <input id="rating_group_4_choose"  class="ratingRadio" type="radio" name="group" value="4" /></div>
                    <div id="rating_group_4_1" class="rating_star rating_star_full"></div>
                    <div id="rating_group_4_2" class="rating_star rating_star_full "></div>
                    <div id="rating_group_4_3" class="rating_star rating_star_full"></div>
                    <div id="rating_group_4_4" class="rating_star rating_star_full"></div>
                    <div id="rating_group_4_5" class="rating_star rating_star_empty"></div>
                    <div style="padding-top:5px;"> 4 stars </div>
                </div>
                <div id="rating_group_5" class="rating_group" >
                    <div class="rating_star"> <input id="rating_group_5_choose"  class="ratingRadio" type="radio" name="group" value="5" /></div>
                    <div id="rating_group_5_1" class="rating_star rating_star_full"></div>
                    <div id="rating_group_5_2" class="rating_star rating_star_full "></div>
                    <div id="rating_group_5_3" class="rating_star rating_star_full"></div>
                    <div id="rating_group_5_4" class="rating_star rating_star_full"></div>
                    <div id="rating_group_5_5" class="rating_star rating_star_full"></div>
                    <div style="padding-top:5px;"> 5 stars </div>
                </div>
                <div class="rating_group" >
                    <input id="postRating" class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>accept.png" value="postRating" alt="<spring:message code="abstract.post.caption"/>" title="<spring:message code="abstract.post.caption"/>" />
                    <div id="radio_button_error" class="error hidden"> <spring:message code="publication_abstract_rating_validation"/> </div>
                    <div class="hidden">
                            <input type="text" name="backAddress" value="${login.backAddress}"/> 
                            <input type="text" name="publicationId" value="${publication.id}"/>
                    </div>
                </div>
            </c:if>

    </form:form>         
</div>

<div class="rbox publication_abstract_comment">
    <p style="font-style: italic; font-size: 15px;">
        <b><spring:message code="publication_abstract_comment"/>&nbsp;</b> <br/>
    </p>
    <div class="item">
        <form:form method="POST"  action="${pageContext.request.contextPath}/publication/comment/add">
            <table>
                <tr>
                    <textarea id="commentTextArea" rows="5" cols="50" name="comment"></textarea>
                </tr>
                <tr class="hidden">
                    <td class="hidden">
                        <input type="text" name="backAddress" value="${login.backAddress}"/> 
                    </td>
                </tr>
                <tr class="hidden">
                    <td class="hidden">
                        <input type="text" name="publicationId" value="${publication.id}"/>
                    </td>
                </tr>
                <c:if test="${login.lastLoginStatus=='ok'}">
                    <tr>
                        <td>
                            <input id="post" class="small_button_icon keepScrollPosition" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>accept.png" value="post" alt="<spring:message code="abstract.post.caption"/>" title="<spring:message code="abstract.post.caption"/>" />
                        </td>
                    </tr>
                </c:if>
                <c:if test="${login.lastLoginStatus!='ok'}">
                    <tr id="noteForComment" class="hidden">
                        <td>
                            <b class="error"><spring:message code="publication_abstract_comment_sign_in"/>&nbsp;</b>
                        </td>
                    </tr>
                </c:if>
            </table>
        </form:form>
    </div>
</div>

<div id="publication_abstract_comment_number" class="rbox">
    <p>
        <b><spring:message code="publication_abstract_comment_number"/>&nbsp; (<c:out value="${publication.countOfComments}"/>)  </b>
    </p>
</div>

<div>
    <c:forEach items="${publication.comments}" var="comment">

        <c:url var="deleteUrl" value="/publication/comment/delete">
            <c:param name="id" value="${comment.id}" />
            <c:param name="backAddress" value="${login.backAddress}" />
            <c:param name="publicationId" value="${publication.id}" />
        </c:url> 

        <div id="tr_${comment.id}" class="publication_abstract_each_comment">
            <c:if test="${login.lastLoginStatus=='ok'}">
                <c:if test="${comment.user.id==user.id}">
                    <a id="td_${comment.id}" class="right keepScrollPosition" 
                       onclick="return confirmdelete('${comment.id}','<spring:message code="delete.confirmation.title"/>'
                            ,'<spring:message code="delete.confirmation.message"/>','<spring:message code="delete.confirmation.accept"/>',
                            '<spring:message code="delete.confirmation.deny"/>');" 
                       href="#" rev='<c:out value="${deleteUrl}"/>' title='<spring:message code="abstract.delete.comment.button"/>' ><img width="30px" height="30px" src="${pageContext.request.contextPath}/<spring:theme code="image"/>red_delete.jpg" alt="<spring:message code="abstract.delete.comment.button"/>" /></a> 
                </c:if>
            </c:if>
            <c:out value="${comment.text}"/> 
        </div>

    </c:forEach>
</div>



