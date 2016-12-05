package com.bong.cont;

import com.bong.com.FixturesProperty;
import com.bong.repository.mappers.StbMapper;
import com.bong.vo.Article;
import com.bong.vo.Post;
import com.bong.vo.Stb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by qhdrl on 2016-12-01.
 */

@Controller
@EnableAutoConfiguration
public class PostController {

    static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired
    private FixturesProperty fixturesProperty;

    @Autowired StbMapper stbMapper;

    @RequestMapping(value = "/posts/new", method = RequestMethod.GET)
    public String newPost(Model model) {

        log.trace("TraceLog");
        log.debug("DebugLog");
        log.info("InfoLog");
        log.warn("WarnLog");

        model.addAttribute("post", new Post());

        return "new";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", post);

        return "show";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {

        List<Article> article = fixturesProperty.getArticles();
        log.info("article : " + article.get(0));
        Stb stb = stbMapper.selectTestStb();

//        log.info("stb id : " + stb.getStbId());
//        log.info("iptv_status_code : " + stb.getIptvStatusCode());
//        log.info("user_service_num : " + stb.getUserServiceNum());

        model.addAttribute("post", new Post());

        return "new";
    }
}
