
<%@ page import="it.algos.wikiapi.WikiBio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wikiBio.label', default: 'WikiBio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-wikiBio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wikiBio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="pageid" title="${message(code: 'wikiBio.pageid.label', default: 'Pageid')}" />

						<g:sortableColumn property="title" title="${message(code: 'wikiBio.title.label', default: 'Title')}" />
						<g:sortableColumn property="ultimaLettura" title="${message(code: 'wikiBio.title.label', default: 'Letto')}" />
						<g:sortableColumn property="size" title="${message(code: 'wikiBio.title.label', default: 'Size')}" />
						<g:sortableColumn property="user" title="${message(code: 'wikiBio.title.label', default: 'Last')}" />
						<g:sortableColumn property="comment" title="${message(code: 'wikiBio.title.label', default: 'Comment')}" />

					</tr>
				</thead>
				<tbody>
				<g:each in="${wikiBioInstanceList}" status="i" var="wikiBioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${wikiBioInstance.id}">${fieldValue(bean: wikiBioInstance, field: "pageid")}</g:link></td>

						<td><g:link action="show" id="${wikiBioInstance.id}">${fieldValue(bean: wikiBioInstance, field: "title")}</g:link></td>

						<td><g:link action="show" id="${wikiBioInstance.id}"><g:formatDate date="${wikiBioInstance.ultimaLettura}" /></g:link></td>
						<td><g:link action="show" id="${wikiBioInstance.id}">${fieldValue(bean: wikiBioInstance, field: "size")}</g:link></td>
						<td><g:link action="show" id="${wikiBioInstance.id}">${fieldValue(bean: wikiBioInstance, field: "user")}</g:link></td>
						<td><g:link action="show" id="${wikiBioInstance.id}">${fieldValue(bean: wikiBioInstance, field: "comment")}</g:link></td>

					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${wikiBioInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
