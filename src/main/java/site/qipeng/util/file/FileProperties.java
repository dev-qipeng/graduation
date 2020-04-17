package site.qipeng.util.file;

/**
 * FileProperties
 *
 * @author Chill
 */
public class FileProperties {


    /**
     * 上传路径(相对路径)
     */
    public static String uploadPath = "/upload";

    /**
     * 下载路径
     */
    public static String downloadPath = "/download";

    /**
     * 图片压缩
     */
    public static Boolean compress = false;

    /**
     * 图片压缩比例
     */
    public static Double compressScale = 2.00;

    /**
     * 图片缩放选择:true放大;false缩小
     */
    public static Boolean compressFlag = false;

    /**
     * 项目物理路径
     */
    public static String realPath = "";

    /**
     * 项目相对路径
     */
    public static String contextPath = "";


    public static String getUploadRealPath() {
        return (realPath + uploadPath);
    }

    public static String getUploadCtxPath() {
        return contextPath + uploadPath;
    }

}
