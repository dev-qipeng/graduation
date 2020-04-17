package site.qipeng.controller.video;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Category;
import site.qipeng.entity.Video;
import site.qipeng.service.CategoryService;
import site.qipeng.service.VideoService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

import java.util.Date;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Controller
@RequestMapping(value = "video")
public class VideoController {

    private VideoService videoService;

    private CategoryService categoryService;

    @ResponseBody
    @GetMapping(value = "{id}")
    public JsonResult getVideo(@PathVariable("id") Integer id) {
        return JsonResultUtil.getObjectJson(videoService.getById(id));
    }

    @GetMapping(value = "list")
    public String list(Page page, @RequestParam(required = false, defaultValue = "", value = "keyword") String keyword,
                       @RequestParam(required = false, defaultValue = "", value = "categoryId") Integer categoryId,
                       Model model) {

        LambdaQueryWrapper<Video> wrapper = Wrappers.<Video>lambdaQuery().orderByDesc(Video::getCreateTime);

        if (keyword != null && !"".equals(keyword)) {
            wrapper.like(Video::getName, keyword);
        }
        if (categoryId != null) {
            wrapper.eq(Video::getCategoryId, categoryId);
        }

        IPage pageInfo = videoService.page(page, wrapper);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        // 返回分类列表
        List<Category> categoryList = categoryService.list();
        model.addAttribute("categoryList", categoryList);
        return "video/video-list";
    }

    @ResponseBody
    @PostMapping(value = "insert")
    public JsonResult insert(Video video) {
        video.setLikeNum(0);
        video.setPlayNum(0);
        video.setScore(9.0);
        video.setCreateTime(new Date());
        video.setDescription(video.getDescription());
        return JsonResultUtil.getStatusJson(videoService.save(video));
    }

    @ResponseBody
    @PostMapping(value = "update")
    public JsonResult update(Video video) {
        return JsonResultUtil.getStatusJson(videoService.updateById(video));
    }

    @ResponseBody
    @DeleteMapping(value = "{id}/delete")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return JsonResultUtil.getStatusJson(videoService.removeById(id));
    }

    /**
     * 跳转到添加视频页面
     */
    @RequestMapping(value = "goto-add-video")
    public String gotoAddView() {
        return "video/add-video";
    }

    /**
     * 跳转到修改视频页面
     */
    @RequestMapping(value = "{id}/goto-update-video")
    public String gotoUpdateView(@PathVariable("id") Integer id, Model model) {
        try {
            Video video = videoService.getById(id);
            if (video == null) {
                return "/500";
            }
            model.addAttribute("video", video);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "video/update-video";
    }


}
