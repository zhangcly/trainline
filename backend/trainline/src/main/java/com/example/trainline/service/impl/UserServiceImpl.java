package com.example.trainline.service.impl;

import com.example.trainline.entity.User;
import com.example.trainline.mapper.UserMapper;
import com.example.trainline.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhangc
 * @since 2019-06-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
