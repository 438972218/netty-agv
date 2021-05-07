package com.xdcplus.netty.common.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

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
public class AgvState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 小车号
     */
    private Integer agvId;

    /**
     * 任务号
     */
    private Integer taskNum;

    /**
     * 电量
     */
    private Integer agvEnergy;

    /**
     * AGV状态
     */
    private Integer state;

    /**
     * 当前站台
     */
    private Integer curStationNum;

    /**
     * 当前地图id
     */
    private Integer mapId;

    /**
     * 坐标X
     */
    private Integer x;

    /**
     * 坐标Y
     */
    private Integer y;

    private List<AxisState> axisStates;

}
