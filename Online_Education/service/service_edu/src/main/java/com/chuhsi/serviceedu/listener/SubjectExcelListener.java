package com.chuhsi.serviceedu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.chuhsi.commonUtils.exceptionhandler.GuliException;
import com.chuhsi.serviceedu.entity.EduSubject;
import com.chuhsi.serviceedu.entity.excel.SubjectData;
import com.chuhsi.serviceedu.service.EduSubjectService;

import java.util.Map;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

    public EduSubjectService eduSubjectService;

    public SubjectExcelListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExcelListener() {
    }

    //判断一级分类，不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    //判断二级分类，不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new GuliException(20001, "文件数据为空");
        }
        //判断一级分类
        EduSubject oneSubject = this.existOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        //没有相同的一级分类，进行添加
        if (oneSubject == null) {
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }

        //判断二级分类
        String pid = oneSubject.getId();
        EduSubject twoSubject = this.existTwoSubject(eduSubjectService, subjectData.getTwoSubjectName(), pid);
        //没有相同的二级分类，进行添加
        if (twoSubject == null) {
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(twoSubject);
        }
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
