package com.linmsen.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.linmsen.demo.dao.Video;
import com.linmsen.demo.dao.VideoMapperPlus;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapperPlus, Video> implements VideoService{

}
