<!--添加banner-->
<#assign ctx="${springMacroRequestContext.contextPath}">
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a id="back" href="${ctx}/banner/list.do?pageNum=1"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">添加banner</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-8 col-lg-offset-2">
            <form class="form-horizontal" id="bannerAddForm" role="form">
                <div class="form-group">
                    <label class="col-lg-2">上传Banner图：</label>
                    <div class="col-lg-9">
                        <#include "../upload/upload.ftl"/>
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
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/lib/crypto1/crypto/crypto.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/lib/crypto1/hmac/hmac.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/lib/crypto1/sha1/sha1.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/lib/base64.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/lib/plupload-2.1.2/js/plupload.full.min.js"></script>
<script type="text/javascript" src="${springMacroRequestContext.contextPath}/statics/upload/upload-two.js"></script>
<script>
    $(function () {
        var ctx = '${springMacroRequestContext.contextPath}';

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

        // 返回按钮
        $("#back").click(function () {
            var url = $(this).attr("href");
            $.get(url,function(html){
                $(".content").html(html);
            });
            return false;
        });
    })
</script>