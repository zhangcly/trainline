package com.example.trainline.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.trainline.entity.LineStationRelation;
import com.example.trainline.entity.Station;
import com.example.trainline.mapper.LineStationRelationMapper;
import com.example.trainline.mapper.StationMapper;
import com.example.trainline.model.ResultData;
import com.example.trainline.service.IStationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trainline.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IStationService {

    @Autowired
    private LineStationRelationMapper lineStationRelationMapper;

    @Override
    public ResultData<Boolean> add(Station station) {
        ResultData<Boolean> result = new ResultData<>();
        if(station == null){
            result.set("参数异常", false, 0);
            return result;
        }
        if(StringUtil.isNull(station.getStationName())){
            result.set("车站名称不能为空", false, 0);
            return result;
        }
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("station_name", station.getStationName());
        List<Station> stations = baseMapper.selectList(wrapper);
        if(stations.size() > 0){
            result.set("车站名称已存在", false, 0);
            return result;
        }
        station.setStationId(UUID.randomUUID().toString());
        station.setCreateTime(new Date());
        baseMapper.insert(station);
        result.set("添加成功", true, 1);
        return result;
    }

    @Override
    public ResultData<List<Station>> queryAll() {
        ResultData<List<Station>> result = new ResultData<>();
        List<Station> stations = baseMapper.selectList(null);
        result.set("查询成功", stations, 1);
        return result;
    }

    @Override
    public ResultData<Boolean> delete(String stationId) {
        ResultData<Boolean> result = new ResultData<>();
        int res = baseMapper.deleteById(stationId);
        if(res >= 1){
            result.set("删除成功", true, 1);
        }
        else {
            result.set("该站不存在", false, 0);
        }
        return result;
    }

    @Override
    public ResultData<Boolean> modify(Station station) {
        ResultData<Boolean> result = new ResultData<>();
        if(station == null){
            result.set("参数异常", false, 0);
            return result;
        }
        if(StringUtil.isNull(station.getStationName())){
            result.set("车站名称不能为空", false, 0);
            return result;
        }
        QueryWrapper<Station> wrapper = new QueryWrapper<>();
        wrapper.eq("stationName", station.getStationName());
        List<Station> stations = baseMapper.selectList(wrapper);
        if((stations.size() == 1 && (!stations.get(0).getStationName().equals(station.getStationName()))) ||
                stations.size() > 1){
            result.set("车站名称已存在", false, 0);
            return result;
        }
        int res = baseMapper.updateById(station);
        if(res == 0){
            result.set("该站不存在", false, 0);
            return result;
        }
        result.set("修改成功", true, 1);
        return result;
    }
}
