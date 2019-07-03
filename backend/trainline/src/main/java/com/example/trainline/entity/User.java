package com.example.trainline.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
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
@ApiModel(value="用户对象", description="用户对象")
public class User implements Serializable {

    private static final long serialVersionUID=1L;

    private String userId;

    private String username;

    private String password;

    private LocalDateTime createTime;


}
