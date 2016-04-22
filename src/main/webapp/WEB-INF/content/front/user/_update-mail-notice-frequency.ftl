<dl id="u-update-mail-notice-frequency-code" class="input-item clearfix">
   <dt class="input-item-name">メール送信頻度</dt>
   <dd style="width:100%;">
     <@s.radio listKey="key" listValue="value" list="userForm.mailNoticeFrequencyCodes" key="userForm.mailNoticeFrequencyCode" value="userForm.mailNoticeFrequencyCode"/>
	 <div class="clearfix pt-6">
       <#if updated >
         <span class="fl icon-ok-sign u-update-partial-success-text">更新しました。</span>
         <script type="text/javascript">
           $(".u-update-partial-success-text").animate({opacity: 0}, {duration:2500} );
         </script>
       </#if>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
    	  my.utils.partialPost(
    		  "#u-update-mail-notice-frequency-code", 
    		  "${url('/user/update-mail-notice-frequency-ajax')}" );
        });
      </script>
    </div>
  </dd>
</dl> 