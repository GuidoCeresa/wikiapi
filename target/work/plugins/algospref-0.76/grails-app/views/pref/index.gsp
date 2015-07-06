<%@ page import="it.algos.algospref.Pref" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'pref.label', default: 'Pref')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-pref" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                           default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label"
                                                              args="[entityName]"/></g:link></li>
        <g:if test="${menuExtra}">
            <algos:menuExtra menuExtra="${menuExtra}"></algos:menuExtra>
        </g:if>
    </ul>
</div>

<div id="list-pref" class="content scaffold-list" role="main">

    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.error}">
        <div class="errors" role="status">${flash.error}</div>
    </g:if>
    <g:if test="${flash.messages}">
        <g:each in="${flash.messages}" status="i" var="singoloMessaggio">
            <div class="message" role="status">${singoloMessaggio}</div>
        </g:each>
    </g:if>
    <g:if test="${flash.errors}">
        <g:each in="${flash.errors}" status="i" var="singoloErrore">
            <div class="errors" role="status">${singoloErrore}</div>
        </g:each>
    </g:if>
    <h1><g:message code="pref.list.label" args="[entityName]" default="Elenco"/></h1>

    <div class="pagination">
        <g:paginate total="${prefInstanceCount ?: 0}"/>
    </div>
    <table>
        <thead>
        <g:if test="${campiLista}">
            <algos:titoliLista campiLista="${campiLista}"></algos:titoliLista>
        </g:if>
        <g:else>
            <tr>

                <g:sortableColumn property="ordine"
                                  title="${message(code: 'pref.ordine.label', default: 'Ordine')}"/>

                <g:sortableColumn property="type"
                                  title="${message(code: 'pref.type.label', default: 'Type')}"/>

                <g:sortableColumn property="code"
                                  title="${message(code: 'pref.code.label', default: 'Code')}"/>

                <g:sortableColumn property="descrizione"
                                  title="${message(code: 'pref.descrizione.label', default: 'Descrizione')}"/>

                <g:sortableColumn property="stringa"
                                  title="${message(code: 'pref.stringa.label', default: 'Stringa')}"/>

                <g:sortableColumn property="bool"
                                  title="${message(code: 'pref.bool.label', default: 'Bool')}"/>

                <g:sortableColumn property="intero"
                                  title="${message(code: 'pref.intero.label', default: 'Intero')}"/>

                <g:sortableColumn property="lungo"
                                  title="${message(code: 'pref.lungo.label', default: 'Lungo')}"/>

                <g:sortableColumn property="reale"
                                  title="${message(code: 'pref.reale.label', default: 'Reale')}"/>

                <g:sortableColumn property="doppio"
                                  title="${message(code: 'pref.doppio.label', default: 'Doppio')}"/>

                <g:sortableColumn property="decimale"
                                  title="${message(code: 'pref.decimale.label', default: 'Decimale')}"/>

                <g:sortableColumn property="data"
                                  title="${message(code: 'pref.data.label', default: 'Data')}"/>

            </tr>
        </g:else>
        </thead>
        <tbody>
        <g:if test="${campiLista}">
            <g:each in="${prefInstanceList}" status="i" var="prefInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <algos:rigaLista campiLista="${campiLista}" rec="${prefInstance}"></algos:rigaLista>
                </tr>
            </g:each>
        </g:if>
        <g:else>
            <g:each in="${prefInstanceList}" status="i" var="prefInstance">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "ordine")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "type")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "code")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "descrizione")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "stringa")}</g:link></td>

                    <td>
                        <g:if test="${prefInstance.bool != null}">
                            <g:checkBox name="bool" value="${prefInstance.bool}"/>
                        </g:if>
                    </td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "intero")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "lungo")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "reale")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "doppio")}</g:link></td>

                    <td><g:link action="show"
                                id="${prefInstance.id}">${fieldValue(bean: prefInstance, field: "decimale")}</g:link></td>

                    <td><g:formatDate date="${prefInstance.data}"/></td>

                </tr>
            </g:each>
        </g:else>
        </tbody>
    </table>

    <div class="pagination">
        <g:paginate total="${prefInstanceCount ?: 0}"/>
    </div>
</div>
</body>
</html>
