<%@ page import="it.algos.wikiapi.WikiBio" %>



<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'pageid', 'error')} required">
	<label for="pageid">
		<g:message code="wikiBio.pageid.label" default="Pageid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="pageid" type="number" value="${wikiBioInstance.pageid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'ns', 'error')} required">
	<label for="ns">
		<g:message code="wikiBio.ns.label" default="Ns" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="ns" type="number" value="${wikiBioInstance.ns}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="wikiBio.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${wikiBioInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'contentmodel', 'error')} ">
	<label for="contentmodel">
		<g:message code="wikiBio.contentmodel.label" default="Contentmodel" />
		
	</label>
	<g:textField name="contentmodel" value="${wikiBioInstance?.contentmodel}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'pagelanguage', 'error')} ">
	<label for="pagelanguage">
		<g:message code="wikiBio.pagelanguage.label" default="Pagelanguage" />
		
	</label>
	<g:textField name="pagelanguage" value="${wikiBioInstance?.pagelanguage}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'touched', 'error')} ">
	<label for="touched">
		<g:message code="wikiBio.touched.label" default="Touched" />
		
	</label>
	<g:datePicker name="touched" precision="day"  value="${wikiBioInstance?.touched}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'lastrevid', 'error')} required">
	<label for="lastrevid">
		<g:message code="wikiBio.lastrevid.label" default="Lastrevid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="lastrevid" type="number" value="${wikiBioInstance.lastrevid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'length', 'error')} required">
	<label for="length">
		<g:message code="wikiBio.length.label" default="Length" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="length" type="number" value="${wikiBioInstance.length}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'revid', 'error')} required">
	<label for="revid">
		<g:message code="wikiBio.revid.label" default="Revid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="revid" type="number" value="${wikiBioInstance.revid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'parentid', 'error')} required">
	<label for="parentid">
		<g:message code="wikiBio.parentid.label" default="Parentid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="parentid" type="number" value="${wikiBioInstance.parentid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'user', 'error')} ">
	<label for="user">
		<g:message code="wikiBio.user.label" default="User" />
		
	</label>
	<g:textField name="user" value="${wikiBioInstance?.user}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'userid', 'error')} required">
	<label for="userid">
		<g:message code="wikiBio.userid.label" default="Userid" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="userid" type="number" value="${wikiBioInstance.userid}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'timestamp', 'error')} ">
	<label for="timestamp">
		<g:message code="wikiBio.timestamp.label" default="Timestamp" />
		
	</label>
	<g:datePicker name="timestamp" precision="day"  value="${wikiBioInstance?.timestamp}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'size', 'error')} required">
	<label for="size">
		<g:message code="wikiBio.size.label" default="Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="size" type="number" value="${wikiBioInstance.size}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'comment', 'error')} ">
	<label for="comment">
		<g:message code="wikiBio.comment.label" default="Comment" />
		
	</label>
	<g:textField name="comment" value="${wikiBioInstance?.comment}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'contentformat', 'error')} ">
	<label for="contentformat">
		<g:message code="wikiBio.contentformat.label" default="Contentformat" />
		
	</label>
	<g:textField name="contentformat" value="${wikiBioInstance?.contentformat}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'csrftoken', 'error')} ">
	<label for="csrftoken">
		<g:message code="wikiBio.csrftoken.label" default="Csrftoken" />
		
	</label>
	<g:textField name="csrftoken" value="${wikiBioInstance?.csrftoken}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'starttimestamp', 'error')} ">
	<label for="starttimestamp">
		<g:message code="wikiBio.starttimestamp.label" default="Starttimestamp" />
		
	</label>
	<g:datePicker name="starttimestamp" precision="day"  value="${wikiBioInstance?.starttimestamp}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'ultimaLettura', 'error')} ">
	<label for="ultimaLettura">
		<g:message code="wikiBio.ultimaLettura.label" default="Ultima Lettura" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'ultimaScrittura', 'error')} ">
	<label for="ultimaScrittura">
		<g:message code="wikiBio.ultimaScrittura.label" default="Ultima Scrittura" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: wikiBioInstance, field: 'tmplBio', 'error')} required">
	<label for="tmplBio">
		<g:message code="wikiBio.tmplBio.label" default="Tmpl Bio" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="tmplBio" required="" value="${wikiBioInstance?.tmplBio}"/>

</div>

