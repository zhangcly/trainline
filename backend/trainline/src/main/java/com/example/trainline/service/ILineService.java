package com.example.trainline.service;

import com.example.trainline.entity.Line;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.trainline.entity.Station;
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
public interface ILineService extends IService<Line> {

    ResultData<Boolean> add(Line line);

    ResultData<List<Line>> queryAll();

    ResultData<Boolean> delete(String lineId);

    ResultData<Boolean> modify(Line line);

    ResultData<Line> queryOne(String lineId);
}
