package com.louis.maggooo.core.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageRequest {
    /**
     * 当前页码
     */
    private int pageNum=1;
    /**
     * 每页数量
     */
    private int pageSize=10;
    /**
     * 查询参数
     */
    private Map<String,Object> param=new HashMap<>();

}