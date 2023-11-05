package com.huang.luck.controller;


import com.huang.luck.service.ex.*;
import com.huang.luck.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpSession;
import java.rmi.ServerException;

public class BaseController {

    //static final声明后，就称为常量，不能改变
    public static final int OK=200;
  //请求处理方法，这个方法的返回值就是需要传递给前端的数据
    //自动将异常对象传递给此方法的参数列表上
    //当项目中产生了异常会被统一拦截到此方法中，这个方法此时就充当的是请求处理方法，方法的返回值直接给到前端
  @ExceptionHandler(ServerException.class) //用于统一抛出异常
  public JsonResult<Void> handleException(Throwable e){
      JsonResult<Void> result;
      result = new JsonResult<>(e);
      if (e instanceof UsernameDuplicatedException) {  //instantof用于判断对象类型
          result.setState(4000);
          result.setMessage("用户名已经被占用");
      }else if (e instanceof UsernameNotFoundException){
          result.setState(5001);
          result.setMessage("用户数据不存在");
      }else if (e instanceof PasswordNotMatchException){
          result.setState(5002);
          result.setMessage("用户名密码异常");
      } else if (e instanceof InsertException){
          result.setState(5000);
          result.setMessage("注册时产生未知的异常");
      }else if (e instanceof UpdateException){
          result.setState(5003);
          result.setMessage("更新时产生未知的异常");
      }
          return result;//这里才是传送给前端的结果集

  }
/*  这里的东西暂时没有用到
protected final Integer getuidFromSession(HttpSession session){
      return Integer.valueOf(session.getAttribute("uid").toString());
  }
  protected final String getUsernameFromSession(HttpSession session){
     return session.getAttribute("username").toString();
  }
    protected final Integer getaidFromSession(HttpSession session){
        return Integer.valueOf(session.getAttribute("aid").toString());
    }
    protected final String getAdminNameFromSession(HttpSession session){
        return session.getAttribute("AdminName").toString();
    }*/
}
