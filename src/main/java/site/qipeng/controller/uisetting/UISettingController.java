package site.qipeng.controller.uisetting;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import site.qipeng.service.UISettingService;

@Controller
@RequestMapping(value = "uisetting")
public class UISettingController {

    @Autowired
    private UISettingService uiSettingService;


}
