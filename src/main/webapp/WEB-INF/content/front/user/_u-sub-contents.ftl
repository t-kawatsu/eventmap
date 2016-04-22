<#switch contents>
	<#case 'ACTIVITY'>
	    <#include '_read-activity.ftl' />
	    <#break />
	<#case 'EVENT'>
	    <#include '_read-event.ftl' />
	    <#break/>
	<#case 'PROFILE'>
	    <#include '_read-profile.ftl' />
	    <#break />
	<#case 'FRIENDS'>
	    <#include '_read-friends.ftl' />
	    <#break />
</#switch>