package com.liuyanzhao.blog.dao;

import com.liuyanzhao.blog.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 言曌
 * @date 2017/11/28 下午4:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations ={"classpath:applicationContext.xml"})
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testSave() {
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("123456");
        user.setNickname("张三");
        user.setEmail("zhangsan@qq.com");
        user.setStatus(1);
        userDao.save(user);
    }

    @Test
    public void testDelete() {

        //userDao.delete(1);
    }

}