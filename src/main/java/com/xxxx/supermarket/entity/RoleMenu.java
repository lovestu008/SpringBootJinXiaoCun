package com.xxxx.supermarket.entity;

public class RoleMenu {
    private Integer id;

    private Integer menuId;

    private Integer roleId;

    private Integer aclValue;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getAclValue() {
        return aclValue;
    }

    public void setAclValue(Integer aclValue) {
        this.aclValue = aclValue;
    }
}