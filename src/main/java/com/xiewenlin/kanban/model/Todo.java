package com.xiewenlin.kanban.model;

import lombok.Data;

/**
 * @author xiewenlin@frtauto.com
 * @ClassName Todo
 * @Description
 * @Date 2019/6/26 17:01
 * @Version V1.0.0
 */
@Data
public class Todo {
    public static final String ACTIVE    = "active";
    public static final String COMPLETED = "completed";

    private String value;
    private String status;

    public Todo() {

    }

    public Todo(String value, String status) {
        this.value = value;
        this.status = status;
    }
    @Override
    public String toString() {
        return "Todo(value='" + value + ", status='" + status + ')';
    }
}
