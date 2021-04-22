package com.ccj.event.dao;

import com.ccj.event.entity.ScenicInfoTable;
import com.ccj.event.entity.TicketTable;

import java.util.Map;

public interface IFuzzyQuery <T>{
    public T fuzzyQuery(String clue );
}
