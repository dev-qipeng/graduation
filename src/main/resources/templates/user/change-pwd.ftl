<#--noinspection ALL-->
<!--弹出修改用户窗口-->
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li class="active">修改密码</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-10 col-lg-offset-1">
            <form class="form-horizontal" id="changePwdForm" role="form">
                <div class="form-group ">
                    <label for="nickname" class="col-lg-2">昵称：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="nickname" name="nickname"
                        <#--noinspection FtlReferencesInspection-->
                               value="${Session.SPRING_SECURITY_CONTEXT.authentication.principal.username !''}"
                               readonly>
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="oldPwd" class="col-lg-2">旧密码：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="oldPwd" name="oldPwd">
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="newPwd" class="col-lg-2">新密码：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="newPwd" name="newPwd">
                    </div>
                </div>
                <div class="form-group form-select-button">
                    <label for="newPwd2" class="col-lg-2">重新输入新密码：</label>
                    <div class="col-lg-9">
                        <input type="text" class="form-control" id="newPwd2" name="newPwd2">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-lg-offset-2 col-sm-9">
                        <button type="button" class="btn btn-white">取 消</button>
                        <button type="submit" class="btn btn-green" id="changePwdSubmit">保 存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    $(function () {
        var ctx = '${springMacroRequestContext.contextPath}';

        // form提交
        function validate(formData, jqForm, options) {
            for (var i = 0; i < formData.length; i++) {
                if (!formData[i].value) {
                    alert('旧密码,新密码和重复密码都不能为空!');
                    return false;
                }
            }

            var newPwd1 = formData[2].value;
            var newPwd2 = formData[3].value;
            if (!(newPwd1 == newPwd2)) {
                alert("两次密码不一致");
                return false;
            }
            return true;
        };

        var options = {
            url: ctx + '/user/change-pwd',
            type: 'POST',
            dataType: 'json',
            resetForm: false,
            timeout: 3000,
            beforeSubmit: validate,
            success: function (json) {
                console.log(json);
                if (json.result === 1) {
                    bootbox.alert({
                        message: "修改成功",
                        size: 'small'
                    });
                    // 两秒后跳转到登录
                    setInterval(function(){
                        window.location.href = ctx + "/logout";
                    }, 2000)

                } else {
                    bootbox.alert({
                        message: "修改失败:" + json.message,
                        size: 'small'
                    });
                }
            }
        };
        $("#changePwdForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    })
</script>
