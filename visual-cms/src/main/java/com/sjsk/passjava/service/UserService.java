package com.sjsk.passjava.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjsk.passjava.mapper.UserMapper;
import com.sjsk.passjava.model.User;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ocean
 * @since 2024-09-19
 */

@Service
public class UserService extends ServiceImpl<UserMapper, User> implements IService<User>   {

}

