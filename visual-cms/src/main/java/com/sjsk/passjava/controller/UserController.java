package com.sjsk.passjava.controller;


import com.sjsk.passjava.common.ResponseCodeEnum;
import com.sjsk.passjava.common.utils.R;
import com.sjsk.passjava.config.JwtProperties;
import com.sjsk.passjava.dto.UserDto;
import com.sjsk.passjava.dto.UserLoginDto;
import com.sjsk.passjava.model.User;
import com.sjsk.passjava.service.UserService;
import com.sjsk.passjava.utils.JwtTokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Ocean
 * @since 2024-09-19
 */
@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private JwtProperties jwtProperties;
    @Resource
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册接口", notes = "注册接口说明")
    @PostMapping("regist")
    public Object regist(@Validated @RequestBody UserDto userDto) {

        User user = new User();
        BeanUtils.copyProperties(userDto, user);
        if (!StringUtils.hasLength(user.getUserName()) || !StringUtils.hasLength(user.getPassword())) {
            return R.error("用户名或密码不能为空");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return R.ok().put("user", user);
    }

    @ApiOperation(value = "登录接口", notes = "登录接口说明")
    @PostMapping("/login")
    public Object login(@Validated @RequestBody UserLoginDto loginDto) {
        // 从请求体中获取用户名密码
        String userId = loginDto.getUserId();
        String password = loginDto.getPassword();

        // 如果用户名和密码为空
        if (!StringUtils.hasLength(userId) || !StringUtils.hasLength(password)) {
            return R.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
        }
        // 根据 userId 去数据库查找该用户
        User user = userService.getById(userId);
        if (Objects.nonNull(user)) {
            // 将数据库的加密密码与用户明文密码做比对
            boolean isAuthenticated = passwordEncoder.matches(password, user.getPassword());
            // 如果密码匹配成功
            if (isAuthenticated) {
                // 通过 jwtTokenUtil 生成 JWT 令牌和刷新令牌
                Map<String, Object> tokenMap = jwtTokenUtil
                        .generateTokenAndRefreshToken(userId, user.getUserName());
                return R.ok(tokenMap);
            }
            // 如果密码匹配失败
            return R.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
        }
        // 如果未找到用户
        return R.error(ResponseCodeEnum.LOGIN_ERROR.getCode(), ResponseCodeEnum.LOGIN_ERROR.getMessage());
    }
}
