package com.xdcplus.xdcweb.basics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xdcplus.netty.common.model.AgvState;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fish
 * @since 2021-05-06
 */
public interface AgvStateMapper extends BaseMapper<AgvState> {

    Integer insertReturnId(AgvState agvState);

}
