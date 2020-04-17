package site.qipeng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.qipeng.entity.Banner;
import site.qipeng.mapper.BannerMapper;
import site.qipeng.service.BannerService;

@Slf4j
@AllArgsConstructor
@Service
public class BannerServiceImpl extends ServiceImpl<BannerMapper, Banner> implements BannerService {

}
