<div id="u-fb-private-state" class="clearfix">
	<#if user.isFacebookUser()>
	<div class="fl bold" style="padding-right:18px;"><#if user.fbUser.isPrivate>非公開<#else>公開</#if></div>
	<a href="${url('/fb-user/update-private-ajax')}#u-fb-private-state" class="ajax-get"><#if user.fbUser.isPrivate>公開する<#else>非公開にする</#if></a>
	<#else>
	<a class="btn-fb btn-large icon-facebook-sign" href="${url('/fb-user/merge')}">アカウントを登録する</a>
	</#if>
</div>