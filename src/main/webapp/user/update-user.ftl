<!--弹出修改用户窗口-->
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">修改视频</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-10 col-lg-offset-1">
            <form class="form-horizontal" id="userUpdateForm" role="form">
                <input type="hidden" name="id" value="${user.id!''}">
                <div class="form-group ">
                    <label for="nickname" class="col-lg-2">昵称：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="nickname" name="nickname"
                               value="${user.nickname!''}">
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="password" class="col-lg-2">密码：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="password" name="password"
                               value="${user.password!''}">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-2">上传头像：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="imgUrl" name="imgUrl"
                               value="${user.headImg !''}">
                        <#include "/upload/upload.ftl"/>
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="sex" class="col-lg-2">性别：</label>
                    <div class="col-lg-9">
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="sex" value="1" <#if user.sex == 1>checked</#if>> 男
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="sex" id="sex"  value="2" <#if user.sex == 2>checked</#if>> 女
                        </label>
                    </div>
                </div><div class="form-group form-select-button">
                <label for="city" class="col-lg-2">城市：</label>
                <div class="col-lg-9">
                    <input type="text" class="form-control" id="city" name="city"
                           value="${user.city!''}">
                </div>
            </div>

                <div class="form-group">
                    <div class="col-lg-offset-2 col-sm-9">
                        <button type="button" class="btn btn-white">取 消</button>
                        <button type="submit" class="btn btn-green" id="userUpdateSubmit">保 存</button>
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
            url: ctx + '/user/update.do',
            type: 'POST',
            dataType: 'json',
            resetForm: false,
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
        $("#userUpdateForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    })
</script>
