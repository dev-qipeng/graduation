package site.qipeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.qipeng.entity.Video;
import site.qipeng.mapper.VideoMapper;
import site.qipeng.service.VideoService;

@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {


}
