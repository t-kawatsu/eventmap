<title>${user.nickname}</title>

<link href="${assets('user.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" >

<div class="clmn-left-menu">
  <nav class="mb-14 c-btn-wrap">
    <ul class="u-menu-category">
      <li><a href="${url('/user/read/'+user.id?c+'/profile')}" class="js-pjax">プロフィールを見る</a></li>
      <li><a href="${url('/user/read/'+user.id?c+'/activity')}" class="js-pjax">アクティビティ</a></li>
      <li><a href="${url('/user/read/'+user.id?c+'/friends')}" class="js-pjax">友達</a></li>
    </ul>
  </nav>
  <nav class="mb-14 c-btn-wrap">
    <ul class="u-menu-category">
      <li><a href="#">参加予定のイベント</a></li>
      <li><a href="#">過去のイベント</a></li>
      <li><a href="#">お気に入りのイベント</a></li>
      <li><a href="${url('/event/create')}">イベントを登録する</a></li>
    </ul>
  </nav>
  <#if !isMyPage >
  <nav class="mb-14 c-btn-wrap">
    <ul class="u-menu-category">
      <li><#include "../user-friend/_btn-friend-state.ftl" /></li>
    </ul>
  </nav>
  <nav class="mb-14 c-btn-wrap">
    <ul class="u-menu-category">
      <li><a href="#">メッセージを送る</a></li>
    </ul>
  </nav>
  </#if>
</div>

<div class="clmn-right-main">
  <div class="u-sub-contents js-pjax-con">
	<#include '_u-sub-contents.ftl' />
  </div>
</div>
