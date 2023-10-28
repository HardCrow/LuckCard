package com.huang.luck.service.UserServiceImpl;

import com.huang.luck.entity.Admin;
import com.huang.luck.entity.Goods;
import com.huang.luck.entity.LuckRecode;
import com.huang.luck.entity.User;
import com.huang.luck.mapper.UserMapper;
import com.huang.luck.service.UserService;
import com.huang.luck.service.ex.*;
import com.huang.luck.util.LuckTool.NumsRandom;
import com.huang.luck.util.LuckTool.SetPrice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@Service
public class UserServiceImpl implements UserService {


    //Integer返回插入的条目数量
    @Autowired
    UserMapper userMapper;

    @Override
    public void insert(User user) {
        String userName = user.getUserName();
        User result = userMapper.findByAccount(userName);
        if (result != null) {
            throw new UsernameDuplicatedException("用户名被占用");
        }
        Integer row = userMapper.insert(user);
        //e值的生成
        if (row != 1) {
            throw new InsertException("在用户注册的过程中产生了未知的异常");   //e值
        }
    }

    //Integer返回插入的条目数量
    @Override
    public User login(String account, String password) {
        User result = userMapper.findByAccount(account);
        if (result == null) {
            throw new UsernameNotFoundException("用户账号未找到,请重试");
        }
        String userpsd = result.getUserpsd();
        if (!password.equals(userpsd)) {
            throw new PasswordNotMatchException("用户密码不正确");
        }
        User user = new User();
        user.setId(result.getId());
        user.setUserName(result.getUserName());
        return user;
    }
//    int temp = (int)price;
  /*  @Override    //抢卡的前身==..==
    public List luck(int CardNum) {
        //数据共享要用static关键字 确保多个用户去操作同一个数组
        //synchronized关键字确保不会取到相同的数据
        //这里不应该传入形参price 应该把创建的操作重新写一个方法去操作
        //在此处调用return数组过来
        SetPrice setPrice = new SetPrice();
         List list = setPrice.SetPrice(10);
         // int temp = (int)price;
        //  List list = new ArrayList(temp);

        ArrayList arrayList = new ArrayList(CardNum);

         //   for (int i = 1; i <= temp; i++) {list.add(i);}
            if (CardNum == 0) {
                throw new CardNumsException("卡片数量不足，出现异常");
            }
            //待完工
            for (int i = 1; i <= CardNum; CardNum--) {
                int index = (int) (Math.random() * list.size());
                arrayList.add(list.get(index));
                list.remove(index);
            }
        System.out.println(arrayList);
            //return 剩余的卡片数和号码
        return list;
    }  */

    //测试用另一种想法
    //   开始这个方法前要先调用下面俩，传递商品的价格确定好卡片的数量
//      SetPrice setPrice = new SetPrice();
//        setPrice.SetPrice1(10);
    @Override
    public List LUCK(User user, Goods goods, int CardNum) {

        if (Goods.arraylist.isEmpty()) {
            throw new GoodsListNULLException("list为空");
        }
        ArrayList array = new ArrayList(CardNum);
        //   for (int i = 1; i <= temp; i++) {list.add(i);}
        if (CardNum == 0) {
            throw new CardNumsException("卡片数量不足，出现异常");
        }

        for (int i = 1; i <= CardNum; CardNum--) {
            int index = (int) (Math.random() * Goods.arraylist.size());
            //这里加一个数据库语句
            array.add(Goods.arraylist.get(index));
            //加CardRecode UserAccount GoodsName

            LuckRecode luckRecode = new LuckRecode();
            luckRecode.setCardRecode((int) Goods.arraylist.get(index));
            luckRecode.setUserAccount(user.getUserAccount());   //获取useraccount的值给cardrecode里面
            luckRecode.setGoodsName(goods.getGoodsName());       //获取goodsname的值给cardrecode里面
            userMapper.AddCard(luckRecode);                     //存入数据库里面  这里已经存到数据库里面了
            Goods.arraylist.remove(index);
        }
        return array;
    }


    @Override
    public Integer LuckCard(int Price) {
        int max = Price;
        int min = 1;
        long randomNum = System.currentTimeMillis();
        int nums = (int) (randomNum % (max - min) + min);
        //循环同一时间会产生相同的数
        // System.out.print(nums);
        return nums;
    }

    @Override
    public String CheckPrizeCard(int PrizeCard) {
        LuckRecode luckRecode = userMapper.CheckLuckCode(PrizeCard);
        String userAccount = luckRecode.getUserAccount();
        return userAccount;
    }

    @Override
    public void AddGoods(Goods goods) {
        String goodsName = goods.getGoodsName();
        Goods result = userMapper.findByGoodsName("goodsName");
        if (result == null) {
            userMapper.AddGoods(goods);
        } else
            System.out.println("商品已经存在，请勿重复添加");
    }

    //下面 是另一种方法
    @Override
    public void AddCard(Admin admin, int Price, Goods goods, String ListName) {
        //这是管理员利用价格去添加卡片数量的操作
        LuckRecode luckRecode = new LuckRecode();
        luckRecode.setListName(ListName);
        luckRecode.setCreateName(admin.getAccount());
        luckRecode.setGoodsName(goods.getGoodsName());
        for (int i = 1; i <= Price; i++) {
            luckRecode.setCardRecode(i);
            userMapper.AddCardGoods(luckRecode);
        }
    }

    @Override
    public void GetCard(User user, Integer Money, Integer num, Goods goods, String ListName) {
        //num是查询admin创建的卡片数量  应该在Controller层去查询出来然后调用本方法时去传入参数
        //user goods Money都是前端传过来的  其他都是后端生成的
        if (num < Money) {
            new Exception("买的卡片数太多了");
        }
        //随机数没有同步，也就是说下面的语句不安全，用户会买重复的卡片
        for (int i = 1; i <= Money; i++) {
          //  int max = num;
          //  int min = 1;
           // long randomNum = System.currentTimeMillis();
           // int nums = (int) (randomNum % (max - min) + min); //随机生成数
            NumsRandom numsRandom = new NumsRandom();
            Integer nums = numsRandom.NumsRandom(num);


            List<LuckRecode> luckRecodes = userMapper.CheckUserAccountIsNULL(goods.getGoodsName(), ListName, nums);
            //这里就只有一条数据
            System.out.println(luckRecodes.get(0).getUserAccount());

            if(luckRecodes.get(0).getUserAccount().equals("null")) {
                //这里尤其注意null是字符串并不是地址因此要用equals并且打上引号
                userMapper.UpdataGetAccount(nums, user.getUserAccount(), goods.getGoodsName(), ListName);
            }
            else {
                --i;
            }

        }
    }


}