<%@ page import="it.algos.wikiapi.Wiki" %>



<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'pageid', 'error')} required">
	<label for="pageid">
		<g:message code="wiki.pageid.label" default="Pageid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pageid" type="number" value="${wikiInstance.pageid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'ns', 'error')} required">
	<label for="ns">
		<g:message code="wiki.ns.label" default="Ns" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ns" type="number" value="${wikiInstance.ns}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="wiki.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${wikiInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'contentmodel', 'error')} required">
	<label for="contentmodel">
		<g:message code="wiki.contentmodel.label" default="Contentmodel" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="contentmodel" required="" value="${wikiInstance?.contentmodel}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'pagelanguage', 'error')} required">
	<label for="pagelanguage">
		<g:message code="wiki.pagelanguage.label" default="Pagelanguage" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="pagelanguage" required="" value="${wikiInstance?.pagelanguage}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'touched', 'error')} required">
	<label for="touched">
		<g:message code="wiki.touched.label" default="Touched" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="touched" precision="day"  value="${wikiInstance?.touched}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'lastrevid', 'error')} required">
	<label for="lastrevid">
		<g:message code="wiki.lastrevid.label" default="Lastrevid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lastrevid" type="number" value="${wikiInstance.lastrevid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'length', 'error')} required">
	<label for="length">
		<g:message code="wiki.length.label" default="Length" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="length" type="number" value="${wikiInstance.length}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'revid', 'error')} required">
	<label for="revid">
		<g:message code="wiki.revid.label" default="Revid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="revid" type="number" value="${wikiInstance.revid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'parentid', 'error')} required">
	<label for="parentid">
		<g:message code="wiki.parentid.label" default="Parentid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="parentid" type="number" value="${wikiInstance.parentid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'user', 'error')} required">
	<label for="user">
		<g:message code="wiki.user.label" default="User" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="user" required="" value="${wikiInstance?.user}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'userid', 'error')} required">
	<label for="userid">
		<g:message code="wiki.userid.label" default="Userid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="userid" type="number" value="${wikiInstance.userid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'timestamp', 'error')} required">
	<label for="timestamp">
		<g:message code="wiki.timestamp.label" default="Timestamp" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="timestamp" precision="day"  value="${wikiInstance?.timestamp}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="wiki.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" type="number" value="${wikiInstance.size}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'comment', 'error')} required">
	<label for="comment">
		<g:message code="wiki.comment.label" default="Comment" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="comment" required="" value="${wikiInstance?.comment}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'contentformat', 'error')} required">
	<label for="contentformat">
		<g:message code="wiki.contentformat.label" default="Contentformat" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="contentformat" required="" value="${wikiInstance?.contentformat}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'csrftoken', 'error')} ">
	<label for="csrftoken">
		<g:message code="wiki.csrftoken.label" default="Csrftoken" />
		
	</label>
	<g:textField name="csrftoken" value="${wikiInstance?.csrftoken}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'starttimestamp', 'error')} ">
	<label for="starttimestamp">
		<g:message code="wiki.starttimestamp.label" default="Starttimestamp" />
		
	</label>
	<g:datePicker name="starttimestamp" precision="day"  value="${wikiInstance?.starttimestamp}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'ultimaLettura', 'error')} required">
	<label for="ultimaLettura">
		<g:message code="wiki.ultimaLettura.label" default="Ultima Lettura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="ultimaLettura" precision="day"  value="${wikiInstance?.ultimaLettura}"  />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiInstance, field: 'ultimaScrittura', 'error')} required">
	<label for="ultimaScrittura">
		<g:message code="wiki.ultimaScrittura.label" default="Ultima Scrittura" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="ultimaScrittura" precision="day"  value="${wikiInstance?.ultimaScrittura}"  />

</div>

