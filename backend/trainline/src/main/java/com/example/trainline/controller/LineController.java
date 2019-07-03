package com.example.trainline.controller;


import com.example.trainline.entity.Line;
import com.example.trainline.entity.Station;
import com.example.trainline.model.ResultData;
import com.example.trainline.service.ILineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
@Api(value = "线路接口")
@RestController
@RequestMapping("/line")
@CrossOrigin(origins = "*")
public class LineController {

    @Autowired
    private ILineService lineService;

    @ApiOperation(value="添加线路",notes="添加线路")
    @ResponseBody
    @PostMapping("/add")
    public ResultData<Boolean> add(@RequestBody @ApiParam(required = true,name="line", value = "线路实体")Line line){
        ResultData<Boolean> result = lineService.add(line);
        return result;
    }

    @ApiOperation(value="修改线路",notes="修改线路")
    @ResponseBody
    @PutMapping("/modify")
    public ResultData<Boolean> modify(@RequestBody @ApiParam(required = true,name="line", value = "线路实体") Line line){
        ResultData<Boolean> result = lineService.modify(line);
        return result;
    }

    @ApiOperation(value="删除线路",notes="删除线路")
    @ResponseBody
    @DeleteMapping("/delete")
    public ResultData<Boolean> delete(@RequestParam("lineId") @ApiParam(required = true,name="lineId", value = "线路id") String lineId){
        ResultData<Boolean> result = lineService.delete(lineId);
        return result;
    }
    @ApiOperation(value="查询所有线路",notes="查询所有线路")
    @ResponseBody
    @GetMapping("/queryAll")
    public ResultData<List<Line>> queryAll(){
        ResultData<List<Line>> result = lineService.queryAll();
        return result;
    }

    @ApiOperation(value="查询单个线路详情",notes="查询单个线路详情")
    @ResponseBody
    @GetMapping("/queryOne")
    public ResultData<Line> queryOne(@RequestParam("lineId") @ApiParam(required = true,name="lineId", value = "线路id")String lineId){
        ResultData<Line> result = lineService.queryOne(lineId);
        return result;
    }
}

