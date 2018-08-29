package site.qipeng.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.entity.User;
import site.qipeng.entity.UserDTO;
import site.qipeng.service.UserService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    public static Integer pageSize = 5;


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false,defaultValue = "1",value = "pageNum")Integer pageNum,
                       Integer pageSize, Model model){

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> userList = userService.getUserList(map, pageNum, this.pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "user/user-list";
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(UserDTO userDTO){
        int r = userService.update(userDTO);
        if (r == 0){
            return JsonResultUtil.getErrorJson("修改失败");
        }
        return JsonResultUtil.getSuccessJson("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id){
        int r = userService.delete(id);
        if (r == 0){
            return JsonResultUtil.getErrorJson("删除失败");
        }
        return JsonResultUtil.getSuccessJson("删除成功");
    }

    @RequestMapping(value = "{id}/goto-update-user", method = RequestMethod.GET)
    public String gotoUpdateUser(@PathVariable("id") Integer id, Model model){
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/update-user";
    }


}
