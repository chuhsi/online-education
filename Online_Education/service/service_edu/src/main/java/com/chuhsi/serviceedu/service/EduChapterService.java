package com.chuhsi.serviceedu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chuhsi.serviceedu.entity.EduChapter;
import com.chuhsi.serviceedu.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
public interface EduChapterService extends IService<EduChapter> {

    List<ChapterVo> getChapterVoByCourseId(String courseId);

    //5 删除课程章节
    boolean deleteChapterById(String chapterId);

    void removeChapterByCourseId(String courseId);

}
