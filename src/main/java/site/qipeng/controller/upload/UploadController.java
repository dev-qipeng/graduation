package site.qipeng.controller.upload;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import site.qipeng.service.impl.upload.OSSPlugin;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;
import site.qipeng.util.file.LocalFile;
import site.qipeng.util.file.UploadFileUtil;

@Controller
@RequestMapping(value = "upload")
public class UploadController {

    Logger logger = Logger.getLogger(UploadController.class);

    @Autowired
    private OSSPlugin ossPlugin;

    @ResponseBody
    @RequestMapping(value = "/upload")
    public JsonResult uploadFile(MultipartFile file) {
        String path;
        if (file != null && file.getOriginalFilename().length() > 0) {
            if (!isImg(file.getOriginalFilename())) {
                return JsonResultUtil.getErrorJson("不允许上传的文件格式，请上传gif,jpg,bmp格式文件。");
            }
            path = ossPlugin.upload(file);
            logger.info("文件保存路径：" + file.getOriginalFilename());
            return JsonResultUtil.getObjectJson(path);
        } else {
            return JsonResultUtil.getErrorJson("没有文件");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/local-upload")
    public JsonResult uploadLocalFile(MultipartFile file) {
        LocalFile localFile = null;
        if (isImg(file.getOriginalFilename())) {
            localFile = UploadFileUtil.getFile(file, "image");
        } else {
            localFile = UploadFileUtil.getFile(file, "video");
        }
        localFile.transfer();
        System.out.println(localFile.toString());
        logger.info("文件保存路径：" + file.getOriginalFilename());
        return JsonResultUtil.getObjectJson(localFile.getUploadVirtualPath());
    }


    /**
     * 是否是图片
     *
     * @param imgFileName
     */
    private static boolean isImg(String imgFileName) {
        imgFileName = imgFileName.toLowerCase();
        String allowTYpe = "gif,jpg,png,jpeg,swf";
        if (!imgFileName.trim().equals("") && imgFileName.length() > 0) {
            String ex = imgFileName.substring(imgFileName.lastIndexOf(".") + 1, imgFileName.length());
            return allowTYpe.toUpperCase().contains(ex.toUpperCase());
        } else {
            return false;
        }
    }
}
