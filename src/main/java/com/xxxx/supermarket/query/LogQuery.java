package com.xxxx.supermarket.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xxxx.supermarket.base.BaseQuery;
import com.xxxx.supermarket.base.LogBaseQuery;

import java.util.Date;

public class LogQuery extends LogBaseQuery {
    //条件查询
    private String type;
    private String uname;


    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
