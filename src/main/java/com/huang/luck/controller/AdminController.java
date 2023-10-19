package com.huang.luck.controller;

import com.huang.luck.entity.Admin;
import com.huang.luck.service.UserService;
import com.huang.luck.util.JsonResult;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/admin")
@Controller

public class AdminController extends BaseController {

    @Autowired
    UserService userService;

     // public List SetPrice(){
      //    List list = userService.GoodsPrice(10);
    //  return list;
    // }
}
