package site.qipeng.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.qipeng.mapper.BannerMapper;
import site.qipeng.service.UISettingService;

@Service
public class UISettingServiceImpl implements UISettingService {

    @Autowired
    private BannerMapper bannerMapper;
}
