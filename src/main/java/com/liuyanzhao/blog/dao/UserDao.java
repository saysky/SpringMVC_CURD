package com.liuyanzhao.blog.dao;

import com.liuyanzhao.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author 言曌
 * @date 2017/11/28 下午3:31
 */

public interface UserDao extends JpaRepository<User, Integer> {

    //获得用户列表
    Page<User> findAllByOrderByIdDesc(Pageable pageable);


    //根据用户名昵称等信息模糊查询
    @Query("select u from User u where u.username like %?1% or u.nickname like %?1% or u.email like %?1% or u.website like %?1% or u.phone like %?1% or u.id = ?1 order by u.id desc")
    Page<User> findSearch(String query, Pageable pageable);

    //根据用户名查找用户是否存在
    List<User> findByUsername(String username);


}
