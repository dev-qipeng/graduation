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
                    <label class="col-lg-2">上传封面图：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="poster" name="poster"
                               value="${video.poster!''}">
                        <#include "/upload/upload-img.ftl"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">上传视频：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="url" name="url"
                               value="${video.url!}">
                        <#include "/upload/upload-video.ftl"/>
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
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/lib/base64.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/upload/upload-two.js"></script>
<script>
    $(function () {
        var ctx = '${springMacroRequestContext.contextPath}';
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
    })
</script>
