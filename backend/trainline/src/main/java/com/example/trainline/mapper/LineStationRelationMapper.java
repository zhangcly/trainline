package com.example.trainline.mapper;

import com.example.trainline.entity.LineStationRelation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.trainline.entity.Station;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
public interface LineStationRelationMapper extends BaseMapper<LineStationRelation> {

    @Select("select r.station_id as stationId, s.station_name as stationName, r.xposition, r.yposition, " +
            "from line_station_relation r left join station s on r.stationId = s.stationId where r.lineId = #{lineId}")
    @ResultType(Station.class)
    List<Station> selectStationsByLineId(@Param("lineId") String lineId);
}
