<%@ page import="test.NewDomainClass" %>



<div class="fieldcontain ${hasErrors(bean: newDomainClassInstance, field: 'name', 'error')} required">
	<label for="name">
		<g:message code="newDomainClass.name.label" default="Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="name" required="" value="${newDomainClassInstance?.name}"/>

</div>

