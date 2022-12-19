package com.chuhsi.serviceedu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chuhsi.commonUtils.exceptionhandler.GuliException;
import com.chuhsi.serviceedu.entity.EduChapter;
import com.chuhsi.serviceedu.entity.EduVideo;
import com.chuhsi.serviceedu.entity.chapter.ChapterVo;
import com.chuhsi.serviceedu.entity.chapter.VideoVo;
import com.chuhsi.serviceedu.mapper.EduChapterMapper;
import com.chuhsi.serviceedu.service.EduChapterService;
import com.chuhsi.serviceedu.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2020-08-06
 */
@Transactional
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public List<ChapterVo> getChapterVoByCourseId(String courseId) {
        //1，根据课程id，查询课程里面所有的章节
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", courseId);
        List<EduChapter> eduChapterList = baseMapper.selectList(chapterQueryWrapper);

        //2，根据课程id，查询课程里面所有的小节
        QueryWrapper<EduVideo> videoQueryWrapper = new QueryWrapper<>();
        videoQueryWrapper.eq("course_id", courseId);
        List<EduVideo> eduVideoList = eduVideoService.list(videoQueryWrapper);

        //创建list集合进行最终封装
        ArrayList<ChapterVo> finalList = new ArrayList<>();
        //3，遍历查询章节list集合进行封装
        for (int i = 0; i < eduChapterList.size(); i++) {
            EduChapter eduChapter = eduChapterList.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter, chapterVo);
            finalList.add(chapterVo);

            //4，遍历查询小节list集合进行封装
            ArrayList<VideoVo> finalVideoVoList = new ArrayList<>();
            for (int j = 0; j < eduVideoList.size(); j++) {
                EduVideo eduVideo = eduVideoList.get(j);
                if (eduVideo.getChapterId().equals(chapterVo.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo, videoVo);
                    finalVideoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(finalVideoVoList);
        }


        return finalList;
    }

    //删除章节的方法
    @Override
    public boolean deleteChapterById(String chapterId) {
        //根据章节id，如果有小节数据则不能删除
        QueryWrapper<EduVideo> eduVideoWrapper = new QueryWrapper<>();
        eduVideoWrapper.eq("chapter_id", chapterId);
        int count = eduVideoService.count(eduVideoWrapper);
        if (count > 0) {
            //有小节
            throw new GuliException(20001, "有小节，无法删除");
        }
        int i = baseMapper.deleteById(chapterId);
        return i > 0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("course_id", courseId);
        baseMapper.delete(queryWrapper);
    }

}
