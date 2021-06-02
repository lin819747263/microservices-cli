package com.linmsen.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.linmsen.cache.template.RedisRepository;
import com.linmsen.core.exception.ServiceException;
import com.linmsen.core.vo.CommonResult;
import com.linmsen.demo.dao.Video;
import com.linmsen.demo.dao.VideoMapperPlus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@Api("测试swagger")
@RequestMapping("test")
public class TestController {

    @Autowired
    private VideoMapperPlus videoMapperPlus;

    @GetMapping("findById")
    CommonResult findById(MultipartFile file){
        QueryWrapper<Video> queryWrapper = new QueryWrapper<Video>().eq("id", 40);
        IPage<Video> page = new Page<>(1,2);
        page = videoMapperPlus.selectPage(page,queryWrapper);

        return CommonResult.success(page.getRecords());
    }
}
