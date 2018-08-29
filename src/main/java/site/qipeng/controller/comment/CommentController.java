package site.qipeng.controller.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.qipeng.service.CommentService;

@Controller
@RequestMapping(value = "comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
}
