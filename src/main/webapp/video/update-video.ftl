<!--弹出修改用户窗口-->
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">修改视频</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-10 col-lg-offset-1">
            <form class="form-horizontal" id="videoUpdateForm" role="form">
                <input type="hidden" name="id" value="${video.id!''}">
                <div class="form-group ">
                    <label for="name" class="col-lg-2">名称：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="name" name="name"
                               value="${video.name!''}">
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="category" class="col-lg-2">类别：</label>
                    <div class="col-lg-9">
                        <select id="category" class="form-control" name="categoryId">
                            ${video.category!''}
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">预览封面：</label>
                    <div class="col-lg-9">
                        <img src="${video.poster!''}" width="200px" height="200px">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">上传封面图：</label>
                    <div class="col-lg-9">
                        <img src="${video.poster!''}" width="200px" height="200px">
                        <input type="file" name="file" id="poster_upload" multiple class="file-loading" data-allowed-file-extensions='["jpg","png"]'/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">预览视频：</label>
                    <div class="col-lg-9">
                        <video src="${video.url!''}" width="200px" height="200px">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">上传视频：</label>
                    <div class="col-lg-9">
                        <input type="file" name="file" id="video_upload" multiple class="file-loading" data-allowed-file-extensions='["mp4"]'/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-sm-9">
                        <button type="button" class="btn btn-white">取 消</button>
                        <button type="submit" class="btn btn-green" id="videoUpdateSubmit">保 存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        var ctx = '${ctx}';
        // 获取分类json
        $.ajax({
            url: ctx + '/category/list-json.do',
            success: function (result) {
                if (result.result === 1) {
                    var data = result.data;
                    $.each(data, function (i, n) {
                        $("#category").append("<option value='" + n.id + "'>" + n.name + "</option>");
                    });
                    // 默认选中
                    var categoryId = '${video.categoryId}';
                    $('option').each(function () {
                        var _this = $(this);
                        if(_this.val() == categoryId){
                            _this.attr('selected','selected')
                        }
                    });
                }
            }
        });

        // 文件上传
        var oFileInput = new FileInput();
        oFileInput.Init("poster_upload", "/upload/local-upload.do");
        oFileInput.Init("video_upload", "/upload/local-upload.do");

        // form提交
        function validate() {
            console.log("提交。。。");
        }

        var options = {
            url: ctx + '/video/update.do',
            type: 'POST',
            dataType: 'json',
            resetForm: true,
            timeout: 3000,
            beforeSubmit: validate,
            success: function (json) {
                console.log(json);
                if(json.result === 1){
                    bootbox.alert({
                        message: "修改成功",
                        size: 'small'
                    });
                }else{
                    bootbox.alert({
                        message: "修改失败",
                        size: 'small'
                    });
                }
            }
        };
        $("#videoUpdateForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    });



    //初始化fileinput
    var FileInput = function () {
        var oFile = new Object();

        //初始化fileinput控件（第一次初始化）
        oFile.Init = function(ctrlName, uploadUrl) {
            var control = $('#' + ctrlName);

            //初始化上传控件的样式
            control.fileinput({
                language: 'zh', //设置语言
                uploadUrl: uploadUrl, //上传的地址
                // allowedFileExtensions: ['jpg', 'gif', 'png'],//接收的文件后缀
                showUpload: true, //是否显示上传按钮
                showCaption: false,//是否显示标题
                browseClass: "btn btn-primary", //按钮样式
                //dropZoneEnabled: false,//是否显示拖拽区域
                //minImageWidth: 50, //图片的最小宽度
                //minImageHeight: 50,//图片的最小高度
                //maxImageWidth: 1000,//图片的最大宽度
                //maxImageHeight: 1000,//图片的最大高度
                //maxFileSize: 0,//单位为kb，如果为0表示不限制文件大小
                //minFileCount: 0,
                maxFileCount: 10, //表示允许同时上传的最大文件个数
                enctype: 'multipart/form-data',
                validateInitialCount:true,
                previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                msgFilesTooMany: "选择上传的文件数量({n}) 超过允许的最大数值{m}！",
            });

            //导入文件上传完成之后的事件
            control.on("fileuploaded", function (event, data, previewId, index) {
                $("#myModal").modal("hide");
                let res = data.response;
                if(res.result === 1){
                    uploadVirtualPath = res.data;
                    if (ctrlName === 'poster_upload')  {
                        $("#poster").val(res.data)
                    } else {
                        $("#url").val(res.data)
                    }
                    bootbox.alert({
                        message: "上传成功",
                        size: 'small'
                    });
                }else{
                    bootbox.alert({
                        message: "上传失败",
                        size: 'small'
                    });
                }

            });
        };
        return oFile;
    };

</script>
