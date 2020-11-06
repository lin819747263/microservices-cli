package com.linmsen.demo.controller;

import com.linmsen.demo.Thing;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api("todo List")
@RestController
@RequestMapping("todo")
public class DemoController {

    private Map<String, Thing> map = new HashMap<>();


    @ApiOperation("add")
    @PostMapping("/add")
    public void add(Thing thing){
        map.put(thing.getId().toString(), thing);
    }

    @ApiOperation("delete")
    @PostMapping("/delete")
    public void delete(Thing thing){
        map.remove(thing.getId());
    }

    @ApiOperation("list")
    @GetMapping("/list")
    public List<Thing> list(Thing thing){
        return new ArrayList<>(map.values());
    }

    class Solution {
        public int[][] insert(int[][] intervals, int[] newInterval) {
            int left = newInterval[0];
            int right = newInterval[1];

            boolean isplaced = false;

            List<int[]> res = new ArrayList<>();

            for(int[] interval : intervals){
                if(interval[0] > right){
                    if(!isplaced){
                        res.add(new int[]{left, right});
                        isplaced = true;
                    }
                    res.add(interval);
                }else if(interval[1] < left){
                    res.add(interval);
                }else {
                    left = Math.min(interval[0],  left);
                    right = Math.max(interval[1],  right);
                }
            }
            if (!isplaced) {
                res.add(new int[]{left, right});
            }

            int[][] result = new int[res.size()][2];
            for(int i = 0; i < res.size(); ++i){
                result[i] = res.get(i);
            }
            return result;
        }
    }
}
