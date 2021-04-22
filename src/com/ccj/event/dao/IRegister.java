package com.ccj.event.dao;

public interface IRegister<T>{
    public T register(String virtualName,String account,String password,String payPassword);
}
