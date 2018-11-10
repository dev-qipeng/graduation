package site.qipeng.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.qipeng.dao.VideoMapper;
import site.qipeng.entity.Video;
import site.qipeng.entity.VideoDTO;
import site.qipeng.entity.VideoExample;
import site.qipeng.service.VideoService;
import site.qipeng.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class VideoServiceImpl extends Video implements VideoService {

    private static Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private VideoMapper videoMapper;

    public Video getVideoById(Integer id) {
        if (id == null){
            return null;
        }
        return videoMapper.selectByPrimaryKey(id);
    }

    public List<Video> getVideoList(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        VideoExample example = new VideoExample();
        return videoMapper.selectByExample(example);
    }

    @Transactional
    public int insertVideo(VideoDTO video) {
        Video record = new Video();
        record.setName(video.getName());
        record.setPoster(video.getPoster());
        record.setCategoryId(video.getCategoryId());
        record.setUrl(video.getUrl());
        record.setLikeNum(0);
        record.setPlayNum(0);
        record.setScore(9.0);
        record.setCreateTime(new Date());
        record.setDescription(video.getDescription());
        logger.info("insert one video...");
        return videoMapper.insert(record);
    }

    @Transactional
    public int updateVideo(VideoDTO video) {
        Video record = getVideoById(video.getId());
        record.setName(video.getName());
        record.setPoster(video.getPoster());
        record.setCategoryId(video.getCategoryId());
        record.setUrl(video.getUrl());
        record.setUpdateTime(new Date());
        record.setDescription(video.getDescription());
        logger.info("update one video...");
        return videoMapper.updateByPrimaryKeySelective(record);
    }

    @Transactional
    public int deleteVideo(Integer id) {
        if (!StringUtils.isEmpty(id.toString())){
            logger.info("delete one video...");
            return videoMapper.deleteByPrimaryKey(id);
        }
        return 0;
    }
}