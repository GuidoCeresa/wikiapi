
<%@ page import="it.algos.wikiapi.Wiki" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wiki.label', default: 'Wiki')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-wiki" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-wiki" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list wiki">
			
				<g:if test="${wikiInstance?.pageid}">
				<li class="fieldcontain">
					<span id="pageid-label" class="property-label"><g:message code="wiki.pageid.label" default="Pageid" /></span>
					
						<span class="property-value" aria-labelledby="pageid-label"><g:fieldValue bean="${wikiInstance}" field="pageid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.ns}">
				<li class="fieldcontain">
					<span id="ns-label" class="property-label"><g:message code="wiki.ns.label" default="Ns" /></span>
					
						<span class="property-value" aria-labelledby="ns-label"><g:fieldValue bean="${wikiInstance}" field="ns"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="wiki.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${wikiInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.contentmodel}">
				<li class="fieldcontain">
					<span id="contentmodel-label" class="property-label"><g:message code="wiki.contentmodel.label" default="Contentmodel" /></span>
					
						<span class="property-value" aria-labelledby="contentmodel-label"><g:fieldValue bean="${wikiInstance}" field="contentmodel"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.pagelanguage}">
				<li class="fieldcontain">
					<span id="pagelanguage-label" class="property-label"><g:message code="wiki.pagelanguage.label" default="Pagelanguage" /></span>
					
						<span class="property-value" aria-labelledby="pagelanguage-label"><g:fieldValue bean="${wikiInstance}" field="pagelanguage"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.touched}">
				<li class="fieldcontain">
					<span id="touched-label" class="property-label"><g:message code="wiki.touched.label" default="Touched" /></span>
					
						<span class="property-value" aria-labelledby="touched-label"><g:formatDate date="${wikiInstance?.touched}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.lastrevid}">
				<li class="fieldcontain">
					<span id="lastrevid-label" class="property-label"><g:message code="wiki.lastrevid.label" default="Lastrevid" /></span>
					
						<span class="property-value" aria-labelledby="lastrevid-label"><g:fieldValue bean="${wikiInstance}" field="lastrevid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.length}">
				<li class="fieldcontain">
					<span id="length-label" class="property-label"><g:message code="wiki.length.label" default="Length" /></span>
					
						<span class="property-value" aria-labelledby="length-label"><g:fieldValue bean="${wikiInstance}" field="length"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.revid}">
				<li class="fieldcontain">
					<span id="revid-label" class="property-label"><g:message code="wiki.revid.label" default="Revid" /></span>
					
						<span class="property-value" aria-labelledby="revid-label"><g:fieldValue bean="${wikiInstance}" field="revid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.parentid}">
				<li class="fieldcontain">
					<span id="parentid-label" class="property-label"><g:message code="wiki.parentid.label" default="Parentid" /></span>
					
						<span class="property-value" aria-labelledby="parentid-label"><g:fieldValue bean="${wikiInstance}" field="parentid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="wiki.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:fieldValue bean="${wikiInstance}" field="user"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.userid}">
				<li class="fieldcontain">
					<span id="userid-label" class="property-label"><g:message code="wiki.userid.label" default="Userid" /></span>
					
						<span class="property-value" aria-labelledby="userid-label"><g:fieldValue bean="${wikiInstance}" field="userid"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.timestamp}">
				<li class="fieldcontain">
					<span id="timestamp-label" class="property-label"><g:message code="wiki.timestamp.label" default="Timestamp" /></span>
					
						<span class="property-value" aria-labelledby="timestamp-label"><g:formatDate date="${wikiInstance?.timestamp}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.size}">
				<li class="fieldcontain">
					<span id="size-label" class="property-label"><g:message code="wiki.size.label" default="Size" /></span>
					
						<span class="property-value" aria-labelledby="size-label"><g:fieldValue bean="${wikiInstance}" field="size"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.comment}">
				<li class="fieldcontain">
					<span id="comment-label" class="property-label"><g:message code="wiki.comment.label" default="Comment" /></span>
					
						<span class="property-value" aria-labelledby="comment-label"><g:fieldValue bean="${wikiInstance}" field="comment"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.contentformat}">
				<li class="fieldcontain">
					<span id="contentformat-label" class="property-label"><g:message code="wiki.contentformat.label" default="Contentformat" /></span>
					
						<span class="property-value" aria-labelledby="contentformat-label"><g:fieldValue bean="${wikiInstance}" field="contentformat"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.csrftoken}">
				<li class="fieldcontain">
					<span id="csrftoken-label" class="property-label"><g:message code="wiki.csrftoken.label" default="Csrftoken" /></span>
					
						<span class="property-value" aria-labelledby="csrftoken-label"><g:fieldValue bean="${wikiInstance}" field="csrftoken"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.starttimestamp}">
				<li class="fieldcontain">
					<span id="starttimestamp-label" class="property-label"><g:message code="wiki.starttimestamp.label" default="Starttimestamp" /></span>
					
						<span class="property-value" aria-labelledby="starttimestamp-label"><g:formatDate date="${wikiInstance?.starttimestamp}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.ultimaLettura}">
				<li class="fieldcontain">
					<span id="ultimaLettura-label" class="property-label"><g:message code="wiki.ultimaLettura.label" default="Ultima Lettura" /></span>
					
						<span class="property-value" aria-labelledby="ultimaLettura-label"><g:formatDate date="${wikiInstance?.ultimaLettura}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${wikiInstance?.ultimaScrittura}">
				<li class="fieldcontain">
					<span id="ultimaScrittura-label" class="property-label"><g:message code="wiki.ultimaScrittura.label" default="Ultima Scrittura" /></span>
					
						<span class="property-value" aria-labelledby="ultimaScrittura-label"><g:formatDate date="${wikiInstance?.ultimaScrittura}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:wikiInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${wikiInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
