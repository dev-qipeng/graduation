<!--添加banner-->
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">添加Banner</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-8 col-lg-offset-2">
            <form class="form-horizontal" id="bannerAddForm" role="form">
                <div class="form-group">
                    <label class="col-lg-2">上传Banner图：</label>
                    <div class="col-lg-9">
                        <#include "/upload/upload.ftl"/>
                        <input type="hidden" id="imgUrl" name="imgUrl">
                    </div>
                </div>
                <div class="form-group ">
                    <label for="videoId" class="col-lg-2">链接的视频ID</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="videoId" name="videoId">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-sm-9">
                        <button type="button" class="btn btn-white">取 消</button>
                        <button type="submit" class="btn btn-green" id="bannerAddSubmit">保 存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx}/upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${ctx}/upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${ctx}/upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${ctx}/upload/lib/base64.js"></script>
<script type="text/javascript" src="${ctx}/upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${ctx}/upload/upload.js"></script>
<script>
    $(function () {
        var ctx = '${ctx}';

        // form提交
        function validate() {
            console.log("提交。。。");
        }

        var options = {
            url: ctx + '/banner/insert.do',
            type: 'POST',
            dataType: 'json',
            resetForm: true,
            timeout: 3000,
            beforeSubmit: validate,
            success: function (json) {
                console.log(json);
                if(json.result === 1){
                    bootbox.alert({
                        message: "添加成功",
                        size: 'small'
                    });
                }else{
                    bootbox.alert({
                        message: "添加失败",
                        size: 'small'
                    });
                }
            }
        };
        $("#bannerAddForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    })
</script>