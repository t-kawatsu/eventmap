<title>イベントマップ</title>
<link href="${assets('index.css', 'css')}" media="screen" rel="stylesheet" type="text/css" />

<div class="i-map-con contents">
  <div class="i-map-wrap clearfix">
  	<div id="map-canvas"></div>
  </div>
  <div class="i-timeline-con b-shadow">
    <nav class="nav i-map-nav-bar">
  	  <div id="i-map-nav-place" class="fs-ss">東京都世田谷区砧</div>
  	</nav>
  	<ul class="i-timeline">
  	  <#list ['art', 'music', 'game'] as row>
  	  <li>
  	    <div class="icon-time fs-ss sub-fc i-timeline-t">2013/05/03 5時</div>
  	    <div class="clearfix pl-12">
  	  	  <div class="fl">
  	  	    <img src="${assets('category-icons/circle-${row}-36.png', 'images', 'common')}" width="36" height="36" />
  	  	  </div>
  	  	  <div class="fl pl-6">
  	  	    <h3 class="bold pt-6 pb-6"><a href="#">テストテストイベント</a></h3>
  	  	    <div class="fs-ss">by <a href="#">ごろちゃん</a></div>
  	  	  </div>
  	  	</div>
  	  </li>
  	  </#list>
  	</ul>
  </div>
</div>
<div>

<div class="clearfix pt-12">
	<div class="clmn-left-main">
	  <div class="i-activity-con contents radius-3">
	    <h2 class="p-12 h2">周辺のアクティビティ</h2>
		<ul class="i-activity">
		<#list ['a', 'b', 'b'] as row >
		  <li class="p-12">
		    <div class="icon-time fs-ss sub-fc pb-6 pt-6">2013/05/03 5時</div>
		    <div class="clearfix">
		      <div class="i-a-user-p pr-6">
		        <img class="radius-3 b-shadow" src="${assets('u-sample.jpg', 'images', 'external')}" alt="" width="36" height="36" />
		      </div>
		      <div class="i-a-detail">
		        <div class=""><a class="bold" href="#">ユーザーほげほげ</a></div>
		        <address >東京都世田谷区砧.....</address>
		      </div>
		    </div>
		  </li>
		  </#list>
		</ul>
	  </div>
	</div>
	<div class="clmn-right-menu">
		<div class="i-guide radius-3">
		  <h2></h2>
		</div>
		<div class="contents radius-3 mt-12">
	      <h2 class="p-12 h2">おすすめのイベント</h2>
		  <ul class="i-activity">
		    <#list ['a', 'b', 'b'] as row >
		    <li class="p-12">
		    </li>
		    </#list>
		  </ul>
	    </div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		var map = new my.Map("map-canvas");
			map.create();
			map.putEventMarker("ART", 35.6894875, 139.69170639999993);
			map.putEventMarker("GAME", 35.6864875, 139.69170639999993);
			map.putEventMarker("MUSIC", 35.6894875, 139.69570639999993);
			map.panTo(map.lat, map.lon);
			map.bindPosNavigation("i-map-nav-place");
	});
</script>
</div>