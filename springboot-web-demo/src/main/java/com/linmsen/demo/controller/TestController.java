package com.linmsen.demo.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("测试swagger")
@RequestMapping("test")
public class TestController {

//    @Autowired
//    private RedisRepository redisRepository;
//
//    @Autowired
//    private VideoMapperPlus videoMapperPlus;
//
//    @GetMapping("/hello")
//    @ApiOperation("hello")
//    public CommonResult hello(){
//        redisRepository.set("s", "hello");
//        return CommonResult.success("Hello");
//    }
//
//    @GetMapping("/sb")
//    @ApiOperation("测试异常")
//    public CommonResult sb(){
//        throw new ServiceException(0,"asda");
//    }
//
////    @GetMapping("findById")
////    CommonResult findById(Long id){
////        Video video =  videoMapper.findById(id);
////        return CommonResult.success(video);
////    }
//
//    @GetMapping("findById")
//    CommonResult findById(){
//        IPage<Video> page = new Page<Video>(1,2);
//        page = videoMapperPlus.selectPage(page,null);
////        redisRepository.set("s2", page.getRecords());
//        return CommonResult.success(page.getRecords());
//    }
}
