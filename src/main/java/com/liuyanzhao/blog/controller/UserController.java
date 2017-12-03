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

    @RequestMapping(value = "restoreData")
    public String restoreData() throws SQLException {
        Connection conn = DBUtil.getConnection();
        String sql = "DELETE FROM `user`";
        String sql2 = "INSERT INTO `user` VALUES ('1', '2017-12-02 13:51:54', 'liubei@shu.com', '刘玄德', null, '1', 'liubei', '/uploads/2017/12/刘备.png', '15711111111', 'http://liubei.com'), ('2', '2017-12-02 13:54:20', '007@san.com', '吕玲绮', '007', '1', 'lv007', '/uploads/2017/12/吕玲绮.png', '15711111112', 'http://007.com'), ('3', '2017-12-02 13:55:14', 'mayunlu@san.com', '马云禄', '123', '1', 'mayunlu', '/uploads/2017/12/马云禄.png', '13533333333', 'http://mayunlu.com'), ('4', '2017-12-02 13:57:15', 'xiaoji@wu.com', '孙尚香', null, '1', 'xiaoji', '/uploads/2017/12/孙尚香.png', '15771111111', 'http://xiaoji.com'), ('5', '2017-12-02 13:58:37', 'zilong@shu.com', '常山赵子龙', null, '1', 'zilong', '/uploads/2017/12/赵云.png', '13545234522', 'http://zilong.com'), ('6', '2017-12-02 13:59:47', 'machao@shu.com', '马孟起', null, '1', 'chaochao', '/uploads/2017/12/马超.png', '15712312312', 'http://machao.com'), ('7', '2017-12-02 14:01:02', 'lvbu@san.com', '奉先', '123', '1', 'lvbu', '/uploads/2017/12/吕布.png', '18111111111', 'http://lvbu.com'), ('8', '2017-12-02 14:02:06', 'wolong@san.com', '卧龙', '134', '1', 'kming', '/uploads/2017/12/诸葛亮.png', '15777777777', 'http://wolong.com'), ('9', '2017-12-02 14:03:40', 'zhouyu@wu.com', '周郎', null, '1', 'yuyu', '/uploads/2017/12/周瑜.png', '13711111112', 'http://zhouyu.com'), ('10', '2017-12-02 14:04:51', 'yide@shu.com', '铁血翼德', null, '1', 'feir', '/uploads/2017/12/张飞.png', '15766666666', 'http://yide.com'), ('11', '2017-12-02 14:05:57', 'benchu@han.com', '袁本初', '123', '1', 'benchu', '/uploads/2017/12/袁绍.png', '13556544533', 'http://benchu.com'), ('12', '2017-12-02 14:08:52', 'yanliang@han.com', '颜良', 'yan', '1', 'yanliang', '/uploads/2017/12/颜良.png', '13232323232', 'http://yanliang.com'), ('13', '2017-12-02 14:09:53', 'xiaoqiao@wu.com', '小乔', 'qiao1', '1', 'xiaoqiao', '/uploads/2017/12/小乔.png', '13434545343', 'http://xiaoqiao.com'), ('14', '2017-12-02 14:10:45', 'yuan@wei.com', '夏侯渊', 'yuan', '1', 'yuan', '/uploads/2017/12/夏侯渊.png', '15434654321', 'http://yuan.com'), ('15', '2017-12-02 14:11:58', 'sunjian@wu.com', '江东之虎', 'sun', '1', 'sunjian', '/uploads/2017/12/孙坚.png', '15666666654', 'http://sunjian.com'), ('16', '2017-12-02 14:12:35', 'sunce@wu.com', '小霸王', 'ce', '1', 'sunce', '/uploads/2017/12/孙策.png', '15665656565', 'http://sunce.com'), ('17', '2017-12-02 14:13:30', 'lvmeng@wu.com', '吕蒙', 'lvmeng', '1', 'lvmeng', '/uploads/2017/12/吕蒙.png', '18778777777', 'http://lvmeng.com'), ('18', '2017-12-02 14:14:45', 'luxun@wu.com', '陆逊', 'lu', '1', 'xunge', '/uploads/2017/12/陆逊.png', '15666666566', 'http://luxun.com'), ('19', '2017-12-02 14:16:03', 'huangzhong@shu.com', '黄汉升', '123', '1', 'zhong', '/uploads/2017/12/黄忠.png', '15645454445', 'http://huangz.com'), ('20', '2017-12-02 14:17:13', 'guojia@wei.com', '鬼才', '123', '1', 'guojia', '/uploads/2017/12/郭嘉.png', '15434333333', 'http://guojia.com'), ('21', '2017-12-02 14:18:07', 'yunchang@shu.com', '美髯公', 'chang', '1', 'yunchang', '/uploads/2017/12/关羽.png', '15788886666', 'http://yunchang.com'), ('22', '2017-12-02 14:19:52', 'guanyinping@shu.com', '关三小姐', 'guan', '1', 'xiaoguan', '/uploads/2017/12/关银屏.png', '15766666887', 'http://guanyingping.com'), ('23', '2017-12-02 14:20:45', 'ganning@wu.com', '兴霸', '123', '1', 'ning', '/uploads/2017/12/甘宁.png', '15444442222', 'http://ganning.com'), ('24', '2017-12-02 14:21:59', 'diaochan@han.com', '闭月', 'chan', '1', 'chan', '/uploads/2017/12/貂蝉.png', '15688885555', 'http://diaochan.com'), ('25', '2017-12-02 14:22:39', 'dianwei@wei.com', '典韦', 'wei', '1', 'weiwei', '/uploads/2017/12/典韦.png', '13434333333', 'http://dianwei.com'), ('26', '2017-12-02 14:23:26', 'dengai@wei.com', '邓艾', 'ai', '1', 'dengai', '/uploads/2017/12/邓艾.png', '13535353352', 'http://dengai.com'), ('27', '2017-12-02 14:24:40', 'daqiao@wu.com', '大乔', '123', '1', 'daqiao', '/uploads/2017/12/大乔.png', '18788223344', 'http://daqiao.com'), ('28', '2017-12-02 14:25:50', 'weiwu@wei.com', '魏武帝', 'cao', '1', 'mengde', '/uploads/2017/12/曹操.png', '18888888886', 'http://weiwu.com'), ('29', '2017-12-02 14:26:40', 'bulianshi@wu.com', '步练师', '123', '1', 'bulianshi', '/uploads/2017/12/步练师.png', '13455443344', 'http://bulianshi.com');";
        PreparedStatement ptmt = conn.prepareStatement(sql);
        PreparedStatement ptmt2 = conn.prepareStatement(sql2);
        ptmt.executeUpdate();
        ptmt2.executeUpdate();
        return "redirect:/admin/user/list";
    }

}

