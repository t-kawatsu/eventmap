<title>パスワード送信</title>

<link href="${assets('user.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" >

<section class="clearfix">
  <div class="clmn-left-main">
    <h1 class="c-pt icon-key">パスワード送信</h1>
    <p class="pb-6">
      ご登録いただいたメールアドレス宛にパスワードを記載し送信いたします。<br />
      なお、パスワードの変更はログイン後、パスワード変更画面から出来ます。
    </p>
    <form method="post" action="${url('/user/send-password', 'true', 'true')}">
	  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'sendPasswordForm.mailAddress'/>">
	    <dt>
	      <span class="input-item-name"><label for="sendPasswordForm_mailAddress">メールアドレス</label></span>
	      <span class="input-item-d">※会員登録の際に登録されたメールアドレスを入力して下さい。</span>
	    </dt>
	    <dd>
	  	  <@s.textfield name="sendPasswordForm.mailAddress" cssClass="form-text input-shadow" autocomplete="off" placeholder="メールアドレス" maxlength="100" size="40" />
		  <@s.fielderror><@s.param value="%{'sendPasswordForm.mailAddress'}" /></@s.fielderror> 
	    </dd>
	  </dl>
	  <dl class="input-item clearfix">
	    <dt>&nbsp;<@s.token /></dt>
	    <dd><@s.submit value="送信" cssClass="btn-b btn-large form-submit" /></dd>
	  </dl>
    </form>
  </div>
</section>