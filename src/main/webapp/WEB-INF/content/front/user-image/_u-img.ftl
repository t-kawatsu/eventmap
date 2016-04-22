<div id="u-img" class="u-img b-shadow radius-3">
  <#if user.userImageId?? >
    <a href="${userImageSrc(user.id?c, user.userImageId?c, "l")}" class="image-modal"><@my.userImg user "m" /></a>
  	<ul class="u-img-update-menu hide clearfix">
  	  <li class="c-btn-wrap"><a href="${url('/user-image/create-ajax')}" class="btn-a btn-ssmall modal">編集</a></li>
  	  <li class="c-btn-wrap mt-14"><a href="${url('/user-image/delete-ajax/' + user.userImageId?c)}#u-img" class="btn-a btn-ssmall ajax-get">削除</a></li>
  	</ul>
  <#else>
    <@my.userImg user "m" />
  	<ul class="u-img-update-menu hide clearfix">
  	  <li class="fl c-btn-wrap"><a href="${url('/user-image/create-ajax')}" class="btn-a btn-ssmall modal">作成</a></li>
  	</ul>
  </#if>
</div>