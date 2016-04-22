<dl id="u-update-nickname" class="input-item clearfix <@my.errorInputClass 'userForm.nickname'/>">
  <dt>
    <span class="input-item-name"><label for="userForm_nickname">名前(ニックネーム)</label></span>
    <span class="input-item-d">2~20文字</span>
  </dt>
  <dd>
    <@s.textfield name="userForm.nickname" cssClass="form-text input-shadow" autocomplete="off" placeholder="ニックネーム" />
    <div class="js-input-balloon-d">
      このサイトでのあなたの表示名です。<br/>2文字以上20文字以内で入力して下さい。
    </div>
    <div class="clearfix pt-6">
      <div class="fl">
        <#if updated >
          <span class="fl u-update-partial-success-text">更新しました。</span>
          <script type="text/javascript">
            $(".u-update-partial-success-text").animate( {opacity: 0}, {duration:2500} );
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.nickname'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
        	my.utils.partialPost(
        		  "#u-update-nickname", 
        		  "${url('/user/update-nickname-ajax')}" );
        });
      </script>
    </div>
  </dd>
</dl>