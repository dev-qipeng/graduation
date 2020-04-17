package site.qipeng.controller.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.qipeng.config.security.SpringSecurityUtil;
import site.qipeng.entity.User;
import site.qipeng.service.UserService;
import site.qipeng.util.JsonResult;
import site.qipeng.util.JsonResultUtil;

@AllArgsConstructor
@Controller
@RequestMapping(value = "user")
public class UserController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @GetMapping(value = "list")
    public String list(Page page, Model model) {
        model.addAttribute("pageInfo", userService.page(page));
        return "user/user-list";
    }

    @ResponseBody
    @PostMapping(value = "update")
    public JsonResult update(User user) {
        boolean b = userService.update(user);
        if (!b) {
            return JsonResultUtil.getErrorJson("修改失败");
        }
        return JsonResultUtil.getSuccessJson("修改成功");
    }

    @ResponseBody
    @DeleteMapping(value = "{id}/delete")
    public JsonResult delete(@PathVariable("id") Integer id) {
        return JsonResultUtil.getStatusJson(userService.removeById(id));
    }

    @GetMapping(value = "{id}/goto-update-user")
    public String gotoUpdateUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "user/update-user";
    }

    @GetMapping(value = "/goto-change-pwd")
    public String gotoChangePwd(Model model) {
        org.springframework.security.core.userdetails.User user = SpringSecurityUtil.getCurrentUser();
        model.addAttribute("user", user);
        return "user/change-pwd";
    }

    @ResponseBody
    @PostMapping(value = "/change-pwd")
    public JsonResult changePwd(String oldPwd, String newPwd, Model model) {
        String username = SpringSecurityUtil.getCurrentUsername();

        User user = userService.getOne(Wrappers.<User>lambdaQuery().eq(User::getNickname, username));

        //判断旧密码是否正确
        if (!passwordEncoder.matches(oldPwd, user.getPassword())) {
            return JsonResultUtil.getErrorJson("旧密码错误");
        }

        // 加密新密码
        final String encodePassword = passwordEncoder.encode(newPwd);
        user.setPassword(encodePassword);

        return JsonResultUtil.getStatusJson(userService.changePwd(user));
    }


}
