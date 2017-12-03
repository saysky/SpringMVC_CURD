package com.liuyanzhao.blog.service;

import com.liuyanzhao.blog.dto.UserDTO;
import com.liuyanzhao.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


/**
 * @author 言曌
 * @date 2017/11/28 下午3:32
 */


public interface UserService {

    //查询一条记录
    UserDTO findOne(Integer id);

    //全部查询
    Page<UserDTO> findAll(Pageable pageable);

    //添加记录
    UserDTO save(UserDTO userDTO);

    //删除记录
    void delete(Integer id);

    //更新记录
    void update(UserDTO userDTO);

    //根据用多种信息查询模糊用户
    Page<UserDTO> findSearch(String username,Pageable pageable);

    //批量删除
    void deleteMore(Integer[] ids);

    //根据用户名查找用户是否存在
    List<User> findByUsername(String username);
}
