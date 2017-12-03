package com.liuyanzhao.blog.service.Impl;

import com.liuyanzhao.blog.converter.User2UserDTOConverter;
import com.liuyanzhao.blog.dao.UserDao;
import com.liuyanzhao.blog.dto.UserDTO;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.enums.ResultEnum;
import com.liuyanzhao.blog.exception.UserException;
import com.liuyanzhao.blog.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author 言曌
 * @date 2017/11/28 下午3:32
 */

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Override
    public UserDTO findOne(Integer id) {
        UserDTO userDTO = new UserDTO();
        User user =  userDao.findOne(id);
        //如果失败，抛异常
        if(user == null) {
            log.error("【用户】用户不存在，id={}",id);
            throw new UserException(ResultEnum.USER_NOT_EXIST);
        }
        BeanUtils.copyProperties(user,userDTO);
        return userDTO;
    }

    //分页获得列表
    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        Page<User> userPage = userDao.findAllByOrderByIdDesc(pageable);
        List<UserDTO> userDTOList = User2UserDTOConverter.convert(userPage.getContent());
        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());
        return userDTOPage;
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        //用户名已存在
        if(userDao.findByUsername(userDTO.getUsername()).size() > 0) {
            log.error("【用户】创建失败，用户名已存在，username={}",userDTO.getUsername());
            throw new UserException(ResultEnum.USERNAME_IS_EXIST);
        }
        User user = new User();
        BeanUtils.copyProperties(userDTO,user);
        user.setStatus(1);
        user.setCreateTime(new Date());
        User saveResult = userDao.save(user);
        //如果失败，抛异常
        if(saveResult == null) {
            log.error("【用户】创建失败，user={}",user);
            throw new UserException(ResultEnum.USER_CREATE_FAIL);
        }
        return userDTO;
    }

    @Override
    public void delete(Integer id) {
        userDao.delete(id);
    }

    @Override
    @Transactional
    public void update(UserDTO userDTO) {
        User user = userDao.findOne(userDTO.getId());
        //如果用户记录不存在
        if(user == null) {
            log.error("【用户】不存在,id=",userDTO.getId());
            throw new UserException(ResultEnum.USER_NOT_EXIST);
        }
        //用户名已存在
        //其实只会查到一条记录，因为设置了字段unique
        List<User> temp = userDao.findByUsername(userDTO.getUsername().trim());
        //如果用户名存在但是不是当前的用户名
        if(temp.size() > 0 && !temp.get(0).getId().equals(userDTO.getId())) {
            log.error("【用户】更新失败，用户名已存在，username={}",userDTO.getUsername());
            throw new UserException(ResultEnum.USERNAME_IS_EXIST);
        }
        userDTO.setCreateTime(user.getCreateTime());
        userDTO.setPassword(user.getPassword());
        BeanUtils.copyProperties(userDTO,user);
        User updateResult = userDao.save(user);
        //如果失败，抛异常
        if(updateResult == null) {
            log.error("【用户】更新失败，user={}",user);
            throw new UserException(ResultEnum.USER_UPDATE_FAIL);
        }
    }

    @Override
    public Page<UserDTO> findSearch(String query,Pageable pageable) {
        Page<User> userPage = userDao.findSearch(query,pageable);
        List<UserDTO> userDTOList  = User2UserDTOConverter.convert(userPage.getContent());
        Page<UserDTO> userDTOPage = new PageImpl<UserDTO>(userDTOList,pageable,userPage.getTotalElements());
        return userDTOPage;
    }

    @Override
    @Transactional
    public void deleteMore(Integer[] ids) {
        for(int i=0;i<ids.length;i++) {
            userDao.delete(ids[i]);
        }
    }

    @Override
    public List<User> findByUsername(String username) {
        return userDao.findByUsername(username);
    }

}
