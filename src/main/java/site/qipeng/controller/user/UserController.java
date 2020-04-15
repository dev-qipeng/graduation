package site.qipeng.controller.user;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.config.security.SpringSecurityUtil;
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

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public final Integer pageSize = 5;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(@RequestParam(required = false, defaultValue = "1", value = "pageNum") Integer pageNum,
                       Integer pageSize, Model model) {

        Map<String, Object> map = new HashMap<String, Object>();
        List<User> userList = userService.getUserList(map, pageNum, this.pageSize);
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        model.addAttribute("pageInfo", pageInfo);
        return "user/user-list";
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public JsonResult update(UserDTO userDTO) {
        int r = userService.update(userDTO);
        if (r == 0) {
            return JsonResultUtil.getErrorJson("修改失败");
        }
        return JsonResultUtil.getSuccessJson("修改成功");
    }

    @ResponseBody
    @RequestMapping(value = "{id}/delete", method = RequestMethod.DELETE)
    public JsonResult delete(@PathVariable("id") Integer id) {
        int r = userService.delete(id);
        if (r == 0) {
            return JsonResultUtil.getErrorJson("删除失败");
        }
        return JsonResultUtil.getSuccessJson("删除成功");
    }

    @RequestMapping(value = "{id}/goto-update-user", method = RequestMethod.GET)
    public String gotoUpdateUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @RequestMapping(value = "/goto-change-pwd", method = RequestMethod.GET)
    public String gotoChangePwd(Model model) {
        org.springframework.security.core.userdetails.User user = SpringSecurityUtil.getCurrentUser();
        model.addAttribute("user", user);
        return "user/change-pwd";
    }

    @ResponseBody
    @RequestMapping(value = "/change-pwd", method = RequestMethod.POST)
    public JsonResult changePwd(String oldPwd, String newPwd, Model model) {
        String username = SpringSecurityUtil.getCurrentUsername();

        User user = userService.getUserByUsername(username);

        //判断旧密码是否正确
        if (!passwordEncoder.matches(oldPwd, user.getPassword())) {
            return JsonResultUtil.getErrorJson("旧密码错误");
        }

        // 加密新密码
        final String encodePassword = passwordEncoder.encode(newPwd);
        user.setPassword(encodePassword);

        try {
            int r = userService.changePwd(user);
            if (r == 0) {
                return JsonResultUtil.getSuccessJson("修改失败");
            }
        } catch (Exception e) {
            JsonResultUtil.getErrorJson("修改失败: " + e.getLocalizedMessage());
        }

        return JsonResultUtil.getSuccessJson("修改成功");
    }


}
