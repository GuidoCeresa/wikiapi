
<%@ page import="it.algos.wikiapi.WikiBio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wikiBio.label', default: 'WikiBio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wikiBio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wikiBio" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wikiBio">
			
				<g:if test="${wikiBioInstance?.pageid}">
				<li class="fieldcontain">
					<span id="pageid-label" class="property-label"><g:message code="wikiBio.pageid.label" default="Pageid" /></span>
					
						<span class="property-value" aria-labelledby="pageid-label"><g:fieldValue bean="${wikiBioInstance}" field="pageid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.ns}">
				<li class="fieldcontain">
					<span id="ns-label" class="property-label"><g:message code="wikiBio.ns.label" default="Ns" /></span>
					
						<span class="property-value" aria-labelledby="ns-label"><g:fieldValue bean="${wikiBioInstance}" field="ns"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="wikiBio.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${wikiBioInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.contentmodel}">
				<li class="fieldcontain">
					<span id="contentmodel-label" class="property-label"><g:message code="wikiBio.contentmodel.label" default="Contentmodel" /></span>
					
						<span class="property-value" aria-labelledby="contentmodel-label"><g:fieldValue bean="${wikiBioInstance}" field="contentmodel"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.pagelanguage}">
				<li class="fieldcontain">
					<span id="pagelanguage-label" class="property-label"><g:message code="wikiBio.pagelanguage.label" default="Pagelanguage" /></span>
					
						<span class="property-value" aria-labelledby="pagelanguage-label"><g:fieldValue bean="${wikiBioInstance}" field="pagelanguage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.touched}">
				<li class="fieldcontain">
					<span id="touched-label" class="property-label"><g:message code="wikiBio.touched.label" default="Touched" /></span>
					
						<span class="property-value" aria-labelledby="touched-label"><g:formatDate date="${wikiBioInstance?.touched}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.lastrevid}">
				<li class="fieldcontain">
					<span id="lastrevid-label" class="property-label"><g:message code="wikiBio.lastrevid.label" default="Lastrevid" /></span>
					
						<span class="property-value" aria-labelledby="lastrevid-label"><g:fieldValue bean="${wikiBioInstance}" field="lastrevid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.length}">
				<li class="fieldcontain">
					<span id="length-label" class="property-label"><g:message code="wikiBio.length.label" default="Length" /></span>
					
						<span class="property-value" aria-labelledby="length-label"><g:fieldValue bean="${wikiBioInstance}" field="length"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.revid}">
				<li class="fieldcontain">
					<span id="revid-label" class="property-label"><g:message code="wikiBio.revid.label" default="Revid" /></span>
					
						<span class="property-value" aria-labelledby="revid-label"><g:fieldValue bean="${wikiBioInstance}" field="revid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.parentid}">
				<li class="fieldcontain">
					<span id="parentid-label" class="property-label"><g:message code="wikiBio.parentid.label" default="Parentid" /></span>
					
						<span class="property-value" aria-labelledby="parentid-label"><g:fieldValue bean="${wikiBioInstance}" field="parentid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="wikiBio.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${wikiBioInstance}" field="user"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.userid}">
				<li class="fieldcontain">
					<span id="userid-label" class="property-label"><g:message code="wikiBio.userid.label" default="Userid" /></span>
					
						<span class="property-value" aria-labelledby="userid-label"><g:fieldValue bean="${wikiBioInstance}" field="userid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.timestamp}">
				<li class="fieldcontain">
					<span id="timestamp-label" class="property-label"><g:message code="wikiBio.timestamp.label" default="Timestamp" /></span>
					
						<span class="property-value" aria-labelledby="timestamp-label"><g:formatDate date="${wikiBioInstance?.timestamp}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="wikiBio.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${wikiBioInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="wikiBio.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${wikiBioInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.contentformat}">
				<li class="fieldcontain">
					<span id="contentformat-label" class="property-label"><g:message code="wikiBio.contentformat.label" default="Contentformat" /></span>
					
						<span class="property-value" aria-labelledby="contentformat-label"><g:fieldValue bean="${wikiBioInstance}" field="contentformat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.csrftoken}">
				<li class="fieldcontain">
					<span id="csrftoken-label" class="property-label"><g:message code="wikiBio.csrftoken.label" default="Csrftoken" /></span>
					
						<span class="property-value" aria-labelledby="csrftoken-label"><g:fieldValue bean="${wikiBioInstance}" field="csrftoken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.starttimestamp}">
				<li class="fieldcontain">
					<span id="starttimestamp-label" class="property-label"><g:message code="wikiBio.starttimestamp.label" default="Starttimestamp" /></span>
					
						<span class="property-value" aria-labelledby="starttimestamp-label"><g:formatDate date="${wikiBioInstance?.starttimestamp}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.ultimaLettura}">
				<li class="fieldcontain">
					<span id="ultimaLettura-label" class="property-label"><g:message code="wikiBio.ultimaLettura.label" default="Ultima Lettura" /></span>
					
						<span class="property-value" aria-labelledby="ultimaLettura-label"><g:fieldValue bean="${wikiBioInstance}" field="ultimaLettura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.ultimaScrittura}">
				<li class="fieldcontain">
					<span id="ultimaScrittura-label" class="property-label"><g:message code="wikiBio.ultimaScrittura.label" default="Ultima Scrittura" /></span>
					
						<span class="property-value" aria-labelledby="ultimaScrittura-label"><g:fieldValue bean="${wikiBioInstance}" field="ultimaScrittura"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiBioInstance?.tmplBio}">
				<li class="fieldcontain">
					<span id="tmplBio-label" class="property-label"><g:message code="wikiBio.tmplBio.label" default="Tmpl Bio" /></span>
					
						<span class="property-value" aria-labelledby="tmplBio-label"><g:fieldValue bean="${wikiBioInstance}" field="tmplBio"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:wikiBioInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${wikiBioInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
