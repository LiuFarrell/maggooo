package com.louis.maggooo.core.page;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.louis.maggooo.common.utils.ReflectionUtils;


import java.util.List;

public class MybatisPageHelper {
    public static final String findPage = "findPage";

    public static PageResult findPage(PageRequest pageRequest, Object mapper) {
        return findPage(pageRequest, mapper, findPage);
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public static PageResult findPage(PageRequest pageRequest, Object mapper, String queryMethodName, Object... args) {

        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);

        //利用反射调用查询方法
        Object result = ReflectionUtils.invoke(mapper, queryMethodName, args);
        return getPageResult(new PageInfo<>((List) result));

    }

    private static PageResult getPageResult(PageInfo<?> pageInfo) {
        return PageResult.builder().pageNum(pageInfo.getPageNum()).pageSize(pageInfo.getPageSize())
                .totalSize(pageInfo.getTotal()).totalPages(pageInfo.getPages())
                .content(pageInfo.getList()).build();
    }

}