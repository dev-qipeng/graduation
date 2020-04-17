package site.qipeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import site.qipeng.entity.Comment;
import site.qipeng.mapper.CommentMapper;
import site.qipeng.service.CommentService;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
}
