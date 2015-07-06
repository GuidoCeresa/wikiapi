
<%@ page import="it.algos.wikiapi.Wiki" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'wiki.label', default: 'Wiki')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-wiki" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-wiki" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="pageid" title="${message(code: 'wiki.pageid.label', default: 'Pageid')}" />
					
						<g:sortableColumn property="ns" title="${message(code: 'wiki.ns.label', default: 'Ns')}" />
					
						<g:sortableColumn property="title" title="${message(code: 'wiki.title.label', default: 'Title')}" />
					
						<g:sortableColumn property="contentmodel" title="${message(code: 'wiki.contentmodel.label', default: 'Contentmodel')}" />
					
						<g:sortableColumn property="pagelanguage" title="${message(code: 'wiki.pagelanguage.label', default: 'Pagelanguage')}" />
					
						<g:sortableColumn property="touched" title="${message(code: 'wiki.touched.label', default: 'Touched')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${wikiInstanceList}" status="i" var="wikiInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${wikiInstance.id}">${fieldValue(bean: wikiInstance, field: "pageid")}</g:link></td>
					
						<td>${fieldValue(bean: wikiInstance, field: "ns")}</td>
					
						<td>${fieldValue(bean: wikiInstance, field: "title")}</td>
					
						<td>${fieldValue(bean: wikiInstance, field: "contentmodel")}</td>
					
						<td>${fieldValue(bean: wikiInstance, field: "pagelanguage")}</td>
					
						<td><g:formatDate date="${wikiInstance.touched}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${wikiInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
