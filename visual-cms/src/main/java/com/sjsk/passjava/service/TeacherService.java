package com.sjsk.passjava.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjsk.passjava.mapper.TeacherMapper;
import com.sjsk.passjava.model.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Ocean
 * @since 2024-09-11
 */

@Service
public class TeacherService extends ServiceImpl<TeacherMapper, Teacher> implements IService<Teacher>   {

}

