package com.example.trainline.controller;


import com.example.trainline.entity.Station;
import com.example.trainline.model.ResultData;
import com.example.trainline.service.IStationService;
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
@Api(value = "车站接口")
@RestController
@RequestMapping("/station")
@CrossOrigin(origins = "*")
public class StationController {

    @Autowired
    private IStationService stationService;

    @ApiOperation(value="新增车站",notes="新增车站")
    @ResponseBody
    @PostMapping("/add")
    public ResultData<Boolean> add(@RequestBody @ApiParam(required = true,name="station", value = "车站实体") Station station){
        ResultData<Boolean> result = stationService.add(station);
        return result;
    }


    @ApiOperation(value="修改车站",notes="修改车站")
    @ResponseBody
    @PutMapping("/modify")
    public ResultData<Boolean> modify(@RequestBody @ApiParam(required = true,name="station", value = "车站实体") Station station){
        ResultData<Boolean> result = stationService.modify(station);
        return result;
    }

    @ApiOperation(value="删除车站",notes="删除车站")
    @ResponseBody
    @DeleteMapping("/delete")
    public ResultData<Boolean> delete(@RequestParam("stationId") @ApiParam(required = true,name="station", value = "车站id") String stationId){
        ResultData<Boolean> result = stationService.delete(stationId);
        return result;
    }
    @ApiOperation(value="查询所有车站",notes="查询所有车站")
    @ResponseBody
    @GetMapping("/queryAll")
    public ResultData<List<Station>> queryAll(){
        ResultData<List<Station>> result = stationService.queryAll();
        return result;
    }
}

