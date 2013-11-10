<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>

<div id="advance_search_form" class="inputform">
    <p><h2><spring:message code="advanced_search_header"/></h2></p>
    <form:form method="POST" commandName="searchItems">
        <table id="first_table_item">
            <tr>
                <td>Apply between condition</td>
                <td>
                    <select  name="operator">
                        <option value="1">OR</option>
                        <option value="2">AND</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><spring:message code="advanced_search_all_categories"/></td>
                <td><form:input path="allCategories" /></td>
            </tr>
        </table>

        <fieldset>
        <legend><b><spring:message code="advanced_search_search_categories"/></b></legend>
            <table>
                <tr>
                    <td><spring:message code="advanced_search_authors"/></td>
                    <td><form:input path="authors" /></td>
                </tr>
                <tr>
                    <td><spring:message code="advanced_search_title"/></td>
                    <td><form:input path="title" /></td>
                </tr>
                <tr>
                    <td><spring:message code="advanced_search_abstract"/></td>
                    <td><form:input path="abstracts" /></td>
                </tr>
                <tr>
                    <td><spring:message code="advanced_search_keywords"/></td>
                    <td><form:input path="keywords" /></td>
                </tr>
            </table>
        </fieldset>

        <fieldset>
        <legend><b><spring:message code="advanced_search_publish_date"/></b></legend>
            <table>
                <tr>
                    <td>
                        <table class="subTable">
                            <tr>
                                <td> <spring:message code="advanced_search_fromdate"/> </td>
                                <td> 
                                    <table class="subTable">
                                        <tr><td><spring:message code="advanced_search_year"/></td></tr>
                                        <tr><td><spring:message code="advanced_search_month"/></td></tr>
                                    </table>
                                </td>
                            </tr>
                        </table>

                    </td>
                    <td>
                        <table class="subTable">
                            <tr><td><form:input class="numeric" path="fromDateYear" /></td></tr>
                            <tr><td><form:select path="fromDateMonth" items="${months}"/></td></tr>
                        </table>
                    </td>
                </tr>
            </table>
            <div class="right"> 
                <span class="error"> <form:errors path="fromDateYear" /></span><br/>
                <span class="error"> <form:errors path="untilDateYear" /></span><br/>
            </div>
            <table>
                <tr>
                    <td>
                        <table class="subTable">
                            <tr>
                                <td> <spring:message code="advanced_search_untildate"/> </td>
                                <td> 
                                    <table class="subTable">
                                        <tr><td><spring:message code="advanced_search_year"/></td></tr>
                                        <tr><td><spring:message code="advanced_search_month"/></td></tr>
                                    </table>
                                </td>
                            </tr>
                        </table>

                    </td>
                    <td>
                        <table class="subTable">
                            <tr><td><form:input class="numeric" path="untilDateYear" /></td></tr>
                            <tr><td><form:select path="untilDateMonth" items="${months}"/></td></tr>
                        </table>
                    </td>
                </tr>
            </table>
        </fieldset>
        <div style="width:100%;">
            <div style="margin: 0 auto; width: 70px; height: 50px;">
                <input id="advanced_search_icon" type="image" src="${pageContext.request.contextPath}/<spring:theme code="image"/>search.png" value="Submit" alt="Start Search" title="<spring:message code="advanced_search_submit_title"/>" />
            </div>
        </div>  
    </form:form>

</div>
