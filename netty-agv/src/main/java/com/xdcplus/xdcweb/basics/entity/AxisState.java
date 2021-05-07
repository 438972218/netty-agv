package com.xdcplus.xdcweb.basics.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fish
 * @since 2021-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AxisState implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer actionAxis;

    private Integer materialCount;

    @TableField("materialType")
    private Integer materialtype;


}
