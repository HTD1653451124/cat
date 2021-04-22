package com.ccj.event.service;

import java.util.regex.Pattern;

public class Check {


    /*
     判断注册时的输入是否合法，其中账号必须为数字（考虑到可能存在国外用户，故不限制数字位数），昵称不为空，两次输入的密码要一致，全部满足才返回true
    * */
    public boolean checkRegister(String phoneNum,String virtualName,String password1,String password2,String password3,String password4){
        for (int i  = 0;i<phoneNum.length();i++){
            if (!Character.isDigit(phoneNum.charAt(i))){
                return  false;
            }
        }

        if (virtualName!=null||password1.equals(password2)||password3.equals(password4)){
            return true;
        }
        else return false;
    }
    public boolean checkAdm(String last,String tickerNum,String price){
        for (int i  = 0;i<last.length();i++){
            if (!Character.isDigit(last.charAt(i))){
                return  false;
            }
        }
        for (int i  = 0;i<tickerNum.length();i++){
            if (!Character.isDigit(tickerNum.charAt(i))){
                return  false;
            }
        }
        for (int i  = 0;i<price.length();i++){
            if (!Character.isDigit(price.charAt(i))){
                return  false;
            }
        }
        return true;
    }

}
