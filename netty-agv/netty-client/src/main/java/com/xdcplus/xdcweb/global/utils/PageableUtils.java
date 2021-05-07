package com.xdcplus.xdcweb.global.utils;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xdcplus.xdcweb.global.pojo.dto.PageDTO;
import com.xdcplus.xdcweb.global.pojo.dto.SortDTO;

import java.util.StringJoiner;

/**
 *  分页封装类
 * @author Rong.Jia
 * @date 2018/6/20 10:58
 */
public class PageableUtils {

    /**
     * 默认排序
     */
    public static final String  DEFAULT_ORDER_TYPE = "desc";

    /**
     * 默认排序字段
     */
    public static final String DEFAULT_ORDER_FIELD = "id";

    /**
     * 获取基础分页对象
     * @param pageDTO 分页对象
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(PageDTO pageDTO, T t) {

        return basicPage(pageDTO.getCurrentPage(), pageDTO.getPageSize(),
                pageDTO.getOrderType(), pageDTO.getOrderField(), t);
    }

    /**
     * 获取基础分页对象
     * @param currentPage 获取第几页
     * @param pageSize 每页条数
     * @param orderField 排序字段
     * @param orderType 排序类型
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long currentPage, Long pageSize,
                                        String orderType, String orderField,
                                        T t) {

        return basicPage(currentPage, pageSize, new SortDTO(orderType, orderField), t);
    }

    /**
     * 获取基础分页对象
     * @param currentPage 获取第几页
     * @param pageSize 每页条数
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long currentPage, Long pageSize, T t) {

        return basicPage(currentPage, pageSize, DEFAULT_ORDER_TYPE, DEFAULT_ORDER_FIELD, t);
    }

    /**
     * 获取基础分页对象
     * @param page 获取第几页
     * @param <T> 实体类型
     * @param size 每页条数
     * @param dto 排序对象
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long page, Long size, SortDTO dto, T t) {

        page = (page == null || page < 0) ? 1 : page;

        size = (size == null || size <= 0) ? 20 : size;

        Page<T> pageHelper = new Page<T>(page, size, Boolean.TRUE);
        if (DEFAULT_ORDER_TYPE.equals(dto.getOrderType())) {
            pageHelper.addOrder(OrderItem.desc(dto.getOrderField()));
        }else {
            pageHelper.addOrder(OrderItem.asc(dto.getOrderField()));
        }

        return pageHelper;
    }

    /**
     * 获取基础分页对象，每页条数默认20条
     *  - 默认以id降序排序
     * @param page 获取第几页
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long page, T t) {

        return basicPage(page, 0L,
                new SortDTO(DEFAULT_ORDER_TYPE, DEFAULT_ORDER_FIELD), t);
    }

    /**
     * 获取基础分页对象，每页条数默认20条
     * @param page 获取第几页
     * @param dto 排序对象
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long page, SortDTO dto, T t) {
        return basicPage(page, 0L, dto, t);
    }

    /**
     * 获取基础分页对象，排序方式默认降序
     * @param page 获取第几页
     * @param size 每页条数
     * @param orderField 排序字段
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long page, Long size, String orderField, T t) {
        return basicPage(page, size, new SortDTO(DEFAULT_ORDER_TYPE, orderField), t);
    }

    /**
     * 获取基础分页对象
     *  - 每页条数默认20条
     *  - 排序方式默认降序
     * @param page 获取第几页
     * @param orderField 排序字段
     * @param <T> 实体类型
     * @return 分页查询对象
     */
    public static <T> Page<T> basicPage(Long page, String orderField, T t) {
        return basicPage(page, 0L, new SortDTO(DEFAULT_ORDER_TYPE, orderField), t);
    }

    public static String basicSort() {
        return basicSort(DEFAULT_ORDER_TYPE, DEFAULT_ORDER_FIELD);
    }

    public static String basicSort(String orderType, String orderField) {

        if (StrUtil.isBlank(orderType)) {
            orderType = DEFAULT_ORDER_TYPE;
        }

        if (StrUtil.isBlank(orderField)) {
            orderField =  DEFAULT_ORDER_FIELD;
        }

        StringJoiner stringJoiner = new StringJoiner(StrUtil.SPACE);
        stringJoiner.add(orderField).add(orderType);

        return stringJoiner.toString();
    }

}
