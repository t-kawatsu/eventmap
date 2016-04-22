<title>イベント登録</title>

<link type="text/css" href="${assets('jquery-ui-1.10.2.custom.min.css', 'css', 'common')}" rel="stylesheet" />
<link href="${assets('event.css', 'css')}" media="screen, print" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${assets('jquery.timepicker.min.js', 'js', 'common')}" ></script>
<link href="${assets('jquery.timepicker.css', 'css', 'common')}" media="screen, print" rel="stylesheet" type="text/css" >

<script type="text/javascript" src="${assets('jquery.upload-1.0.2.min.js', 'js', 'common')}" ></script>


<section class="clearfix">
	<div class="clmn-left-main">
	  <h1 class="c-pt pt-12">イベント登録</h1>
	  <form class="e-create-form" action="${url('/event/create', 'true', 'true')}" method="POST">

		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.name'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_name">イベント名</label></span>
		    	<span class="input-item-d">任意(300文字まで)</span>
		    </dt>
		    <dd>
		      <@s.textfield name="eventForm.name" cssClass="form-text input-shadow" autocomplete="off" placeholder="イベント名" />
			  <@s.fielderror><@s.param value="%{'eventForm.name'}" /></@s.fielderror> 
		    </dd>
		  </dl>
		  
		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.name'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_categoryId">イベントのカテゴリは？</label></span>
		    	<span class="input-item-d">任意</span>
		    </dt>
		    <dd>
		      <@s.select listKey="key" listValue="value" list="eventForm.categories" key="eventForm.categoryId" cssClass="form-select js-category-select" />
			  <@s.fielderror><@s.param value="%{'eventForm.categoryId'}" /></@s.fielderror> 
		    </dd>
		  </dl>

		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.event_image_id'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_place">写真アップロード</label></span>
		    	<span class="input-item-d">任意</span>
		    </dt>
		    <dd>
		        <#assign hasSuspendFile = eventForm.imageIds?? && 0 < eventForm.imageIds?size />
		    	<div class="e-image-con">
			      <ul class="e-images clearfix">
			        <li class="fl">
			          <div class="e-image-upload-panel btn-a" style="padding:0px">
					    <i class="e-icn-upload icon-upload-alt"></i>
					    <div class="fs-ss">ファイルを選択(jpeg/gif/png)</div>
					    <@s.file name="imageForm.file" label="Image" cssClass="form-file" />
					  </div>
					  <div class="e-image-err-msg"></div>
			        </li>
			      <#if hasSuspendFile >
			        <#list eventForm.imageIds as row >
			          <li class="fl ml-14 pos-r" data-file-id="${row}"><img src="${url('/event-image/read-tmp/' + row + '/ss')}" alt="" class="radius-3 b-shadow" width="126" height="95" /><a class="fancybox-close e-btn-remove-image"></a></li>
			        </#list>
			      </#if>
			      </ul>
			      <@s.hidden name="eventForm.imageIdsCsv" cssClass="c-images-csv-input" />
			      <@s.hidden name="eventForm.imageTypesCsv" cssClass="c-image-types-csv-input" />
			      <@s.hidden name="eventForm.imageVendorDataCsv" cssClass="c-image-vendor-data-input" />
			    </div>
			    <script type="text/javascript">
					$(".e-create-form").on('change', "input[type='file']", function(e) {
						var app = my.resources.get("app");
					    $(this).upload(
					    	"${url('/event-image/upload-ajax')}", 
					        { /* no_csrf:form.find('#no_csrf').val() */ },
					    	function(res) {
					            res = jQuery.parseJSON(res);
					            if(!res || res.fileId == undefined) {
					            	var eMsg = '<ul class="errorMessage">';
					                jQuery.each(res.messages, function() {
					                	eMsg += '<li><span>' + this + '</span></li>';
					                });
					                eMsg += '</ul>';
					                $(".e-image-err-msg").append($(eMsg));
					                return false;
					            } else {
					     			$('.e-images').append(
					     	    			$('<li class="fl ml-14 pos-r" data-file-id="'+res.fileId+'"><img src="${url("/event-image/read-tmp/")}'+res.fileId+'/ss" class="radius-3 b-shadow"><a class="fancybox-close e-btn-remove-image"></a></li>'));
					     	    	app.image.appendImageInfo(res.fileId, res.type, res.vendorData, '.e-image-con');
					            }
					    	}
					    );
					});
				</script>
		    </dd>
		  </dl>
		  
		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.address'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_address">場所は？</label></span>
		    	<span class="input-item-d">必須</span>
		    	<span class="input-item-d">※</span>
		    </dt>
		    <dd>
		      <!-- <div id="map-canvas"></div> -->
	      	  <@s.textfield name="eventForm.address" cssClass="form-text input-shadow cancel-enter" placeholder="場所は？" />
			  <@s.fielderror><@s.param value="%{'eventForm.address'}" /></@s.fielderror> 
		    </dd>
		  </dl>

		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.startAt'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_startAt">いつ？</label></span>
		    	<span class="input-item-d">必須</span>
		    </dt>
		    <dd>
				<div class="table ce-datetimepicker-table">
				  <div class="table-row">
					<div class="table-cell">
						<@s.textfield name="eventForm.startAtDate" cssClass="form-text input-shadow datepicker" placeholder="${date?string('yyyy/MM/dd')}"/>
					</div>
					<div class="table-cell">
					    <@s.textfield name="eventForm.startAtTime" cssClass="form-text input-shadow timepicker" placeholder="${date?string('H:30')}" />
					</div>
					<div class="table-cell e-end-at-items" <#if !eventForm.endAt??>style="display:none"</#if> ><span style="width:100%">~</span></div>
					<div class="table-cell e-end-at-items" <#if !eventForm.endAt??>style="display:none"</#if> >
						<@s.textfield name="eventForm.endAtDate" cssClass="form-text input-shadow datepicker" placeholder="${date?string('yyyy/MM/dd')}"/>
					</div>
					<div class="table-cell e-end-at-items" <#if !eventForm.endAt??>style="display:none"</#if> >
					    <@s.textfield name="eventForm.endAtTime" cssClass="form-text input-shadow timepicker" placeholder="${date?string('H:30')}" />
					</div>
					<div class="table-cell e-add-end-at">
					  <button type="button" class="form-button btn-event-add-end-at btn-a btn-ssmall c-inline-btn " style="margin:0px 6px;">
					  	<span class="icon-caret-left" <#if eventForm.endAt??>style="display:none"</#if> >終了時間を追加</span>
					  	<span <#if !eventForm.endAt??>style="display:none"</#if> >×</span>
					  </button>
					</div>
				  </div>
				  <div class="table-row">
				    <@s.fielderror><@s.param value="%{'eventForm.startAt'}" /></@s.fielderror>
				  </div>
				</div>
		    </dd>
		  </dl>
		  
		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.description'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_description">なにが起こる？or起こった？</label></span>
		    	<span class="input-item-d">必須(1500文字まで)</span>
		    </dt>
		    <dd>
		      <@s.textarea name="eventForm.description" cssClass="form-textarea input-shadow cc-input auto-resize-textbox" autocomplete="off" placeholder="なにが起こる？or起こった？" />
			  <@s.fielderror><@s.param value="%{'eventForm.description'}" /></@s.fielderror> 
		    </dd>
		  </dl>
		  
		  <dl class="input-item clearfix js-input-balloon-con <@my.errorInputClass 'eventForm.status'/>">
		    <dt>
		    	<span class="input-item-name"><label for="eventForm_categoryId">公開範囲</label></span>
		    	<span class="input-item-d">必須</span>
		    </dt>
		    <dd>
		      <@s.select listKey="key" listValue="value" list="eventForm.statuses" key="eventForm.status" cssClass="form-select js-category-select" />
			  <@s.fielderror><@s.param value="%{'eventForm.status'}" /></@s.fielderror> 
		    </dd>
		  </dl>
			  
		  <@s.token />
		  <dl class="input-item clearfix item-submit">
		    <dt>&nbsp;</dt>
		    <dd class="clearfix">
		    	<div class="fl mr-14"><a class="btn-a btn-small" href="${url('/user/read/'+currentUser.id?c)}">戻る</a></div>
		    	<div class="fl"><@s.submit value="登録する" cssClass="btn-b form-submit btn-small" /></div>
		    </dd>
		  </dl>
		  
		<script type="text/javascript">
		  $(document).ready(function() {
			$.datepicker.setDefaults({
			  	dateFormat: 'yy/mm/dd',
			  	dayNames:[
			  	],
			  	dayNamesMin:[ "日", "月", "火", "水", "木", "金", "土" ],
			  	dayNamesShort:[ "日", "月", "火", "水", "木", "金", "土" ],
			  	monthNames:[ 
			  		"1月", "2月", "3月", "4月", "5月", "6月", "7月", "8月", "9月", "10月", "11月", "12月" 
			  	],
			  	monthNamesShort:[
			  		1,2,3,4,5,6,7,8,9,10,11,12
			  	],
			  	gotoCurrentType:true
			});
			$(".datepicker").datepicker();
			$('.timepicker').timepicker({ 'timeFormat': 'H:i' });
	
			var app = my.resources.get("app");
			
			$('.btn-event-add-end-at').on('click', function(e) {
				e.preventDefault();
				$(this).find('span').toggle();
				if($('.e-end-at-items').toggle().css('display') == 'none') {
					$('.e-end-at-items').find('input').val(null);
				}
			});
			
			$(document).on('click', '.e-btn-remove-image', function(e) {
	 			e.preventDefault();
	 			var fileId = $(this).parents('li').remove().attr("data-file-id");
	 			app.image.removeImageInfo(fileId, '.e-image-con');
	 		});
			/*
			var map = new my.Map("map-canvas");
				map.create();
				map.panTo(map.lat, map.lon);
				map.bindPosNavigation("i-map-nav-place");
			*/
			var place = new my.Place();
				place.autocomplete("eventForm_address");
		  });
		</script>
	  </form>
	</div>
	<div class="clmn-right-menu">
	  <div>
	    <h1 class="c-pt pt-12">他サービスからイベントを登録</h1>
		<dl class="input-item clearfix item-submit">
			<dd><a class="btn-fb btn-large icon-facebook-sign" href="${url('/fb-user/event-create')}">Facebook</a></dd>
		</dl>
	  </div>
    </div>
</section>
