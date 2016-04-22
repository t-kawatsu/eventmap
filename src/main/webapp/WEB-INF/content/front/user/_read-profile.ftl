<section>
  <div class="u-img-con mb-14">
	  <#include '../user-image/_u-bg-img.ftl' />
	  <#include '../user-image/_u-img.ftl' />
	  <h2 class="u-nickname">${user.nickname!""?html}</h2>
  </div>
  <#if isMyPage >
  <script type="text/javascript">
	  $('.u-img-con').on({
		'mouseenter' : function(e) {
			$(this).find('.u-img-update-menu')
				.css('opacity', 0)
				.removeClass('hide')
				.animate({opacity : 1}, "fast");
		},
		'mouseleave' : function(e) {
			$(this).find('.u-img-update-menu')
				.css('opacity', 1)
				.animate({opacity : 0}, "fast");
		}
	  }, '.u-img,.u-bgimg');
  </script>
  </#if>
  
  <#if isMyPage >
  <div class="pl-4" style="height:54px;">
    <a href="${url('/user/update', 'true', 'true')}" class="">プロフィールを編集</a>
  </div>
  </#if>

  <div class="clearfix mt-14 contents p-6">
    <div class="fl u-detail-left">
      <div class="u-detail-item">
        <h3 class="u-detail-item-name">自己紹介/メッセージ</h3>
        <p><@my.nl2br>${user.message!""?html}</@my.nl2br></p>
      </div>
    </div>
    <div class="fl u-detail-right pl-6">
      <#if user.isFacebookUser() && !user.fbUser.isPrivate >
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">Facebookアカウント</dt>
        <dd><a href="https://www.facebook.com/${user.fbUser.fbId}" target="_blank" class="icon-facebook">${user.fbUser.accountName?html}</a></dd>
      </dl> 
      </#if>
      <#if user.isTwitterUser() && !user.twUser.isPrivate >
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">Twitterアカウント</dt>
        <dd><a href="https://twitter.com/${user.twUser.accountName}">${user.twUser.accountName?html}</a></dd>
      </dl>
      </#if>
      <#if user.sex??>
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">性別</dt>
        <dd class="pt-6">${languageSetting("C008", user.sex)}</dd>
      </dl>
      </#if>
      <#if user.birthday?? >
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">誕生日</dt>
        <dd>${user.birthday?string('yyyy年MM月dd日')}</dd>
      </dl>
      </#if>
      <#if user.residenceCountryCode??>
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">居住地</dt>
        <dd class="pt-6">${languageCountry(user.residenceCountryCode)}&nbsp;${languageSubdivision(user.residenceSubdivisionCode)}</dd>
      </dl>
      </#if>
      <#if user.nativeCountryCode??>
      <dl class="u-detail-item">
        <dt class="u-detail-item-name">出身地</dt>
        <dd class="pt-6">${languageCountry(user.nativeCountryCode)}&nbsp;${languageSubdivision(user.nativeSubdivisionCode)}</dd>
      </dl>
      </#if>
    </div>
  </div>
</section>
