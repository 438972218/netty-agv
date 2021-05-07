package com.xdcplus.xdcweb.basics.entity;

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
public class AlarmState implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 来源
     */
    private Integer alarmSource;

    /**
     * 等级
     */
    private Integer alarmLevel;

    /**
     * 异常码
     */
    private Integer alarmCode;

    /**
     * 详情
     */
    private String alarmDetail;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 持续时间
     */
    private Integer duration;


}
