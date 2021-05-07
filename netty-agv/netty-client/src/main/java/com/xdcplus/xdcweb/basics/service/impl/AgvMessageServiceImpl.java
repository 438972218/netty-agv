package com.xdcplus.xdcweb.basics.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xdcplus.netty.common.model.AgvMessage;
import com.xdcplus.xdcweb.basics.mapper.AgvMessageMapper;
import com.xdcplus.xdcweb.basics.service.IAgvMessageService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2021-04-30
 */
@Service
public class AgvMessageServiceImpl extends ServiceImpl<AgvMessageMapper, AgvMessage> implements IAgvMessageService {

}
