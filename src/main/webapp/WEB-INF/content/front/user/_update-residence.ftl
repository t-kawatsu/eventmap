<dl id="u-update-residence" class="input-item clearfix <@my.errorInputClass 'userForm.residence'/>">
  <dt class="input-item-name"><label for="userForm_residence">居住地</label></dt>
  <dd>
    <div class="js-area-select-group">
  	  <@s.select cssClass="js-area-country-select form-select" style="width:180px;" listKey="key" listValue="value" list="userForm.residenceCountryCodes" key="userForm.residenceCountryCode" value="userForm.residenceCountryCode" />
  	  <@s.select cssClass="js-area-subdivision-select form-select" style="width:180px;" listKey="key" listValue="value" list="userForm.residenceSubdivisionCodes" key="userForm.residenceSubdivisionCode" value="userForm.residenceSubdivisionCode" />
  	</div>
  	<div class="clearfix pt-6">
  	  <div class="fl">
        <#if updated >
          <span class="fl u-update-partial-success-text">更新しました。</span>
          <script type="text/javascript">
            $(".u-update-partial-success-text").animate({opacity: 0}, {duration:2500} );
          </script>
        <#else>
          <@s.fielderror><@s.param value="%{'userForm.residence'}" /></@s.fielderror> 
        </#if>
      </div>
      <a href="#" class="fr btn-a btn-ssmall js-partial-post-btn">変更する</a>
      <script type="text/javascript">
        $(document).ready(function(e) {
          my.resources.get("input").Behavior.areaSelector.settings.subdivisionUrl = "${url('/subdivision/index-ajax')}";
          my.resources.get("input").bind("areaSelector");
    	  my.utils.partialPost(
    		  "#u-update-residence", 
    		  "${url('/user/update-residence-ajax')}" );
        });
      </script>
    </div>
  </dd>
</dl>
