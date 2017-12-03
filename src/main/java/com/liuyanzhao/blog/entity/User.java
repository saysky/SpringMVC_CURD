package com.liuyanzhao.blog.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author 言曌
 * @date 2017/11/28 下午3:30
 */

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 7419229779731522702L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(length = 20)
    private String username;

    @Column(length = 50)
    private String password;

    @Column(length = 20)
    private String nickname;

    @Column(length = 50)
    private String email;

    @Column(name = "create_time",length = 50)
    private Date createTime = new Date();

    @Column(length = 1)
    private Integer status = 1;

    private String avatar;

    @Column(length = 50)
    private String website;

    @Column(name = "phone",length = 20)
    private String phone;

    public void setId(Integer id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
