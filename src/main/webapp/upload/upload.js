
accessid= 'LTAIQJULJbDy55YF';
accesskey= '1Edf9pnyxConT4337bhAtt3vUhZAAY';
host = 'http://qipeng.oss-cn-beijing.aliyuncs.com';

var g_dirname = 'img/';
var g_object_name = '';
var now = timestamp = Date.parse(new Date()) / 1000;

var policyText = {
    "expiration": "2020-01-01T12:00:00.000Z", //设置该Policy的失效时间，超过这个失效时间之后，就没有办法通过这个policy上传文件了
    "conditions": [
        ["content-length-range", 0, 1048576000] // 设置上传文件的大小限制
    ]
};

var policyBase64 = Base64.encode(JSON.stringify(policyText))
message = policyBase64
var bytes = Crypto.HMAC(Crypto.SHA1, message, accesskey, { asBytes: true }) ;
var signature = Crypto.util.bytesToBase64(bytes);

//获取随机串
function guid() {
    return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = Math.random()*16|0, v = c == 'x' ? r : (r&0x3|0x8);
        return v.toString(16);
    });
}

// 获取后缀名
function get_suffix(filename) {
    var pos = filename.lastIndexOf('.');
    var suffix = '';
    if (pos != -1) {
        suffix = filename.substring(pos)
    }
    return suffix;
}

// 获取上传后的文件名字
function get_uploaded_object_name()
{
    return g_object_name
}

// 设置参数、发起上传
function set_upload_param(up, filename)
{
    var suffix = get_suffix(filename);
    g_object_name = g_dirname + guid() + suffix;
    var new_multipart_params = {
        'key' : g_object_name,
        'policy': policyBase64,
        'OSSAccessKeyId': accessid,
        'success_action_status' : '200', //让服务端返回200,不然，默认会返回204
        'signature': signature
    };

    up.setOption({
        'url': host,
        'multipart_params': new_multipart_params
    });
}
// 图片上传
var imgUploader = new plupload.Uploader({
    runtimes : 'html5,flash,silverlight,html4',
    browse_button : 'selectimg',
    //multi_selection: false,
    container: document.getElementById('container'),
    flash_swf_url : 'lib/plupload-2.1.2/js/Moxie.swf',
    silverlight_xap_url : 'lib/plupload-2.1.2/js/Moxie.xap',
    url : 'http://oss.aliyuncs.com',

    init: {
        // videoUploader.init();执行后调用
        PostInit: function() {
            document.getElementById('imglist').innerHTML = '';
            document.getElementById('uploadimg').onclick = function() {
                imgUploader.start();
                return false;
            };
        },

        FilesAdded: function(up, files) {
            if(files.length > 1){
                alert('只能上传一张图片！');
                return;
            }
            plupload.each(files, function(file) {
                document.getElementById('imglist').innerHTML = '<div id="' + file.id + '">' + file.name + ' (' + plupload.formatSize(file.size) + ')<b></b>'
                    +'<div class="progress"><div class="progress-bar" style="width: 0%"></div></div>'
                    +'</div>';
            });
        },

        BeforeUpload: function(up, file) {
            set_upload_param(up, file.name);
        },

        UploadProgress: function(up, file) {
            var d = document.getElementById(file.id);
            d.getElementsByTagName('b')[0].innerHTML = '<span>' + file.percent + "%</span>";
            var prog = d.getElementsByTagName('div')[0];
            var progBar = prog.getElementsByTagName('div')[0]
            progBar.style.width= 2*file.percent+'px';
            progBar.setAttribute('aria-valuenow', file.percent);
        },

        FileUploaded: function(up, file, info) {
            if (info.status == 200)
            {
                document.getElementById("imgDiv").innerHTML = '<img src="'+host +'/' + get_uploaded_object_name()+'" width="300px" height="150px">';
                document.getElementById("imgUrl").value=host +'/' + get_uploaded_object_name();
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = 'success, url:' + host +'/' + get_uploaded_object_name();
            }
            else
            {
                document.getElementById(file.id).getElementsByTagName('b')[0].innerHTML = info.response;
            }
        },

        Error: function(up, err) {
            document.getElementById('console').appendChild(document.createTextNode("\nError xml:" + err.response));
        }
    }
});
imgUploader.init();
