<title>ユーザー情報編集</title>

<link href="${assets('user.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" >

<section class="clearfix">
  <h1 class="c-pt pt-12">ユーザー情報編集</h1>
  <form action="${url('/user/update', 'true', 'true')}" method="POST">
    <div class="clearfix">
    <div class="clmn-left-main">
      <#include '_update-nickname.ftl' /> 
 	  <#include '_update-message.ftl' />
 	  <#include '_update-mail-address.ftl' />
 	  <#include '_update-password.ftl' />
 	  <#include '_update-residence.ftl' />
 	  <#include '_update-native.ftl' />
    </div>
    <div class="clmn-right-menu">
	  <#include '_update-sex.ftl' />
	  <#include '_update-mail-notice-frequency.ftl' />
      <dl class="input-item clearfix">
	      <dt class="input-item-name">Facebookアカウント</dt>
	      <dd><#include '../fb-user/_btn-private.ftl' /></dd>
      </dl>   
	  <dl class="input-item clearfix">
	      <dt class="input-item-name">Twitterアカウント</dt>
	      <dd><#include '../tw-user/_btn-private.ftl' /></dd>
	  </dl>
    </div>
    </div>
	<dl class="input-item clearfix">
		<dt>&nbsp;</dt>
		<dd><a class="btn-b btn-large" href="${url('/user/read/' + currentUser.id?c)}">マイページに戻る</a></dd>
	</dl>
  </form>
</section>
