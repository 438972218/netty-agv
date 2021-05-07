package com.xdcplus.xdcweb.basics.service.impl;

import com.xdcplus.xdcweb.basics.entity.Task;
import com.xdcplus.xdcweb.basics.mapper.TaskMapper;
import com.xdcplus.xdcweb.basics.service.ITaskService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fish
 * @since 2021-05-06
 */
@Service
public class TaskServiceImpl extends ServiceImpl<TaskMapper, Task> implements ITaskService {

}
