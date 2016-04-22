<@compress single_line=false>
<!DOCTYPE html>
<html lang="ja"
	xmlns="http://www.w3.org/1999/xhtml"
	xml:lang="ja"
	xmlns:og="http://ogp.me/ns#"
	xmlns:fb="http://www.facebook.com/2008/fbml">
<head>
<meta charset="UTF-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" >
<meta http-equiv="Content-Style-Type" content="text/css" >
<meta http-equiv="Content-Script-Type" content="text/javascript" >
<meta name="keywords" content="イベントマップ,eventmap,jogoj" >
<meta name="description" content="" >
<meta name="author" content="jogoj Inc." >
<meta name="copyright" content="Copyright &amp;copy;jogoj Inc." >

<link href="${assets('jquery.fancybox.css', 'css', 'common')}" media="screen, print" rel="stylesheet" type="text/css" >
<link href="${assets('jquery.qtip.min.css', 'css', 'common')}" media="screen, print" rel="stylesheet" type="text/css" >
<link href="${assets('font-awesome.css', 'css', 'common')}" media="screen" rel="stylesheet" type="text/css" >
<link href="${assets('default.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" >
<link href="${assets('common.css', 'css')}?_=20130407" media="screen, print" rel="stylesheet" type="text/css" >
<link href="${assets('emotion.css', 'css', 'common')}" media="screen" rel="stylesheet" type="text/css" >
<link href="${assets('site-id-fb.png', 'images', 'common')}" rel="icon" type="image/gif" >
<link href="${assets('site-id-fb.png', 'images', 'common')}" rel="shortcut icon" type="image/gif" >

<script type="text/javascript" src="${assets('jquery-1.8.2.min.js', 'js', 'common')}"></script>

<!--[if lt IE 9]>
<script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->

<title>${cutStr(title, 18)?html} | <@s.text name="app.site.name"/></title>

<meta property="og:title" content="${cutStr(title, 18)?html}"/>
<meta property="og:image" content="${url('/', 'true', 'true')}${assets('site-id-fb.png', 'images', 'common')}"/>
<meta property="og:url" content="${url('', 'true', 'true')}"/>
<meta property="og:site_name" content="<@s.text name="app.site.name"/>"/>
<meta property="og:type" content="website"/>
<meta property="og:app_id" content="<@s.text name="app.facebook.appId"/>"/>

<meta name="google-site-verification" content="<@s.text name='app.google.siteVerificationMeta' />" />
</head>

<body>
  <div id="container">
	<div id="wrapper">
	  <header id="header">
	    <div class="gframe clearfix">
		  <h1 id="site-id"><a href="${url('/', 'true')}"><img src="${assets('site-id.png', 'images', 'common')}" alt="<@s.text name="app.site.name"/>" width="103" height="36" /></a></h1>
		  <div class="c-u-info">
		  </div>
		  <ul class="header-menu clearfix">
		    <li><a class="icon-caret-right" href="${url('/about', 'true')}">イベントマップとは?</a></li>
		    <#if isLogined == true >
		    <li class="js-hover-menu-con pos-r">
		    	<a class="icon-caret-right" href="${url('/user/read/' + currentUser.id?c, 'true', 'true')}">マイページ</a>
		    	<@my.userImg currentUser "ss" />
	    		<ul id="my-menu-con" class="js-hover-menu radius-1">
	    		  <li><a href="${url('/user/read/' + currentUser.id?c + '/watch', 'true', 'true')}">ウォッチ</a></li>
	    		  <li><a href="${url('/user/read/' + currentUser.id?c + '/activity', 'true', 'true')}">アクティビティ</a></li>
	    		  <li><a href="${url('/user/read/' + currentUser.id?c + '/community', 'true', 'true')}">コミュニティ</a></li>
	    		  <li><a href="${url('/user/read/' + currentUser.id?c + '/detail', 'true', 'true')}">基本情報</a></li>
	    		  <li><a href="${url('/user/logout', 'true')}">ログアウト</a></li>
	    		</ul>
		    </li>
		    <#else>
		    <li><a class="icon-caret-right" href="${url('/user/create', 'true', 'true')}">ユーザー登録</a></li>
		    <li class="js-hover-menu-con pos-r">
		    	<a class="icon-caret-right" href="${url('/user/login', 'true', 'true')}">ログイン</a>
		    </li>
		    </#if>
		  </ul>
	    </div>
	  </header>
	  <div class="gframe">
	    <div id="contents">
		  <div id="${pathinfo('controller')}-${pathinfo('action')}" class="${pathinfo('controller')}-container clearfix">
			${body}
		  </div>
	    </div>
	  </div>
	</div>
	<footer id="footer" class="fs-ss pt-12 pb-12">
	  <div class="gframe clearfix">
	    <nav class="footer-nav fl p-12 clearfix">
		  <ul>
		    <li><a href="${url('/about', 'true')}">イベントマップとは</a></li>
		    <#if isLogined == true >
		    <li><a href="${url('/user/logout', 'true')}">ログアウト</a></li>
		    <#else>
		    <li><a href="${url('/user/create', 'true', 'true')}">新規登録</a></li>
		    </#if>
		    <li><a href="${url('/terms', 'true')}">利用規約</a></li>
		    <li><a href="${url('/terms/privacy', 'true')}">プライバシー</a></li>
		    <li><a href="${url('/help', 'true')}">ヘルプ</a></li>
		    <li><a href="${url('/inquiry/create', 'true', 'true')}">お問い合わせ</a></li>
		  </ul>
		</nav>
		<div class="fr p-12 ta-r">
			<div class="copyright pb-12">http://eventmap.in</div>
			<div class="copyright pb-12">Copyright &copy;jogoj Inc. All rights reserved.</div>
			<div class=""><a class="copyright" style="text-decoration:underline;" href="<@s.text name="app.company.url" />" target="_blank">株式会社jogoj</a></div>
		</div>
	  </div>
	</footer>
  </div>

  <a id="page-top" class="smooth-site-link" href="#"><i class="icon-arrow-up"></i><br/><span>PAGE TOP</span></a>

  <script type="text/javascript" src="${assets('jquery-libs.pack.js', 'js', 'common')}" ></script>
  <script type="text/javascript" src="${assets('my.js', 'js')}" ></script>

  <script type="text/javascript" src="https://maps.google.com/maps/api/js?libraries=places&sensor=false&language=ja"></script>

  <#include "../common/_twitter-root.ftl" />
  <#include "../common/_facebook-root.ftl" />
  
<script type="text/javascript">
  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-31427166-3']);
  _gaq.push(['_trackPageview']);
  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
</script>

</body>
</html>
</@compress>