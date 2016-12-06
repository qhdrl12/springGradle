package com.bong.cont;

import com.bong.com.FixturesProperty;

import com.bong.svc.StbService;
import com.bong.domain.Article;
import com.bong.domain.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by qhdrl on 2016-12-01.
 */

@Controller
public class PostController {

    static final Logger log = LoggerFactory.getLogger(PostController.class);

    @Autowired private FixturesProperty fixturesProperty;
    @Autowired StbService stbService;

    /*
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
    */

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(Model model) {

        List<Article> article = fixturesProperty.getArticles();
        log.info("article : " + article.get(0));

        stbService.getTestStb();

        model.addAttribute("post", new Post());

        return "new";
    }
}
