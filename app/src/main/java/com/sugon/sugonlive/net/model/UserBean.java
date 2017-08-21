package com.sugon.sugonlive.net.model;

import java.util.List;

/**
 *
 */
public class UserBean {
    private Integer id; // id
    private Integer orgId; // 归属部门
    private String name; // 名称
    private String loginName; // 登录名
    private String password; // 密码
    private String no; // 工号
    private String email; // 邮箱
    private String phone; // 电话
    private String mobile; // 手机
    private String photo; // 用户头像
    private String loginIp; // 最后登陆IP
    //    private Date loginDate; // 最后登陆时间
    private String createBy; // 创建者
    //    private Date createDate; // 创建时间
    private String updateBy; // 更新者
    //    private Date updateDate; // 更新时间
    private String remarks; // 备注信息

    private List<SysRoleBean> roleBeans; //当前用户的角色列表
    private String roleNameString;//当前用户的角色名

    private int level;//用户权限级别
    private String ssXzqh;//所属行政区划

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getSsXzqh() {
        return ssXzqh;
    }

    public void setSsXzqh(String ssXzqh) {
        this.ssXzqh = ssXzqh;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }


    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }


    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

//    public Date getLoginDate() {
//        return loginDate;
//    }
//
//    public void setLoginDate(Date loginDate) {
//        this.loginDate = loginDate;
//    }
//
//    public Date getCreateDate() {
//        return createDate;
//    }
//
//    public void setCreateDate(Date createDate) {
//        this.createDate = createDate;
//    }
//
//    public Date getUpdateDate() {
//        return updateDate;
//    }
//
//    public void setUpdateDate(Date updateDate) {
//        this.updateDate = updateDate;
//    }

    public void setRoleBeans(List<SysRoleBean> roleBeans) {
        this.roleBeans = roleBeans;
    }

    public void setRoleNameString(String roleNameString) {
        this.roleNameString = roleNameString;
    }

    public List<SysRoleBean> getRoleBeans() {
        return roleBeans;
    }

    public String getRoleNameString() {
        return roleNameString;
    }

}