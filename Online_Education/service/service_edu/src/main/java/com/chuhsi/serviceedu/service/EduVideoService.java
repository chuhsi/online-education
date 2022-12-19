package com.chuhsi.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceedu.entity.EduVideo;

import java.util.List;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
public interface EduVideoService extends IService<EduVideo> {

    void removeByCourseId(String courseId);

}
