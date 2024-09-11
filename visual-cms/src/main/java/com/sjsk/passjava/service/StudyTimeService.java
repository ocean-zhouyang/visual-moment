package com.sjsk.passjava.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sjsk.passjava.common.utils.PageUtils;
import com.sjsk.passjava.model.StudyTimeEntity;

import java.util.Map;

/**
 * 学习-用户学习时常表
 *
 * @author 公众号：悟空聊架构
 * @site www.passjava.cn
 * @date 2020-04-15 17:50:49
 */
public interface StudyTimeService extends IService<StudyTimeEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

