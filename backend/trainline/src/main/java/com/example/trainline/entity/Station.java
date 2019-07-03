package com.example.trainline.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
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
@ApiModel(value="车站对象", description="车站对象")
public class Station implements Serializable {

    private static final long serialVersionUID=1L;

    private String stationId;

    private String stationName;

    private Date createTime;

    private String createById;

    private String createByName;

    @TableField(exist = false)
    private BigDecimal xposition;

    @TableField(exist = false)
    private BigDecimal yposition;
}
