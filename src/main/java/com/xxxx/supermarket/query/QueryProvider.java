package com.xxxx.supermarket.query;

import com.xxxx.supermarket.base.BaseQuery;

public class QueryProvider extends BaseQuery {
    private String name;//供货商
    private String contact;// 联络人
    private String address;// 地址
    private String number;// 电话

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
