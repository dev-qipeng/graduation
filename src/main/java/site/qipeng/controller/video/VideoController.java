package site.qipeng.controller.video;

import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Category;
import site.qipeng.entity.Video;
import site.qipeng.entity.VideoDTO;
import site.qipeng.service.CategoryService;
import site.qipeng.service.VideoService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "video")
public class VideoController {

    Logger logger = Logger.getLogger(VideoController.class);

    @Autowired
    private VideoService videoService;

    @Autowired
    private CategoryService categoryService;

    public static Integer pageSize = 5;

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getVideo(@PathVariable("id") Integer id){
        Video video = videoService.getVideoById(id);
        if (video == null){
            return JsonResultUtil.getErrorJson("该视频不存在");
        }
        return JsonResultUtil.getObjectJson(video);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Integer pageSize, Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Video> videoList = videoService.getVideoList(map, pageNum, this.pageSize);
        PageInfo<Video> pageInfo = new PageInfo<Video>(videoList);
        model.addAttribute("pageInfo",pageInfo);
        // 返回分类列表
        List<Category> categoryList = categoryService.getCategoryListJson();
        model.addAttribute("categoryList",categoryList);
        return "video/video-list";
    }

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public JsonResult insert(VideoDTO video){
        int i = videoService.insertVideo(video);
        if (i == 0){
            return JsonResultUtil.getErrorJson("添加失败");
        }
        return JsonResultUtil.getSuccessJson("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(VideoDTO video){
        try {
            int i = videoService.updateVideo(video);
            if (i == 0){
                return JsonResultUtil.getErrorJson("修改失败");
            }
            return JsonResultUtil.getSuccessJson("修改成功");
        }catch(Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id){
        try {
            logger.info("id:"+id);
            Video video = videoService.getVideoById(id);
            if (video == null){
                return JsonResultUtil.getErrorJson("该视频不存在");
            }
            int i = videoService.deleteVideo(id);
            if (i == 0){
                return JsonResultUtil.getErrorJson("删除失败");
            }
            return JsonResultUtil.getSuccessJson("删除成功");
        }catch(Exception e){
            return JsonResultUtil.getErrorJson(e.getMessage());
        }
    }

    /**
     * 跳转到添加视频页面
     * @return
     */
    @RequestMapping(value = "goto-add-video")
    public String gotoAddView(){
        return "video/add-video";
    }

    /**
     * 跳转到修改视频页面
     * @return
     */
    @RequestMapping(value = "{id}/goto-update-video")
    public String gotoUpdateView(@PathVariable("id") Integer id,Model model){
        try{
            Video video = videoService.getVideoById(id);
            if(video == null){
                return "/500";
            }
            model.addAttribute("video",video);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "video/update-video";
    }


}
