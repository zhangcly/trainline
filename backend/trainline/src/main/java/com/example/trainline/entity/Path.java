package com.example.trainline.entity;

import java.io.Serializable;
import java.util.Date;

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
@ApiModel(value="Path对象", description="")
public class Path implements Serializable {

    private static final long serialVersionUID=1L;

    private String pathId;

    private String station1Id;

    private String station2Id;

    private Integer distance;

    private String lineId;

    private Date createTime;

    private String createById;

    private String createByName;


}
