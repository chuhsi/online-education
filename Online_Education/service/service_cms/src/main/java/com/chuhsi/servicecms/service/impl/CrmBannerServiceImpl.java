package com.chuhsi.servicecms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuhsi.servicecms.entity.CrmBanner;
import com.chuhsi.servicecms.mapper.CrmBannerMapper;
import com.chuhsi.servicecms.service.CrmBannerService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-10
 */
@Transactional
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Cacheable(value = "banner", key = "'selectIndexList'")

    //查询所有banner
    @Override
    public List<CrmBanner> selectAllBanner() {
        //根据id降序排列，显示排列之后的前两条记录
        QueryWrapper<CrmBanner> bannerQueryWrapper = new QueryWrapper<>();
        bannerQueryWrapper.orderByDesc("id");
        //list拼接sql语句
        bannerQueryWrapper.last("limit 2");
        List<CrmBanner> list = baseMapper.selectList(null);
        return list;
    }
}
