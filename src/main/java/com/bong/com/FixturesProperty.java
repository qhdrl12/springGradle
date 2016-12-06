package com.bong.com;

import com.bong.domain.Article;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ibong-gi on 2016. 12. 2..
 */

@Component
@ConfigurationProperties(locations = "classpath:fixtures.yml", prefix = "fixtures")
public class FixturesProperty {
    @NestedConfigurationProperty
    private List<Article> articles = new ArrayList<>();

    public List<Article> getArticles(){
        return articles;
    }
}
