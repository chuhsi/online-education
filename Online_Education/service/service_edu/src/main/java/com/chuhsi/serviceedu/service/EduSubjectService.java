package com.chuhsi.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceedu.entity.EduSubject;
import com.chuhsi.serviceedu.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-05
 */
public interface EduSubjectService extends IService<EduSubject> {

    //1 添加课程（使用了EasyExcel）
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);

    //2 课程分类返回"树形"结构
    List<OneSubject> getAllOneTwoSubject();
}
