package com.example.trainline.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="LineStationRelation对象", description="")
public class LineStationRelation implements Serializable {

    private static final long serialVersionUID=1L;

    private String relationId;

    private String lineId;

    private String stationId;

    private BigDecimal xposition;

    private BigDecimal yposition;

}
