  <div class="p-6 contents">
  	<div id="u-map-canvas"></div>
  </div>
  
  <nav class="clearfix nav mt-14">
    <div class="fl c-btn-wrap">
  	  <ul class="c-switch-menu clearfix">
  	    <li><a href="#">すべて</a></li>
  	    <li><a href="#">地図周辺</a></li>
  	  </ul>
  	</div>
  	
  	<div class="fr c-btn-wrap">
  	  <ul class="c-switch-menu clearfix">
  	    <li><a href="#">タイムライン</a></li>
  	    <li><a href="#">一覧</a></li>
  	  </ul>
  	</div>
  </nav>
  
  <!-- 
  <div class="contents mt-14">
    <h2 class="p-12">イベントリスト</h2>
    <div class="c-no-result">
      登録されたイベントはありません。
    </div>
  </div>
   -->
  <div class="c-events-tl mt-14">
    <#list ['art', 'music', 'game'] as row>
    <div class="c-events-tl-item contents mt-14 p-12 pos-r">
      <img class="c-events-tl-item-category-img" src="${assets('category-icons/circle-book-36.png', 'images', 'common')}" width="36" height="36" />
      <div class="c-events-tl-item-h clearfix">
        <h3 class="fl pr-6"><a href="#" class="bold">タイトルタイトルタイトル...</a></h3>
      </div>
      <div class="c-events-tl-item-b pt-6 sub-text">
        <p>１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０１２３４５６７８９０...</p>
      </div>
    </div>
    </#list>
  </div>
  
  <script type="text/javascript">
	  $(document).ready(function() {
		var map = new my.Map("u-map-canvas");
			map.create();
			map.putEventMarker("ART", 35.6894875, 139.69170639999993);
			map.putEventMarker("GAME", 35.6864875, 139.69170639999993);
			map.putEventMarker("MUSIC", 35.6894875, 139.69570639999993);
			map.panTo(map.lat, map.lon);
			/*map.bindPosNavigation("i-map-nav-place");*/
	  });
  </script>