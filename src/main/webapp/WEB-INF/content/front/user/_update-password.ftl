<#if currentUser.isProperUser()>
<dl id="u-update-password" class="input-item clearfix <@my.errorInputClass 'userForm.password'/>">
  <dt class="input-item-name"><label for="userForm_password">パスワード<span class="bold">(非公開)</span></label></dt>
  <#if currentUser.password??>
  <dd>
  	<@s.password name="userForm.password" 
  			cssClass="form-text cc-input cancel-enter char-counter" placeholder="${maskPassword(currentUser.password)}" />
  	<div class="clearfix pt-6">
  	  <div class="fl">
        <#if updated >
          <span class="fl u-update-partial-success-text">更新しました。</span>
          <script type="text/javascript">
            $(".u-update-partial-success-text").animate({opacity: 0}, {duration:2500} );
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.password'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
    	  my.utils.partialPost(
    		  "#u-update-password", 
    		  "${url('/user/update-password-ajax')}" );
        });
      </script>
    </div>
  </dd>
  </#if>
</dl> 
</#if>