$(function () {
	//列表项模板
    var template = '<tr class="template-upload fade in">'+
        '<td style="vertical-align:middle; text-align:left; width:50%;">'+
           ' <p class="name">${fileName_}</p>  '+          
        '</td>'+
        '<td style="vertical-align:middle; text-align:center; width:30%;">'+
        '    <p class="size">${fileSize_} KB</p>'+
        '    <div class="progress progress-success progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="bar" style="width:0%;"></div></div>'+
        '</td>'+
        '<td style="vertical-align:middle; text-align:center; width:20%;">'+
        '    <button class="btn btn-primary start">'+
        '       <i class="icon-upload icon-white"></i>'+
        '       <span>开始</span>'+
        '    </button>'+
        '    <button class="btn btn-warning cancel">'+
        '       <i class="icon-ban-circle icon-white"></i>'+
        '       <span>取消</span>'+
        '    </button>        '+    
        '</td>'+
    '</tr>';
    var url = 'uploadfile';
    var currentData = {};
    $('#fileupload').fileupload({autoUpload: true,
        url: url,
        dataType: 'json',
        add: function (e, data) {
    	   var templateImpl = $.tmpl(template,{"fileName_":data.files[0].name,"fileSize_":(data.files[0].size/1000).toFixed(2)}).appendTo( ".files" );
    	   data.content = templateImpl;
    	   $(".start", templateImpl).click(function () {
    		    currentData.bar = templateImpl;    		    
                $('<p/>').text('正在上传...').addClass("uploading").replaceAll($(this));
                data.submit();
           });
    	   $(".cancel", templateImpl).click(function () {
                $('<p/>').text('取消...').replaceAll($(this));
                data.abort();
                $(templateImpl).remove();
    	   });
        },

        done: function (e, data) {
        	if(data.result.returnback == 'TYPEERROR'){
        		$(".uploading", data.content).text('只能是xls或xlsx类型文件！');
        	}else if(data.result.returnback == 'ERROR'){
        		$(".uploading", data.content).text('上传出错！');
        	}else{
        		$(".uploading", data.content).text('上传成功, 马上跳转！');
        		window.setTimeout(function(){
        			window.location.href = "house!houselist?type=WEISHENHE";
        		}, 1000); 
        	}
        },
        
        progressall: function (e, data) {
            var progress = parseInt(data.loaded / data.total * 100, 10);
            $('.bar', currentData.bar).css(
                'width',
                progress + '%'
            );
        }
    });
});