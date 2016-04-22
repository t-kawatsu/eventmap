<#macro date date>
	<span class="c-i-date icon-time c-color-g">${date?string("yyyy年MM月dd日 H時")}</span>
</#macro>

<#macro errorInputClass fieldname ><#if fieldErrors[fieldname]?exists >error-item</#if></#macro>

<#macro userImg user size>
<#if size == "ml">
<#local w = 88 />
<#local h = 88 />
<#local size = "m" />
<#else>
<#local w = userImageSize(size).width />
<#local h = userImageSize(size).height />
</#if>
<#if !user?? || !user.userImageId?? >
  <img src="${assets('u-noimage-'+size+'.png', 'images', 'common')}" class="radius-3 b-shadow" width="${w?c}" height="${h?c}" alt="${user.nickname!""?html}" />
<#else>
  <img src="${userImageSrc(user.id, user.userImageId, size)}" class="radius-3 b-shadow" width="${w?c}" height="${h?c}" alt="${user.nickname!""?html}" />
</#if>
</#macro>

<#macro userBgImg user size>
<#local w = userBgImageSize(size).width />
<#local h = userBgImageSize(size).height />
<#if !user?? || !user.userBgImageId?? >
  <#switch size>
    <#case "ss">
  <div class="c-u-bgimg-noimage-ss">&nbsp;</div>
    <#break/>
    <#case "m">
  <div class="u-bgimg-noimage">&nbsp;</div>
    <#break/>
  </#switch>
<#else>
  <img src="${userBgImageSrc(user.id, user.userBgImageId, size)}" class="radius-3 b-shadow" width="${w?c}" height="${h?c}" />
</#if>
</#macro>

<#macro nl2br> 
   <#local content> 
      <#nested><#t> 
   </#local> 
   ${content?replace("\n", "<br>")} 
</#macro>
