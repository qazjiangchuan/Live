package com.sugon.sugonlive.net;

import java.util.List;

/**
 * 接收Json对象的Response父类
 * Created by duke on 2016/12/6.
 */

public class BaseRes<T> {
    int total;
    List<T> datas;
    T data;
    String msg;
    int status;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public T getData() {

        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
