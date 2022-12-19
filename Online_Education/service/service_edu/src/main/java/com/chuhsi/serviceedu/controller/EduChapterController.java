package com.chuhsi.serviceedu.controller;

import com.chuhsi.commonUtils.R;
import com.chuhsi.serviceedu.entity.EduChapter;
import com.chuhsi.serviceedu.entity.chapter.ChapterVo;
import com.chuhsi.serviceedu.service.EduChapterService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
@Api(description = "章节小节管理")
@RestController
@RequestMapping("/eduservice/chapter")
@CrossOrigin()
public class EduChapterController {

    @Autowired
    private EduChapterService chapterService;

    //1 添加课程基本信息的方法
    @GetMapping("getChapterVideo/{courseId}")
    public R getChapterVo(@PathVariable String courseId) {
        List<ChapterVo> list = chapterService.getChapterVoByCourseId(courseId);
        return R.ok().data("allChapterVideo", list);
    }

    //2 添加课程章节
    @PostMapping("addChapter")
    public R addChapter(@RequestBody EduChapter eduChapter) {
        chapterService.save(eduChapter);
        return R.ok();
    }

    //3 根据课程id，查询课程章节
    @GetMapping("getChapterInfo/{chapterId}")
    public R getChapterInfo(@PathVariable String chapterId) {
        EduChapter chapterById = chapterService.getById(chapterId);
        return R.ok().data("chapter", chapterById);
    }

    //4 修改课程章节
    @PostMapping("updateChapter")
    public R updateChapter(@RequestBody EduChapter eduChapter) {
        chapterService.updateById(eduChapter);
        return R.ok();
    }

    //5 删除课程章节
    @DeleteMapping("{chapterId}")
    public R deleteChapter(@PathVariable String chapterId) {
        boolean result = chapterService.deleteChapterById(chapterId);
        if (result) {
            return R.ok();
        } else {
            return R.error();
        }
    }

}

