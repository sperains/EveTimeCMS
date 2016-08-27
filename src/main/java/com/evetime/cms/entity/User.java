package com.evetime.cms.entity;

/**
 * Created by Rains
 * on 2016-05-12.
 */
public class User {

    private  String id ;
    private String userName ;
    private String password;
    private String name;
    private String companyId;
    private int groupId ;
    private String groupName;
    private String companyName;
    private String email;
    private Integer deptId;
    private String deptName;
    private Integer posId;
    private String posName;
    private String pic;
    private String tel;
    private String createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getPosId() {
        return posId;
    }

    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", companyId='" + companyId + '\'' +
                ", groupId=" + groupId +
                ", groupName='" + groupName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", email='" + email + '\'' +
                ", deptId=" + deptId +
                ", deptName='" + deptName + '\'' +
                ", posId=" + posId +
                ", posName='" + posName + '\'' +
                ", pic='" + pic + '\'' +
                ", tel='" + tel + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
