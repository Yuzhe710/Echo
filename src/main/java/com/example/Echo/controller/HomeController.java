package com.example.Echo.controller;

import com.example.Echo.entity.DiscussPost;
import com.example.Echo.entity.Page;
import com.example.Echo.entity.User;
import com.example.Echo.service.DiscussPostService;
import com.example.Echo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private DiscussPostService discussPostService;

    @Autowired
    private UserService userService;

    @RequestMapping(path="/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // 方法调用栈，SpringMVC会自动实例化Model和Page, 并将Page注入Model
        // 所以，在thymeleaf中可以直接访问Page对象中的数据
        page.setRows(discussPostService.findDiscussionPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.findDiscussPosts(0, page.getOffSet(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        for (DiscussPost post : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("post", post);
            User user = userService.findUserById(post.getUserId());
            map.put("user", user);
            discussPosts.add(map);
        }

        model.addAttribute("discussPosts", discussPosts);
        // 返回模板路径
        return "/index";
    }
}
