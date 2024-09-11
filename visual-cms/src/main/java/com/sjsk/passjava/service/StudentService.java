package com.sjsk.passjava.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjsk.passjava.mapper.StudentMapper;
import com.sjsk.passjava.model.Student;
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
public class StudentService extends ServiceImpl<StudentMapper, Student> implements IService<Student>   {

}

