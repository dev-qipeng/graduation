<!--添加分类-->
<#assign ctx="${springMacroRequestContext.contextPath}">
<div class="panel panel-default">
    <ol class="breadcrumb">
        <li><a id="back" href="${ctx}/video/list?current=1"><span class="glyphicon glyphicon-arrow-left"></span>返回</a></li>
        <li class="active">添加分类</li>
    </ol>
    <div class="panel-body">
        <div class="col-lg-8 col-lg-offset-2">
            <form class="form-horizontal" id="categoryAddForm" role="form">
                <div class="form-group ">
                    <label for="name">分类名称：</label>
                    <input type="text" class="form-control" id="name" name="name">
                </div>
                <div class="form-group">
                    <button type="button" class="btn btn-white">取 消</button>
                    <button type="submit" class="btn btn-green" id="categoryAddSubmit">保 存</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
    $(function () {
        var ctx = '${springMacroRequestContext.contextPath}';

        // form提交
        function validate() {
            console.log("提交。。。");
        }

        var options = {
            url: ctx + '/category/insert',
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
        $("#categoryAddForm").submit(function () {
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