package com.wql.mybatisplusdemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wql.mybatisplusdemo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserMapper extends BaseMapper<User> {
}
