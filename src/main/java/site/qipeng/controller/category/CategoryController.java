package site.qipeng.controller.category;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.Category;
import site.qipeng.service.CategoryService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;
import site.qipeng.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    public static Integer pageSize = 5;

    /**
     * 获取分类列表，分页获取
     * @param pageNum
     * @param model
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum, Model model){
        Map<String, Object> map = new HashMap<String, Object>();
        List<Category> categoryList = categoryService.getCategoryList(map, pageNum, pageSize);
        PageInfo<Category> pageInfo = new PageInfo<Category>(categoryList);
        model.addAttribute("pageInfo",pageInfo);
        return "category/category-list";
    }

    /**
     * 获取所有分类数据 返回json
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "list-json", method = RequestMethod.GET)
    public JsonResult listJson(){
        List<Category> categoryList = categoryService.getCategoryListJson();
        return JsonResultUtil.getObjectJson(categoryList);
    }

    /**
     * 添加分类
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public JsonResult insert(@RequestParam(value = "name", required = true) String name){
        try{
            if(StringUtil.isEmpty(name)){
                JsonResultUtil.getErrorJson("参数错误");
            }
            int i = categoryService.insertCategory(name);
            if(i > 0){
                return JsonResultUtil.getSuccessJson("添加成功");
            }else{
                return JsonResultUtil.getErrorJson("添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 修改分类
     * @param id
     * @param name
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(@RequestParam(value = "id", required = true) Integer id, @RequestParam(value = "name", required = true) String name){
        try{
            if(StringUtil.isEmpty(name) || StringUtil.isEmpty(id.toString())){
                JsonResultUtil.getErrorJson("参数错误");
            }
            int i = categoryService.updateCategory(id, name);
            if(i > 0){
                return JsonResultUtil.getSuccessJson("修改成功");
            }else{
                return JsonResultUtil.getErrorJson("修改失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id){
        try{
            Category category = categoryService.getCategoryById(id);
            if(category == null){
                return JsonResultUtil.getErrorJson("参数错误");
            }
            int i = categoryService.deleteCategory(id);
            if(i > 0){
                return JsonResultUtil.getSuccessJson("删除成功");
            }else{
                return JsonResultUtil.getErrorJson("删除失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JsonResultUtil.getErrorJson(e.getMessage());
        }

    }

    /**
     * 跳转到添加分类
     * @return
     */
    @RequestMapping(value = "goto-add-category", method = RequestMethod.GET)
    public String gotoAddCategory(){
        return "category/add-category";
    }

    /**
     * 跳转到修改分类
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "{id}/goto-update-category", method = RequestMethod.GET)
    public String gotoUpdateCategory(@PathVariable("id") Integer id, Model model){
        Category category = categoryService.getCategoryById(id);
        if(category == null){
            return "/500";
        }
        model.addAttribute("category",category);
        return "category/update-category";
    }
}
