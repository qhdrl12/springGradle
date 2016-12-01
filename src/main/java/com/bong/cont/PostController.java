package com.bong.cont;

import com.bong.vo.Post;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by qhdrl on 2016-12-01.
 */

@Controller
@EnableAutoConfiguration
public class PostController {
    @RequestMapping(value = "/posts/new", method = RequestMethod.GET)
    public String newPost(Model model) {
        System.out.printf("2222");
        model.addAttribute("post", new Post());
        return "new";
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", post);

        return "show";
    }
}
