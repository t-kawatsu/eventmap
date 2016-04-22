<dl id="u-update-native" class="input-item clearfix <@my.errorInputClass 'userForm.native'/>">
  <dt class="input-item-name"><label for="userForm_native">出身地</label></dt>
  <dd>
    <div class="js-area-select-group">
  	  <@s.select cssClass="js-area-country-select form-select" style="width:180px;" listKey="key" listValue="value" list="userForm.nativeCountryCodes" key="userForm.nativeCountryCode" value="userForm.nativeCountryCode" />
  	  <@s.select cssClass="js-area-subdivision-select form-select" style="width:180px;" listKey="key" listValue="value" list="userForm.nativeSubdivisionCodes" key="userForm.nativeSubdivisionCode" value="userForm.nativeSubdivisionCode" />
  	</div>
  	<div class="clearfix pt-6">
  	  <div class="fl">
        <#if updated >
          <span class="fl u-update-partial-success-text">更新しました。</span>
          <script type="text/javascript">
            $(".u-update-partial-success-text").animate({opacity: 0}, {duration:2500} );
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.native'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
          //my.resources.get("input").Behavior.areaSelector.settings.subdivisionUrl = "${url('/subdivision/index-ajax')}";
          //my.resources.get("input").bind("areaSelector");
    	  my.utils.partialPost(
    		  "#u-update-native", 
    		  "${url('/user/update-native-ajax')}" );
        });
      </script>
    </div>
  </dd>
</dl>
