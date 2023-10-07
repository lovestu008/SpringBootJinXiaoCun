package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;

public class QueryCustomer extends BaseQuery {

    private String name;//超市名字
    private String contact;// 联络人
    private String address;// 地址
    private String number;// 电话

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
