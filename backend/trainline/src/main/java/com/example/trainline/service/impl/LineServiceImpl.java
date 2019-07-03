package com.example.trainline.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.trainline.entity.Line;
import com.example.trainline.entity.LineStationRelation;
import com.example.trainline.entity.Path;
import com.example.trainline.entity.Station;
import com.example.trainline.mapper.LineMapper;
import com.example.trainline.mapper.LineStationRelationMapper;
import com.example.trainline.mapper.PathMapper;
import com.example.trainline.model.ResultData;
import com.example.trainline.service.ILineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.trainline.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
public class LineServiceImpl extends ServiceImpl<LineMapper, Line> implements ILineService {
    @Autowired
    private LineStationRelationMapper relationMapper;

    @Autowired
    private PathMapper pathMapper;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData<Boolean> add(Line line) {
        ResultData<Boolean> result = new ResultData<>();
        if(line == null){
            result.set("参数异常", false, 0);
            return result;
        }
        if(StringUtil.isNull(line.getLineName())){
            result.set("线路名称不能为空", false, 0);
            return result;
        }
        if(line.getStations() == null || line.getStations().size() < 2){
            result.set("一条线路至少要有两个站", false, 0);
            return result;
        }
        if(line.getPaths() == null || line.getPaths().size() == 0){
            result.set("一条线路至少要有一条连线", false, 0);
            return result;
        }
        List<Station> stations = line.getStations();
        List<Path> paths = line.getPaths();
        //判断是否有车站没有连线
        for (int i = 0; i < stations.size(); i++){
            Station station = stations.get(i);
            if(station == null){
                result.set("提交的数据异常", false, 0);
                return result;
            }
            boolean contains = false;
            for (int j = 0; j < paths.size(); j ++){
                Path path = paths.get(j);
                if(path == null){
                    result.set("提交的数据异常", false, 0);
                    return result;
                }
                retry :
                if(path.getStation1Id().equals(station.getStationId()) || path.getStation2Id().equals(station.getStationId())){
                    contains = true;
                    break retry;
                }
                if(!contains){
                    result.set("线路中存在单独的没有连线的车站", false, 0);
                    return result;
                }
            }
        }
        //判断是否连线中的车站是否都在车站集合中，以及是否存在连接自己本身的连线
        for (int i = 0; i < paths.size(); i++){
            Path path = paths.get(i);
            if(StringUtil.isNull(path.getStation1Id()) || StringUtil.isNull(path.getStation2Id()) || path.getStation1Id().equals(path.getStation2Id())){
                result.set("提交的数据异常", false, 0);
                return result;
            }
            boolean contains1 = false;
            boolean contains2 = false;
            for (int j = 0; j < stations.size(); j++){
                Station station = stations.get(j);
                if(station.getStationId().equals(path.getStation1Id())){
                    contains1 = true;
                    if(contains1 && contains2){
                        break;
                    }
                }
                if(station.getStationId().equals(path.getStation2Id())){
                    contains2 = true;
                    if(contains1 && contains2){
                        break;
                    }
                }
            }
            if(!(contains1 && contains2)){
                result.set("提交的数据异常", false, 0);
                return result;
            }
        }
        QueryWrapper<Line> wrapper = new QueryWrapper<>();
        wrapper.eq("line_name", line.getLineName());
        List<Line> lines = baseMapper.selectList(wrapper);
        if(lines.size() > 0){
            result.set("线路名称已存在", false, 0);
            return result;
        }
        line.setLineId(UUID.randomUUID().toString());
        line.setCreateTime(new Date());
        baseMapper.insert(line);
        for (int i = 0; i < paths.size(); i++){
            Path path = paths.get(i);
            path.setCreateTime(new Date());
            path.setLineId(line.getLineId());
            path.setPathId(UUID.randomUUID().toString());
        }
        for (Path path : paths) {
            pathMapper.insert(path);
        }
        List<LineStationRelation> relations = new ArrayList<>();
        for (Station station :
             stations) {
            LineStationRelation relation = new LineStationRelation();
            relation.setLineId(line.getLineId());
            relation.setRelationId(UUID.randomUUID().toString());
            relation.setStationId(station.getStationId());
            relation.setXposition(station.getXposition());
            relation.setYposition(station.getYposition());
            relations.add(relation);
        }
        for (LineStationRelation relation: relations) {
            relationMapper.insert(relation);
        }
        result.set("添加成功", true, 1);
        return result;
    }

    @Override
    public ResultData<List<Line>> queryAll() {
        ResultData<List<Line>> result = new ResultData<>();
        List<Line> lines = baseMapper.selectList(null);
        result.set("查询成功", lines, 1);
        return result;
    }

    @Override
    public ResultData<Boolean> delete(String lineId) {
        ResultData<Boolean> result = new ResultData<>();
        int res = baseMapper.deleteById(lineId);
        if(res >= 1){
            result.set("删除成功", true, 1);
        }
        else {
            result.set("该站不存在", false, 0);
        }
        return result;
    }

    @Override
    public ResultData<Boolean> modify(Line line) {
        ResultData<Boolean> result = new ResultData<>();
        if(line == null){
            result.set("参数异常", false, 0);
            return result;
        }
        if(StringUtil.isNull(line.getLineName())){
            result.set("线路名称不能为空", false, 0);
            return result;
        }
        QueryWrapper<Line> wrapper = new QueryWrapper<>();
        wrapper.eq("lineName", line.getLineName());
        List<Line> lines = baseMapper.selectList(wrapper);
        boolean exist = (lines.size() == 1 && (!lines.get(0).getLineName().equals(line.getLineName()))) || lines.size() > 1;
        if(exist){
            result.set("线路名称已存在", false, 0);
            return result;
        }
        int res = baseMapper.updateById(line);
        if(res == 0){
            result.set("该线路不存在", false, 0);
            return result;
        }
        result.set("修改成功", true, 1);
        return result;
    }

    @Override
    public ResultData<Line> queryOne(String lineId) {
        ResultData<Line> result = new ResultData<>();
        Line line = baseMapper.selectById(lineId);
        if(line == null){
            result.set("该线路不存在", null, 0);
        }
        QueryWrapper<LineStationRelation> relationWrapper = new QueryWrapper<>();
        relationWrapper.eq("lineId", lineId);
        List<Station> stations = relationMapper.selectStationsByLineId(lineId);
        line.setStations(stations);
        QueryWrapper<Path> pathWrapper = new QueryWrapper<>();
        pathWrapper.eq("lineId", lineId);
        List<Path> paths = pathMapper.selectList(pathWrapper);
        line.setPaths(paths);
        result.set("查询成功", line, 1);
        return result;
    }
}
