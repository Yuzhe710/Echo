package com.example.Echo.service;

import com.example.Echo.dao.DiscussPostMapper;
import com.example.Echo.entity.DiscussPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscussPostService {

    @Autowired
    private DiscussPostMapper discussPostMapper;

    public List<DiscussPost> findDiscussPosts(int userId, int offset, int limit) {
        return discussPostMapper.selectDiscussPosts(userId, offset, limit);
    }

    public int findDiscussionPostRows(int userId) {
        return discussPostMapper.selectDiscussPostRows(userId);
    }

}
