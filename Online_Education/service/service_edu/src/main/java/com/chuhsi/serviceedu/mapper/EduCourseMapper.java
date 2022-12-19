package com.chuhsi.serviceedu.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.chuhsi.serviceedu.entity.EduCourse;
import com.chuhsi.serviceedu.entity.frontVo.CourseWebVo;
import com.chuhsi.serviceedu.entity.vo.CoursePublishVo;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CoursePublishVo getPublishCourseInfo(String id);

    CourseWebVo getBaseCourseInfo(String courseId);
}
