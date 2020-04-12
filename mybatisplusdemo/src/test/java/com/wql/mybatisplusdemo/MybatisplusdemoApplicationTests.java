package com.wql.mybatisplusdemo;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wql.mybatisplusdemo.entity.User;
import com.wql.mybatisplusdemo.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class MybatisplusdemoApplicationTests {

    @Autowired
    private UserMapper userMapper;

    //测试单元
    @Test
    void contextLoads() {
        //查询user表中的所有数据
        findAll();
    }
    //查询user表中的所有数据
    public void findAll(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    //添加
    @Test
    public void addUser(){
        User user = new User();
        user.setName("逻辑删除测试2");
        user.setEmail("12312313@qq.com");
        user.setAge(13);
        int insert = userMapper.insert(user);
        System.out.println("insert:"+insert);
    }
    //修改
    @Test
    public void updateUser(){
        User user = new User();
//        long id = 2;
        user.setId(1249251857893679106l);
        user.setAge(20);
//        user.setCreateTime(new Date());
//        user.setUpdateTime(new Date());
        userMapper.updateById(user);
    }
    //测试乐观锁
    @Test
    public void testLock(){
        User user = userMapper.selectById(1249275365906432002l);
        user.setAge(123);
        userMapper.updateById(user);
    }

    //多个id批量查询
    @Test
    public void selectSomany(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1249226868863774721l,1249251857893679106l,1249274685636448257l,1249275365906432002l));
        System.out.println(users);
    }


    @Test
    public void selectByMap(){
        HashMap<String,Object> map = new HashMap<>();
        map.put("name","alan");
        map.put("age",12);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

    //分页查询
    @Test
    public void PageSelect(){
        //new 一个 page对象  传入两个参数 当前页，每页显示条数
        Page<User> page = new Page<>(1,3);
        //调用selectPage
        IPage<User> userIPage = userMapper.selectPage(page, null);

        //通过page对象 获取分页数据
        System.out.println(page.getCurrent());//当前页
        System.out.println(page.getRecords());//每页的数据的List集合
        System.out.println(page.getSize());//当前页记录(条)数
        System.out.println(page.getTotal());//总条数
        System.out.println(page.getPages());//总页数
        System.out.println(page.hasNext());//是否有下一页
        System.out.println(page.hasPrevious());//是否有上一页
    }

    //物理删除
    @Test
    public void deleteById(){
        int i = userMapper.deleteById(1249275365906432002l);
    }

    //物理删除(批量)
    @Test
    public void deleteByIds(){
        int i = userMapper.deleteBatchIds(Arrays.asList(1249281774979108865l, 1249282126788878337l));
    }

    //逻辑删除
    @Test
    public void uDeleteById(){
        int i = userMapper.deleteById(1249304275209568258l);
    }

    //条件查询
    @Test
    public void findByXXX(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 10);
        List<User> users = userMapper.selectList(queryWrapper);
        System.out.println(users);
    }
}
