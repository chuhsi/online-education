package com.chuhsi.serviceedu.controller.front;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.chuhsi.commonUtils.JwtUtils;
import com.chuhsi.commonUtils.R;
import com.chuhsi.commonUtils.user.CourseWebVoOrder;
import com.chuhsi.serviceedu.client.OrderClient;
import com.chuhsi.serviceedu.entity.EduCourse;
import com.chuhsi.serviceedu.entity.chapter.ChapterVo;
import com.chuhsi.serviceedu.entity.frontVo.CourseQueryVo;
import com.chuhsi.serviceedu.entity.frontVo.CourseWebVo;
import com.chuhsi.serviceedu.service.EduChapterService;
import com.chuhsi.serviceedu.service.EduCourseService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("eduservice/coursefront")
@CrossOrigin
public class CourseFrontController {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private OrderClient orderClient;

    @PostMapping("getFrontInfo/{page}/{limit}")//@RequestBody(required = false)传入值可以为空
    public R getFrontInfo(@PathVariable long page, @PathVariable long limit,
                          @RequestBody(required = false) CourseQueryVo courseQueryVo){

        Page<EduCourse> queryVoPage = new Page<>(page,limit);

        Map<String,Object> map = eduCourseService.getTeacherInfo(queryVoPage,courseQueryVo);
        return R.ok().data(map);
    }

    @GetMapping("getFrontCourseInfo/{courseId}")
    public R getFrontCourseInfo(@PathVariable String courseId,HttpServletRequest request){
        //根据课程id，编写sql语句查询课程信息
        CourseWebVo courseWebVo = eduCourseService.getBaseCourseInfo(courseId);

        //根据课程id，查询章节和小节
        List<ChapterVo> chapterVoList = eduChapterService.getChapterVoByCourseId(courseId);

        //根据课程Id和用户ID查询订单表中是否支付
        String memberId = JwtUtils.getMemberIdByJwtToken(request);//取出用户id
        boolean buyCourse = orderClient.isBuyCourse(courseId, memberId);
        return R.ok().data("courseWebVo",courseWebVo).data("chapterVideoList",chapterVoList).data("isBuy",buyCourse);
    }

    //远程调用根据课程id获得课程信息
    @PostMapping("getCourseInfoOrder/{id}")
    public CourseWebVoOrder getCourseInfoOrder(@PathVariable String id){
        CourseWebVo courseInfo = eduCourseService.getBaseCourseInfo(id);
        CourseWebVoOrder courseWebVoOrder = new CourseWebVoOrder();
        BeanUtils.copyProperties(courseInfo,courseWebVoOrder);
        return courseWebVoOrder;
    }

}
