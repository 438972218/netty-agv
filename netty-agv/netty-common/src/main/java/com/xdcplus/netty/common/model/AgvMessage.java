package com.xdcplus.netty.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author fish
 * @since 2021-04-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AgvMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    private String packHead;

    private Integer packNr;

    private Integer packLength;

    private Integer packAckNr;

    private Integer packAckSt;

    private String senderCode;

    private String receiverCode;

    private Integer heart;

    private Integer packType;

    private Integer spare1;

    private Integer spare2;

    private Integer spare3;

    private Integer spare4;

    private Integer dataSize;

    private String data;

    private Integer packCrc;

    private String packEnd;

    private byte[] bytes;

    private String type;

}
