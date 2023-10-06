package com.xxxx.supermarket.entity;

public class Role{
    private Integer id;

    private String bz;

    private String name;

    private String remarks;

    private Integer isDel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks == null ? null : remarks.trim();
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", bz='" + bz + '\'' +
                ", name='" + name + '\'' +
                ", remarks='" + remarks + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}