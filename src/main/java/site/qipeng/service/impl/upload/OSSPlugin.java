package site.qipeng.service.impl.upload;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.*;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.UUID;

@Component
public class OSSPlugin {
    Logger logger = Logger.getLogger(this.getClass());

    private String endpoint = "oss-cn-beijing.aliyuncs.com";
    private String bucketName = "qipeng";
    private String picLocation = "";
    private String accessKeyId = "LTAIQJULJbDy55YF";
    private String accessKeySecret = "1Edf9pnyxConT4337bhAtt3vUhZAAY";

    public String upload(MultipartFile stream) {

        /** 获取文件名词 */
        String name=stream.getOriginalFilename();
        /** 获取文件后缀 */
        String ext = name.substring(name.lastIndexOf(".")+1);
        /** 将文件MultipartFile转为InputStream  */
        InputStream input = null;
        try {
            input = stream.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        /** 拼接文件的文件名  */
        String fileName = picLocation + UUID.randomUUID().toString().toUpperCase().replace("-", "")+"."+ext; //文件名，根据UUID来
        return putObject(input, ext, fileName, endpoint, bucketName, picLocation, accessKeyId, accessKeySecret);
    }

    public void deleteFile(String filePath) {
        this.delete(filePath,endpoint,accessKeyId,accessKeySecret);
    }

    /**
     * 删除上传文件
     * @param filePath 删除文件全路径
     * @param endpoint 储存空间名称
     * @param accessKeyId 密钥id
     * @param accessKeySecret 密钥
     * @return
     */
    public boolean delete(String filePath,String endpoint,String accessKeyId,String accessKeySecret) {
        String bucketNames = this.getBucketName(filePath);       //根据url获取bucketName
        String fileName = this.getFileName(filePath);           //根据url获取fileName
        if(bucketNames==null||fileName==null)
            return false;
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            GenericRequest request = new DeleteObjectsRequest(bucketNames).withKey(fileName);
            ossClient.deleteObject(request);
        } catch (Exception oe) {
            oe.printStackTrace();
            return false;
        } finally {
            ossClient.shutdown();
        }
        return true;
    }


    /**
     * 上传图片
     * @param input 上传图片文件的输入流
     * @param fileType 文件类型，也就是后缀
     * @param fileName 文件名称
     * @param endpoint 域名
     * @param bucketName 储存空间名称
     * @param picLocation 二级路径名称
     * @param accessKeyId 密钥id
     * @param accessKeySecret 密钥
     * @return 访问图片的url路径
     */
    private String putObject(InputStream input,String fileType, String fileName,String endpoint,String bucketName,String picLocation,String accessKeyId,String accessKeySecret) {
        String urls = null;      //默认null
        String saveUrl = null;  //最终返回的路径
        OSSClient ossClient = null;
        try {
            ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            ObjectMetadata meta = new ObjectMetadata();             // 创建上传Object的Metadata
            meta.setContentType(this.contentType(fileType));       // 设置上传内容类型
            meta.setCacheControl("no-cache");                   // 被下载时网页的缓存行为
            PutObjectRequest request = new PutObjectRequest(bucketName, fileName,input,meta);           //创建上传请求
            ossClient.putObject(request);
            /** 设置Object权限 */
            boolean found = ossClient.doesObjectExist(bucketName, fileName);
            if(found = true) {
                ossClient.setObjectAcl(bucketName, fileName, CannedAccessControlList.PublicRead);
            }
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            URL url = ossClient.generatePresignedUrl(bucketName, fileName, expiration);
            /** 对返回的签名url处理获取最终展示用的url */
            urls = url.toString();
            String[]  strs=urls.split("\\?");
            for(int i=0,len=strs.length;i<len;i++){
                saveUrl = strs[0].toString();
            }
            logger.info("OSS上传成功的地址" + saveUrl);
        } catch (OSSException oe) {
            logger.error("OSSException异常");
            oe.printStackTrace();
            return null;
        } catch (ClientException ce) {
            logger.error("ClientException异常");
            ce.printStackTrace();
            return null;
        }  catch(Exception e) {
            e.printStackTrace();
        } finally {
            ossClient.shutdown();
        }
        return saveUrl;
    }


    /**
     * 根据url获取bucketName
     * @param fileUrl 文件url
     * @return String bucketName  域名
     */
    private static String getBucketName(String fileUrl){
        String http = "http://";
        String https = "https://";
        int httpIndex = fileUrl.indexOf(http);
        int httpsIndex = fileUrl.indexOf(https);
        int startIndex  = 0;
        if(httpIndex==-1){
            if(httpsIndex==-1){
                return null;
            }else{
                startIndex = httpsIndex+https.length();
            }
        }else{
            startIndex = httpIndex+http.length();
        }
        int endIndex = fileUrl.indexOf(".oss-");
        return fileUrl.substring(startIndex, endIndex);
    }

    /**
     *  根据url获取fileName
     * @param fileUrl 文件url
     * @return String fileName  文件名称
     */
    private String getFileName(String fileUrl){
        String str = "aliyuncs.com/";
        int beginIndex = fileUrl.indexOf(str);
        if(beginIndex==-1) return null;
        return fileUrl.substring(beginIndex+str.length());
    }



    /**
     *   获取文件类型
     * @param fileType  文件后缀
     * @return String  文件后缀需要在请求存储时进行转换
     */
    private static String contentType(String fileType){
        fileType = fileType.toLowerCase();
        String contentType = "";
        if ("bmp".equals(fileType)) {
            contentType = "image/bmp";

        } else if ("gif".equals(fileType)) {
            contentType = "image/gif";

        } else if ("png".equals(fileType) || "jpeg".equals(fileType) || "jpg".equals(fileType)) {
            contentType = "image/jpeg";

        } else if ("html".equals(fileType)) {
            contentType = "text/html";

        } else if ("txt".equals(fileType)) {
            contentType = "text/plain";

        } else if ("vsd".equals(fileType)) {
            contentType = "application/vnd.visio";

        } else if ("ppt".equals(fileType) || "pptx".equals(fileType)) {
            contentType = "application/vnd.ms-powerpoint";

        } else if ("doc".equals(fileType) || "docx".equals(fileType)) {
            contentType = "application/msword";

        } else if ("xml".equals(fileType)) {
            contentType = "text/xml";

        } else if ("mp4".equals(fileType)) {
            contentType = "video/mp4";

        } else {
            contentType = "application/octet-stream";

        }
        return contentType;
    }

}
