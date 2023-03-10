package com.chuhsi.servicevod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.chuhsi.commonUtils.R;
import com.chuhsi.commonUtils.exceptionhandler.GuliException;
import com.chuhsi.servicevod.service.VodService;
import com.chuhsi.servicevod.util.AliyunVodSDKUtils;
import com.chuhsi.servicevod.util.ConstantVodUtils;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("uploadAlyiVideo")
    public R uploadAlyiVideo(MultipartFile file) {
        String VideoId = vodService.uploadVIdeo(file);
        return R.ok().data("VideoId", VideoId);
    }

    @DeleteMapping("{videoId}")
    public R removeVideo(@ApiParam(name = "videoId", value = "云端视频id", required = true)
                         @PathVariable String videoId) {
        vodService.removeVideo(videoId);
        return R.ok().message("视频删除成功");
    }

    //删除多个阿里云视频
    @DeleteMapping("deleteBatch")
    public R deleteBatch(@RequestParam List videoIdList) {
        vodService.removeAllVideo(videoIdList);
        return R.ok();
    }

    //根据视频id获得视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id) {
        try {
            //创建初始化对象
            DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证的request和response对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request对象中设置视频id
            request.setVideoId(id);
            //调用方法获得凭证
            GetVideoPlayAuthResponse response = client.getAcsResponse(request);
            String playAuth = response.getPlayAuth();

            return R.ok().data("playAuth", playAuth);
        } catch (ClientException e) {
            e.printStackTrace();
            throw new GuliException(20001, "视频playAuth获取失败");
        }

    }
}
