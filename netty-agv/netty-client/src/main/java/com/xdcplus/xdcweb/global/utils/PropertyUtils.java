package com.xdcplus.xdcweb.global.utils;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdcplus.xdcweb.global.pojo.vo.PageVO;

import java.util.List;

/**
 * 属性工具类
 * @author Rong.Jia
 * @date 2020/01/14 09:22
 */
public class PropertyUtils {

    /**
     *  page 转vo 对象
     * @param page   分页查询结果对象
     * @param pageVO 分页查询结果vo对象
     * @date 2019/07/02 11:13:22
     * @author Rong.Jia
     */
    public static <T> void copyProperties(Page<?> page, PageVO<T> pageVO, List<T> records) {

        pageVO.setTotalPages(page.getPages());
        pageVO.setHasNext(page.hasNext());
        pageVO.setHasPrevious(page.hasPrevious());
        pageVO.setIsFirst(!page.hasPrevious());
        pageVO.setIsLast(!page.hasNext());
        pageVO.setTotal(page.getTotal());
        pageVO.setCurrentPage(page.getCurrent());
        pageVO.setPageSize(page.getSize());
        pageVO.setRecords(records);

    }

}
