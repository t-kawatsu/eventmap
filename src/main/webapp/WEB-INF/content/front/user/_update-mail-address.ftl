<#if currentUser.mailAddress??>
<dl id="u-update-mail-address" class="input-item clearfix <@my.errorInputClass 'userForm.mailAddress'/>">
  <dt class="input-item-name"><label for="userForm_mailAddress">メールアドレス<span class="bold">(非公開)</span></label></dt>
  <dd>
  	<@s.textfield name="userForm.mailAddress" 
  			cssClass="form-text cc-input cancel-enter char-counter" />
  	<div class="clearfix pt-6">
  	  <div class="fl">
        <#if updated >
          <div id="u-mail-update-send-mail-complete-message" class="hide" style="line-height:27px;">
		    <span class="bold">${userForm.mailAddress?html}宛に確認メールをお送りしました。</span><br/>
		      メールの内容にそいメールアドレスの変更を完了させて下さい。
		    <ul class="center-list pt-12" >
              <li><button type="button" class="form-button btn-a js-cancel-modal btn-small" >閉じる</button></li>
            </ul>
          </div>
          <script type="text/javascript">
    	    $.fancybox.open($('#u-mail-update-send-mail-complete-message').clone().removeClass('hide'));
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.mailAddress'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
      	  my.utils.partialPost(
      		  "#u-update-mail-address", 
      		  "${url('/user/update-mail-address-ajax')}" );
        });
      </script>
    </div>
  </dd>
</dl> 
</#if>