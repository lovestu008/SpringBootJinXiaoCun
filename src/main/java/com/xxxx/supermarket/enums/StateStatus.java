package com.xxxx.supermarket.enums;

/**
 * 分配状态枚举类
 */
public enum  StateStatus {
    // 未付款
    UNSTATE(1),
    // 已付款
    STATED(0);

    private Integer type;

    StateStatus(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}
