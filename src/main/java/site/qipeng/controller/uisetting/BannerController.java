package site.qipeng.controller.uisetting;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Banner;
import site.qipeng.entity.BannerDTO;
import site.qipeng.service.BannerService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "banner")
public class BannerController {

    @Autowired
    private BannerService bannerService;

    public static Integer pageSize = 4;

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getBanner(@PathVariable("id") Integer id){
        Banner banner = bannerService.getBannerById(id);
        if (banner == null){
            return JsonResultUtil.getErrorJson("该视频不存在");
        }
        return JsonResultUtil.getObjectJson(banner);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum, Integer pageSize, Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Banner> bannerList = bannerService.getBannerList(map, pageNum, BannerController.pageSize);
        PageInfo<Banner> pageInfo = new PageInfo<Banner>(bannerList);
        model.addAttribute("pageInfo",pageInfo);
        return "uisetting/banner/banner-list";
    }

    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public JsonResult insert(BannerDTO banner){
        int i = bannerService.insertVideo(banner);
        if (i == 0){
            return JsonResultUtil.getErrorJson("添加失败");
        }
        return JsonResultUtil.getSuccessJson("添加成功");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(BannerDTO banner){
        try {
            int i = bannerService.updateBanner(banner);
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
            Banner banner = bannerService.getBannerById(id);
            if (banner == null){
                return JsonResultUtil.getErrorJson("该视频不存在");
            }
            int i = bannerService.deleteBanner(id);
            if (i == 0){
                return JsonResultUtil.getErrorJson("删除失败");
            }
            return JsonResultUtil.getSuccessJson("删除成功");
        }catch(Exception e){
            return JsonResultUtil.getErrorJson(e.getMessage());
        }
    }

    /**
     * 跳转到添加Banner页面
     * @return
     */
    @RequestMapping(value = "goto-add-banner")
    public String gotoAddView(){
        return "uisetting/banner/add-banner";
    }

    /**
     * 跳转到修改Banner页面
     * @return
     */
    @RequestMapping(value = "{id}/goto-update-banner")
    public String gotoUpdateView(@PathVariable("id") Integer id,Model model){
        try{
            Banner banner = bannerService.getBannerById(id);
            if(banner == null){
                return "/500";
            }
            model.addAttribute("banner",banner);
        }catch(Exception e){
            e.printStackTrace();
        }

        return "uisetting/banner/update-banner";
    }
}
