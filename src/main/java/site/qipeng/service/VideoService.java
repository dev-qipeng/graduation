package site.qipeng.service;


import site.qipeng.entity.Video;
import site.qipeng.entity.VideoDTO;

import java.util.List;
import java.util.Map;

public interface VideoService {
    Video getVideoById(Integer id);
    List<Video> getVideoList(Map<String, Object> map, Integer pageNum, Integer pageSize);
    int insertVideo(VideoDTO video);
    int updateVideo(VideoDTO video);
    int deleteVideo(Integer id);
}
