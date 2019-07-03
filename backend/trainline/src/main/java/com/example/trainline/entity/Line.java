package com.example.trainline.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
@ApiModel(value="线路对象", description="线路对象")
public class Line implements Serializable {

    private static final long serialVersionUID=1L;

    private String lineId;

    private String lineName;

    private Date createTime;

    private String createById;

    private String createByName;

    @TableField(exist = false)
    private List<Station> stations;

    @TableField(exist = false)
    private List<Path> paths;
}
