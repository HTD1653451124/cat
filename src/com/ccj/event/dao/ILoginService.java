package com.ccj.event.dao;

public interface ILoginService <T>{
    public T login(String name ,String account,String password);
}
