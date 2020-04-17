<!-- 上文件插件-->


<p class="font-weight-bold">您所选择的文件列表：</p>

<br/>

<div id="container">
    <input id="input-1a" type="file" class="file" data-show-preview="false">
</div>

<span id="console"></span>

<script>
    projectfileoptions : {
        showUpload : false,
		showRemove : false,
		language : 'zh',
		allowedPreviewTypes : [ 'image' ],
		allowedFileExtensions : [ 'jpg', 'png', 'gif' ],
		maxFileSize : 2000,
    },
    // 文件上传框
    $('input[class=file]').each(function() {
        var imageurl = $(this).attr("value");

        if (imageurl) {
            var op = $.extend({
                initialPreview : [ // 预览图片的设置
                    "<img src='" + imageurl + "' class='file-preview-image'>", ]
            }, projectfileoptions);

            $(this).fileinput(op);
        } else {
            $(this).fileinput(projectfileoptions);
        }
    });

</script>
