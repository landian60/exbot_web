package com.exbot.model;

import lombok.Data;

/**
 * @author: tan
 * @Date: 2019/1/16 16:03
 * Describe: 访客
 */
@Data
public class Visitor {

    private int id;

    /**
     * 访客人数
     */
    private long visitorNum;

    /**
     * 当前页的name or 文章名
     */
    private String pageName;
}
