package com.sjsk.passjava.service.impl;

import com.sjsk.passjava.mapper.StudyTimeDao;
import com.sjsk.passjava.model.StudyTimeEntity;
import com.sjsk.passjava.service.StudyTimeService;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjsk.passjava.common.utils.PageUtils;
import com.sjsk.passjava.common.utils.Query;


@Service("studyTimeService")
public class StudyTimeServiceImpl extends ServiceImpl<StudyTimeDao, StudyTimeEntity> implements StudyTimeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<StudyTimeEntity> page = this.page(
                new Query<StudyTimeEntity>().getPage(params),
                new QueryWrapper<StudyTimeEntity>()
        );

        return new PageUtils(page);
    }

}