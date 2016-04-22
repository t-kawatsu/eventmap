<div id="u-tw-private-state" class="clearfix">
	<#if user.isTwitterUser()>
	<div class="fl bold" style="padding-right:18px;"><#if user.twUser.isPrivate>非公開<#else>公開</#if></div>
	<a href="${url('/tw-user/update-private-ajax')}#u-tw-private-state" class="ajax-get"><#if user.twUser.isPrivate>公開する<#else>非公開にする</#if></a>
	<#else>
	<a class="btn-tw btn-large icon-twitter-sign" href="${url('/tw-user/marge')}">アカウントを登録する</a>
	</#if>
</div>