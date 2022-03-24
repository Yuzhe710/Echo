package com.example.Echo.service;

import com.example.Echo.dao.UserMapper;
import com.example.Echo.entity.User;
import com.example.Echo.util.CommunityUtil;
import com.example.Echo.util.MailClient;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${echo.path.domain}")
    private String domain; // 项目域名

    @Value("${server.servlet.context-path}")
    private String contextPath; // 项目名称

    public User findUserById(int id) {
        return userMapper.selectById(id);
    }

    public Map<String, Object> register(User user) {
        Map<String, Object> map = new HashMap<>();

        // 空值处理
        if (user == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        if (StringUtils.isEmptyOrWhitespaceOnly(user.getUsername())) {
            map.put("usernameMsg", "账号不能为空！");
            return map;
        }
        if (StringUtils.isEmptyOrWhitespaceOnly(user.getPassword())) {
            map.put("passwordMsg", "密码不能为空！");
            return map;
        }
        if (StringUtils.isEmptyOrWhitespaceOnly(user.getEmail())) {
            map.put("emailMsg", "邮箱不能为空！");
            return map;
        }

        // 验证账号
        User u = userMapper.selectByName(user.getUsername());
        if (u != null) {
            map.put("usernameMsg", "改账号已存在");
            return map;
        }
        // 验证邮箱
        u = userMapper.selectByEmail(user.getEmail());
        if (u != null) {
            map.put("emailMsg", "该邮箱已存在");
            return map;
        }

        // 所填信息没问题，注册用户
        user.setSalt(CommunityUtil.generateUUID().substring(0, 5));
        user.setPassword(CommunityUtil.md5(user.getPassword()) + user.getSalt());
        user.setType(0);
        user.setStatus(0); //未激活
        user.setActivationCode(CommunityUtil.generateUUID());
        user.setHeaderUrl(String.format("http://images.nowcoder.com/head/%dt.png", new Random().nextInt(1000)));
        userMapper.insertUser(user);

        // 激活邮件
        Context context = new Context();
        context.setVariable("email", user.getEmail());
        // http://localhost:8090/community/activation/101/code
        String url = domain + contextPath + "/activation/" + user.getId() + "/" + user.getActivationCode();// id为放入数据库后，myBatis创建
        context.setVariable("url", url);
        String content = templateEngine.process("/mail/activation", context);
        mailClient.sendMail(user.getEmail(), "激活账号", content);

        // 如果注册成功，map返回空，如果失败，则map中包含失败信息
        return map;
    }
}
