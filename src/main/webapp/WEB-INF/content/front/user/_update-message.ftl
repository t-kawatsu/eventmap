<dl id="u-update-message" class="input-item clearfix <@my.errorInputClass 'userForm.message'/>">
  <dt class="input-item-name"><label for="userForm_message">自己紹介/メッセージ</label></dt>
  <dd>
  	<@s.textarea name="userForm.message" 
  			cssClass="form-textarea cc-input auto-resize-textbox char-counter"
  			placeholder="自己紹介/メッセージを入力する"/>
	<div class="clearfix pt-6">
	  <div class="fl">
        <#if updated >
          <span class="fl u-update-partial-success-text">更新しました。</span>
          <script type="text/javascript">
            $(".u-update-partial-success-text").animate({opacity: 0}, {duration:2500} );
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.message'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
    	  my.utils.partialPost(
    		  "#u-update-message", 
    		  "${url('/user/update-message-ajax')}" );
    	  my.resources.get("input").bind("autoResize");
        });
      </script>
    </div>
  </dd>
</dl> 