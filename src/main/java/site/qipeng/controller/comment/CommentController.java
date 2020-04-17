package site.qipeng.controller.comment;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import site.qipeng.service.CommentService;

@AllArgsConstructor
@Controller
@RequestMapping(value = "comment")
public class CommentController {

    private CommentService commentService;
}
