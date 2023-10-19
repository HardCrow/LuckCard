package com.huang.luck.controller;


import com.huang.luck.entity.Goods;
import com.huang.luck.entity.User;
import com.huang.luck.service.ex.InsertException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Controller
public class UserController extends BaseController {

public void test(){
    User user = new User();
}

}
