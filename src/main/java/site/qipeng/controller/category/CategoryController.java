package site.qipeng.controller.category;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Category;
import site.qipeng.service.CategoryService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;
import site.qipeng.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Controller
@RequestMapping(value = "category")
public class CategoryController {

    private CategoryService categoryService;


    /**
     * 获取分类列表，分页获取
     *
     */
    @GetMapping(value = "list")
    public String list(Page page, Model model) {
        model.addAttribute("pageInfo", categoryService.page(page));
        return "category/category-list";
    }

    /**
     * 获取所有分类数据 返回json
     *
     */
    @ResponseBody
    @GetMapping(value = "list-json")
    public JsonResult listJson() {
        List<Category> categoryList = categoryService.list();
        return JsonResultUtil.getObjectJson(categoryList);
    }

    /**
     * 添加分类
     *
     * @param name 分类名
     */
    @ResponseBody
    @PostMapping(value = "insert")
    public JsonResult insert(@RequestParam(value = "name") String name) {
        try {
            Category category = new Category();
            category.setName(name);
            category.setCreateTime(new Date());
            boolean b = categoryService.save(category);
            if (!b) {
                return JsonResultUtil.getSuccessJson("添加成功");
            } else {
                return JsonResultUtil.getErrorJson("添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 修改分类
     *
     * @param id id
     * @param name name
     */
    @ResponseBody
    @PostMapping(value = "update")
    public JsonResult update(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name) {
        try {
            if (StringUtils.isEmpty(name) || StringUtils.isEmpty(id.toString())) {
                JsonResultUtil.getErrorJson("参数错误");
            }
            Category category = new Category();
            category.setName(name);
            category.setUpdateTime(new Date());
            boolean b = categoryService.update(category, Wrappers.<Category>lambdaUpdate().eq(Category::getId, id));
            if (!b) {
                return JsonResultUtil.getSuccessJson("修改成功");
            } else {
                return JsonResultUtil.getErrorJson("修改失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 删除分类
     *
     * @param id id
     */
    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id) {
        try {
            boolean b = categoryService.removeById(id);
            if (!b) {
                return JsonResultUtil.getSuccessJson("删除成功");
            } else {
                return JsonResultUtil.getErrorJson("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 跳转到添加分类
     *
     */
    @RequestMapping(value = "goto-add-category")
    public String gotoAddCategory() {
        return "category/add-category";
    }

    /**
     * 跳转到修改分类
     *
     * @param id
     * @param model
     */
    @RequestMapping(value = "{id}/goto-update-category")
    public String gotoUpdateCategory(@PathVariable("id") Integer id, Model model) {
        Category category = categoryService.getById(id);
        if (category == null) {
            return "/500";
        }
        model.addAttribute("category", category);
        return "category/update-category";
    }
}
