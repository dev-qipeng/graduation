<!-- 用户管理-->
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
        <div>
            <form class="navbar-form navbar-right" role="search">
                <input type="text" class="form-control">
                <button type="submit" class="btn btn-default">
                    搜索
                </button>
            </form>
        </div>
    </div>
</nav>

<div class="table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th>排序</th>
            <th>昵称</th>
            <th>密码</th>
            <th>头像</th>
            <th>性别</th>
            <th>城市</th>
            <th>创建时间</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        <#list pageInfo.list as user>
            <tr>
                <td width="10%">${user_index+1}</td>
                <td width="15%">${user.nickname !''}</td>
                <td width="10%">${user.password !}</td>
                <td width="10%">
                    <a href="${user.headImg !'#'}" target="_blank"><img src="${user.headImg !'#'}" alt="头像" width="60px" height="60px"></a>
                </td>
                <td width="10%"><#if user.sex !1==1 >男<#else>女</#if></td>
                <td width="10%">${user.city !''}</td>
                <td width="10%">${(user.createTime?string("yyyy-MM-dd"))!} </td>
                <td width="10%">
                    <button class="btn btn-success update-user" id="${user.id}">修改</button>
                    <button class="btn btn-danger delete-user" id="${user.id}">删除</button>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

<!--页码块-->
<footer class="footer">
    <ul class="pagination">
        <#if pageInfo.hasPreviousPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${ctx}/user/list.do?pageNum=${pageInfo.pageNum-1}">Previous</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
        </#if>
        <#list pageInfo.navigatepageNums as page_num>
            <#if (page_num == pageInfo.pageNum)>
                <li class="page-item active"><a class="page-link" href="#">${page_num}</a></li>
            <#else>
                <li class="page-item"><a class="page-link" href="${ctx}/user/list.do?pageNum=${page_num}">${page_num}</a></li>
            </#if>
        </#list>
        <#if pageInfo.hasNextPage?then('true', 'false') == 'true'>
            <li class="page-item"><a class="page-link" href="${ctx}/user/list.do?pageNum=${pageInfo.pageNum+1}">Next</a></li>
        <#else>
            <li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
        </#if>
    </ul>
</footer>
<script>
    $(function(){
        var ctx = '${ctx}';
        $(".update-user").click(function(){
            var id = this.id;
            var url = ctx+'/user/'+id+'/goto-update-user.do';
            $.get(url,function(html){
                $(".content").html(html)
            });
        });
        $(".delete-user").click(function(){
            var id = this.id;
            bootbox.confirm("确定要删除吗？", function(result){
                if(result === true){
                    var url = ctx+'/user/'+id+'/delete.do';
                    $.ajax({
                        type: 'DELETE',
                        url: url,
                        success: function(json){
                            console.log(json);
                            if(json.result === 1){
                                bootbox.alert({
                                    message: "删除成功",
                                    size: 'small'
                                });
                            }else{
                                bootbox.alert({
                                    message: "删除失败",
                                    size: 'small'
                                });
                            }
                        }
                    });
                }
            });
        });

        // 分页点击
        $(".page-link").click(function() {
            // 显示右边内容
            var url = $(this).attr("href");
            $.get(url,function(html){
                $(".content").html(html)
            });
            return false;
        });
    })
</script>