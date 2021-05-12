/**
 * Copyright 2021 bejson.com
 */
package com.alu.itoken.service.sso.entity;
import java.util.List;

/**
 * Auto-generated: 2021-02-12 12:29:49
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class DataBean {

    private List<String> roles;
    private String introduction;
    private String avatar;
    private String name;
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    public List<String> getRoles() {
        return roles;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
    public String getIntroduction() {
        return introduction;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
    public String getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
