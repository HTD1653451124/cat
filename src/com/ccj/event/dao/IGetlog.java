package com.ccj.event.dao;

import com.ccj.event.entity.LogTable;
import com.ccj.event.entity.ScenicInfoTable;

import java.util.Map;

public interface IGetlog <T>{
    public T getlog(Integer uid, Integer sid);
}
