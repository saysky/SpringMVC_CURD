package com.liuyanzhao.blog.controller;

import com.liuyanzhao.blog.dto.UserDTO;
import com.liuyanzhao.blog.entity.User;
import com.liuyanzhao.blog.service.UserService;
import com.liuyanzhao.blog.util.DBUtil;
import com.liuyanzhao.blog.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * @author 言曌
 * @date 2017/11/28 下午3:33
 */

@Controller
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/list")
    public ModelAndView listUser(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                 @RequestParam(value = "size",defaultValue = "5") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        PageRequest request = new PageRequest(page-1,size);
        Page<UserDTO> userDTOPage = userService.findAll(request);
        modelAndView.addObject("userDTOPage",userDTOPage);
        modelAndView.setViewName("/admin/user/list");
        return modelAndView;
    }


    @RequestMapping(value = "/profile/{id}")
    public ModelAndView profile(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = userService.findOne(id);
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.setViewName("/admin/user/profile");
        return modelAndView;
    }

    @RequestMapping(value = "/add")
    public ModelAndView addView() {
        return new ModelAndView("/admin/user/add");
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addSubmit(UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/admin/user/list";
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView editView(@PathVariable("id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        UserDTO userDTO = userService.findOne(id);
        modelAndView.addObject("userDTO",userDTO);
        modelAndView.setViewName("/admin/user/edit");
        return modelAndView;
    }

    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public String editSubmit(UserDTO userDTO) {
        userService.update(userDTO);
        return "redirect:/admin/user/profile/"+userDTO.getId();
    }


    @RequestMapping(value = "/delete")
    @ResponseBody
    public ResultVO delete(@Param("id")Integer id) {
        userService.delete(id);
        ResultVO resultVO = new ResultVO();
        resultVO.setMsg("成功");
        resultVO.setCode(0);
        return resultVO;
    }

    //批量删除文章
    @RequestMapping(value = "/deleteMore",method = RequestMethod.POST)
    @ResponseBody
    public ResultVO deleteMore(@Param("ids")String ids) throws Exception {
        ResultVO resultVO = new ResultVO();
        if(ids != null && ids != "") {
            String[] arr = ids.split(",");
            Integer[] id = new Integer[arr.length];
            for(int i=0;i<arr.length;i++) {
                id[i] = Integer.valueOf(arr[i]);
            }
            //调用service批量删除
            userService.deleteMore(id);
        }
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }


    @RequestMapping(value = "/search")
    public ModelAndView search(@RequestParam(value = "query") String query,
                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                               @RequestParam(value = "size",defaultValue = "5") Integer size) {
        ModelAndView modelAndView = new ModelAndView();
        query = query.trim();//去掉首尾空格
        PageRequest pageRequest = new PageRequest(page-1,size);
        Page<UserDTO> userDTOPage = userService.findSearch(query,pageRequest);
        modelAndView.addObject("userDTOPage",userDTOPage);
        modelAndView.addObject("query",query);
        modelAndView.setViewName("/admin/user/search");
        return modelAndView;
    }

    //判断用户名是否存在
    @RequestMapping(value = "isUsernameExisted")
    @ResponseBody
    public ResultVO isUsernameExisted(@Param("username") String username, @Param("id") Integer id) {
        ResultVO resultVO = new ResultVO();
        username = username.trim();
        //用户名已存在
        List<User> temp = userService.findByUsername(username);
        if(temp.size() > 0 && !temp.get(0).getId().equals(id)) {
            resultVO.setCode(1);
            resultVO.setMsg("用户名已存在");
        } else {
            resultVO.setCode(0);
            resultVO.setMsg("用户名可使用");
        }
        return resultVO;
    }


}

