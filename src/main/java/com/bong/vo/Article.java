package com.bong.vo;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by ibong-gi on 2016. 12. 2..
 */
public class Article implements Serializable{

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String content;
}
