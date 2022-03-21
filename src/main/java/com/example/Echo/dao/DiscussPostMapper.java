package com.example.Echo.dao;

import com.example.Echo.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {
    /**
     *
     * @param userId - 传入userId表示搜索这个user发布的帖子，不然传入0表示搜索所有帖子
     * @param offset - 用于分页，表示每一页开始时候的offset
     * @param limit - 用于分页，表示每一页有多少个post
     * @return
     */
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    // @Param用于给参数取别名
    // 如果只有一个参数，并且在<if>里使用，则必须加别名
    int selectDiscussPostRows(@Param("userId") int userId);



}
