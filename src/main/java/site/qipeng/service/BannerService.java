package site.qipeng.service;


import site.qipeng.entity.Banner;
import site.qipeng.entity.BannerDTO;

import java.util.List;
import java.util.Map;

public interface BannerService {

    /**
     * 根据id获取Banner
     * @param id
     * @return
     */
    Banner getBannerById(Integer id);

    /**
     * 分页查询Banner列表
     * @param map
     * @param pageNum
     * @param pageSize
     * @return
     */
    List<Banner> getBannerList(Map<String,Object> map, Integer pageNum, Integer pageSize);

    /**
     * 插入Banner
     * @param banner
     * @return
     */
    int insertVideo(BannerDTO banner);

    /**
     * 修改Banner
     * @param banner
     * @return
     */
    int updateBanner(BannerDTO banner);

    /**
     * 删除Banner
     * @param id
     * @return
     */
    int deleteBanner(Integer id);
}
