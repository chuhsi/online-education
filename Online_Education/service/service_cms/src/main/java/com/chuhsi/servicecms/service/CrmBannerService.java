package com.chuhsi.servicecms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.servicecms.entity.CrmBanner;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-10
 */

public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectAllBanner();
}
