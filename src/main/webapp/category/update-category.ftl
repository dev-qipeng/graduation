<!--弹出修改用户窗口-->
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a href="javascript:history.back(-1)"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">修改视频</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-8 col-lg-offset-2">
            <form class="form-horizontal" id="categoryUpdateForm" role="form">
                <input type="hidden" name="id" value="${category.id !''}">
                <div class="form-group ">
                    <label for="name">分类名称：</label>
                    <input type="text" class="form-control" id="name" name="name" value="${category.name!''}">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-white">取 消</button>
                    <button type="submit" class="btn btn-green" id="categoryUpdateSubmit">保 存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        var ctx = '${ctx}';

        // form提交
        function validate() {
            console.log("提交。。。");
        }

        var options = {
            url: ctx + '/category/update.do',
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
        $("#categoryUpdateForm").submit(function () {
            $(this).ajaxSubmit(options);
            return false;
        });
    })
</script>
