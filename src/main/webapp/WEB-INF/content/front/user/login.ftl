<title>ログイン</title>

<link href="${assets('user.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" />

<section class="clearfix">
    <div class="clmn-left-main">
      <h1 class="c-pt">ログイン</h1>
	  <form class="u-create-form" action="${url('/user/login')}" method="POST">
		<dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'loginForm.mailAddress'/>">
		  <dt>
		  	<span class="input-item-name"><label for="loginForm_mailAddress">メールアドレス</label></span>
		  </dt>
		  <dd>
			<@s.textfield name="loginForm.mailAddress" cssClass="form-text input-shadow" autocomplete="off" placeholder="メールアドレス" />
			<@s.fielderror><@s.param value="%{'loginForm.mailAddress'}" /></@s.fielderror> 
			<div class="js-input-balloon-d">アカウントに登録しているメールアドレスを入力して下さい。</div>
		  </dd>
		</dl>

		<dl class="input-item clearfix <@my.errorInputClass 'loginForm.password'/>">
		  <dt>
		    <span class="input-item-name"><label for="loginForm_password">パスワード</label></span>
		  </dt>
		  <dd>
			<@s.password name="loginForm.password" cssClass="form-text input-shadow" autocomplete="off" />
			<@s.fielderror><@s.param value="%{'loginForm.password'}" /></@s.fielderror> 
			<div class="pt-12"><a href="${url('/user/send-password')}">パスワードを忘れた方はこちら</a></div>
		  </dd>
		</dl>
		
		<dl class="input-item clearfix js-input-balloon-con">
		  <dt><span class="input-item-name">ログイン状態を維持</span></dt>
		  <dd class="p-4">
		    <@s.checkbox name="loginForm.keep" cssClass="form-checkbox" /><label for="loginForm_keep">ログイン状態を維持する</label>
		    <@s.fielderror><@s.param value="%{'loginForm.keep'}" /></@s.fielderror>
		  </dd>
		</dl>
		  
		<@s.token />
		<dl class="input-item clearfix item-submit">
			<dt>&nbsp;</dt>
			<dd><@s.submit value="送信" cssClass="btn-b btn-large form-submit" /></dd>
		</dl>
	  </form>
	</div>
	<div class="clmn-right-menu">
	  <div>
	    <h1 class="c-pt pt-12">外部アカウントでログイン</h1>
		<dl class="input-item clearfix item-submit">
			<dd><a class="btn-fb btn-large icon-facebook-sign" href="${url('/fb-user/login')}">Facebook</a></dd>
		</dl>
		<dl class="input-item clearfix item-submit">
			<dd><a class="btn-tw btn-large icon-twitter-sign" href="${url('/tw-user/login')}">Twitter</a></dd>
		</dl>
	  </div>
    </div>
</section>
