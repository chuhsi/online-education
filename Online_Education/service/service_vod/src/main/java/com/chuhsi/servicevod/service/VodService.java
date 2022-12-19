package com.chuhsi.servicevod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface VodService {

    String uploadVIdeo(MultipartFile file);

    void removeVideo(String videoId);

    void removeAllVideo(List videoIdList);
}
