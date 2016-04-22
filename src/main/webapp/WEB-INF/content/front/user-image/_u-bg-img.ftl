<div id="u-bgimg" class="u-bgimg b-shadow">
	<@my.userBgImg user "m" />
	<ul class="u-img-update-menu hide clearfix">
	<#if user.userBgImageId?? >
		<li class="fl c-btn-wrap"><a href="${url('/user-image/create-bg-ajax')}" class="btn-a btn-ssmall modal">編集</a></li>
		<li class="fl c-btn-wrap pl-4"><a href="${url('/user-image/delete-bg-ajax/' + user.userBgImageId?c)}#u-bgimg" class="btn-a btn-ssmall ajax-get">削除</a></li>
	<#else>
		<li class="fl c-btn-wrap"><a href="${url('/user-image/create-bg-ajax')}" class="btn-a btn-ssmall modal">作成</a></li>
	</#if>
	</ul>
</div>