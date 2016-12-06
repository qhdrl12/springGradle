package com.bong.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * Created by qhdrl on 2016-12-01.
 */

@EqualsAndHashCode
public class Post {

    @Getter @Setter
    private long id;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String content;

    @Getter @Setter
    private Date created_at;

    @Getter @Setter
    private Date updated_at;
}
