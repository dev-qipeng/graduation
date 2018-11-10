package site.qipeng.service.impl;

import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qipeng.dao.BannerMapper;
import site.qipeng.entity.Banner;
import site.qipeng.entity.BannerDTO;
import site.qipeng.entity.BannerExample;
import site.qipeng.service.BannerService;

import java.util.List;
import java.util.Map;

@Service
public class BannerServiceImpl implements BannerService {

    private static Logger logger = LoggerFactory.getLogger(BannerServiceImpl.class);

    @Autowired
    private BannerMapper bannerMapper;


    @Override
    public Banner getBannerById(Integer id) {
        if(id == null){
            return null;
        }
        return bannerMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Banner> getBannerList(Map<String, Object> map, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        BannerExample example = new BannerExample();
        example.setOrderByClause("id asc");
        List<Banner> banners = bannerMapper.selectByExample(example);
        return banners;
    }

    @Override
    public int insertVideo(BannerDTO banner) {
        Banner record = new Banner();
        record.setImgUrl(banner.getImgUrl());
        record.setVideoId(banner.getVideoId());
        logger.debug("insert one banner...");
        return bannerMapper.insert(record);
    }

    @Override
    public int updateBanner(BannerDTO banner) {
        Banner record = getBannerById(banner.getId());
        if(record == null){
            return 0;
        }
        record.setImgUrl(banner.getImgUrl());
        record.setVideoId(banner.getVideoId());
        logger.debug("update one banner...");
        return bannerMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int deleteBanner(Integer id) {
        if(id == null){
           return 0;
        }
        logger.debug("delete one banner...");
        return bannerMapper.deleteByPrimaryKey(id);
    }


}
