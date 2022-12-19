package com.chuhsi.serviceedu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceedu.entity.EduCourse;
import com.chuhsi.serviceedu.entity.frontVo.CourseQueryVo;
import com.chuhsi.serviceedu.entity.frontVo.CourseWebVo;
import com.chuhsi.serviceedu.entity.vo.CourseInfoVo;
import com.chuhsi.serviceedu.entity.vo.CoursePublishVo;

import java.util.Map;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
public interface EduCourseService extends IService<EduCourse> {

    //1 添加课程信息
    String saveCourseInfo(CourseInfoVo courseInfoVo);

    //2 根据课程id查询课程基本信息
    CourseInfoVo getCourseInfo(String courseId);

    //3 修改课程信息
    void updateCourseInfo(CourseInfoVo courseInfoVo);

    //4 根据课程id，查询课程发布确认信息
    CoursePublishVo getPublishCourseInfo(String id);

    //5 根据课程id删除课程
    void removeCourse(String courseId);

    Map<String, Object> getTeacherInfo(Page<EduCourse> queryVoPage, CourseQueryVo courseQueryVo);

    CourseWebVo getBaseCourseInfo(String courseId);
}
