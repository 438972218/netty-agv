package com.xdcplus.xdcweb.basics.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class StationTaskInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String actionIndex;

    /**
     * 指定轴
     */
    private Integer actionAxis;

    /**
     * 作业类型
     */
    private Integer taskType;

    private Integer ifType;


}
