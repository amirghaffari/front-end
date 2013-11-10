<%@ include file="/WEB-INF/pages/jsp/include.jsp" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><tiles:insertAttribute name="title" ignore="true" /></title>
<link rel="Shortcut Icon" href="${pageContext.request.contextPath}/<spring:theme code="image"/>icon.png" /> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/<spring:theme code="css"/>" type="text/css"/>
<link rel="stylesheet" href="http://www.google.com/cse/style/look/default.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/confirm/jquery.confirm.css" type="text/css" /> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/popup/popup.css" type="text/css" /> 
<link rel="stylesheet" href="${pageContext.request.contextPath}/js/tooltip/tooltip.css" type="text/css" /> 

<script src="http://www.google.com/jsapi" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery.min.js" type="text/javascript"></script> 
<script src="${pageContext.request.contextPath}/js/confirm/jquery.confirm.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/general.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/popup/popup.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/tooltip/tooltip.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jquery-latest.pack.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/jcarousellite_1.0.1c4.js" type="text/javascript"></script>


</head>
<body >
<div id="wrapper" >
    <div id="container" >
	    <div id="header" class="container">
                <div id="logo"class="inline">
                    <a href="${pageContext.request.contextPath}">
                        <img id="logoimg" src="${pageContext.request.contextPath}/<spring:theme code="image"/>logo.png" alt="" />
                    </a>
                </div>
                <div id="hwlogo" class="inline">
                    <a href="http://www.hw.ac.uk/">
                        <img id="hwlogoimg" src="${pageContext.request.contextPath}/<spring:theme code="image"/>hwlogo.png" alt="" />
                    </a> 
                </div>
                <div id="language" class="inline">
                    Select language:<br/>
                     <a href="?lang=en&theme=default">English</a> | <a href="?lang=fa&theme=persian">Farsi</a><br/>
                </div>
                <!-- <div id="banner" class="inline"><img id="slidepanel" src="${pageContext.request.contextPath}/<spring:theme code="image"/>slide1.jpg" width="627" height="150" alt="" /></div> -->
            </div>
	    <div id="menu" class="container" >
		    <ul>
                        <li><a href="<c:url value='/home'/>"><spring:message code="layout_home"/></a></li>
                        <li><a href="<c:url value='/search'/>"><spring:message code="layout_A_Search"/></a></li>
                        <li><a href="<c:url value='/statisticalview'/>"><spring:message code="layout_statisticalview"/></a></li>
                        <li><a href="<c:url value='/peerreview'/>"><spring:message code="layout_peerreview"/></a></li>
                        <li><a href="<c:url value='/about'/>"><spring:message code="layout_about"/></a></li>
		    </ul>
	    </div>
	    <div id="top-bar" class="container">
		    <div id="bar" class="container" >
			    <div id="search">
                                <form method="get" action="http://www.google.com/search" target="_blank">
                                    <fieldset>
                                        <input type="hidden" name="sitesearch" value="hw.ac.uk" />
                                        <input onBlur="if(this.value=='') this.value='Search hw.ac.uk on Google'" onfocus="if(this.value=='Search hw.ac.uk on Google') this.value=''" type="text" name="as_q" value="Search hw.ac.uk on Google" class="input-text" />
                                        <input type="submit" value="<spring:message code="layout_searchButton"/>" class="input-submit" />
                                    </fieldset>
                                </form>
                     <!--google search in current page-->
                    <!--<div id="cse" style="width: 100%;"></div>
                    <script type="text/javascript">
                        //setup_google();
                    </script>-->
                            </div>
		    </div>
	    </div>
	    <div id="page" class="container">
                <tiles:insertAttribute name="content" />
	    </div>
    </div>
    <div id="footer" >
            <p class="center">
                <a href="<c:url value='/home'/>"><spring:message code="layout_home"/></a> |
                <a href="<c:url value='/feedback'/>"><spring:message code="feedback"/></a> |
                <a href="<c:url value='/privacy'/>"><spring:message code="privacy"/></a> | 
                <a href="<c:url value='/terms'/>"><spring:message code="terms"/></a> | 
                <a href="<c:url value='/map'/>"><spring:message code="map"/></a>
            </p>
            <br />
	    <p><span class="left">&copy;Copyright 2011 <a target="value" href="http://www.hw.ac.uk/about/policies/terms-conditions.htm" title="Heriot-Watt University">Heriot-Watt University</a>. All rights reserved.</span> <span class="right"><spring:message code="Last_updated"/></span> </p>
    </div>
</div>

</body>
</html>
