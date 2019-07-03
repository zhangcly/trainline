package com.example.trainline.service;

import com.example.trainline.entity.Station;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trainline.model.ResultData;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
public interface IStationService extends IService<Station> {

    /**
     * fetch data by rule id
     *
     * @param station 车站实体
     * @return Result<Boolean>
     */
    ResultData<Boolean> add(Station station);

    ResultData<List<Station>> queryAll();

    ResultData<Boolean> delete(String stationId);

    ResultData<Boolean> modify(Station station);
}
