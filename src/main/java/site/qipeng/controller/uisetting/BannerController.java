package site.qipeng.controller.uisetting;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Banner;
import site.qipeng.service.BannerService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

@AllArgsConstructor
@Controller
@RequestMapping(value = "banner")
public class BannerController {

    private BannerService bannerService;

    @ResponseBody
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getBanner(@PathVariable("id") Integer id) {
        Banner banner = bannerService.getById(id);
        if (banner == null) {
            return JsonResultUtil.getErrorJson("该视频不存在");
        }
        return JsonResultUtil.getObjectJson(banner);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Page page, Model model) {
        LambdaQueryWrapper<Banner> wrapper = Wrappers.<Banner>lambdaQuery().orderByAsc(Banner::getId);

        model.addAttribute("pageInfo", bannerService.page(page, wrapper));
        return "uisetting/banner/banner-list";
    }

    @ResponseBody
    @PostMapping(value = "insert")
    public JsonResult insert(Banner banner) {
        boolean b = bannerService.save(banner);
        if (!b) {
            return JsonResultUtil.getErrorJson("添加失败");
        }
        return JsonResultUtil.getSuccessJson("添加成功");
    }

    @ResponseBody
    @PostMapping(value = "update")
    public JsonResult update(Banner banner) {
        try {
            boolean b = bannerService.updateById(banner);
            if (!b) {
                return JsonResultUtil.getErrorJson("修改失败");
            }
            return JsonResultUtil.getSuccessJson("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }
    }

    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id) {
        try {
            boolean b = bannerService.removeById(id);
            if (!b) {
                return JsonResultUtil.getErrorJson("删除失败");
            }
            return JsonResultUtil.getSuccessJson("删除成功");
        } catch (Exception e) {
            return JsonResultUtil.getErrorJson(e.getMessage());
        }
    }

    /**
     * 跳转到添加Banner页面
     *
     */
    @RequestMapping(value = "goto-add-banner")
    public String gotoAddView() {
        return "uisetting/banner/add-banner";
    }

    /**
     * 跳转到修改Banner页面
     *
     */
    @RequestMapping(value = "{id}/goto-update-banner")
    public String gotoUpdateView(@PathVariable("id") Integer id, Model model) {
        try {
            Banner banner = bannerService.getById(id);
            if (banner == null) {
                return "/500";
            }
            model.addAttribute("banner", banner);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "uisetting/banner/update-banner";
    }
}
