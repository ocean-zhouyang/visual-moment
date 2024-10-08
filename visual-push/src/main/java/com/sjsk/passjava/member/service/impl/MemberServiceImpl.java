package com.sjsk.passjava.member.service.impl;

import com.sjsk.passjava.member.entity.MemberEntity;
import com.sjsk.passjava.member.service.MemberService;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sjsk.passjava.common.utils.PageUtils;
import com.sjsk.passjava.common.utils.Query;

import com.sjsk.passjava.member.dao.MemberDao;


@Service("memberService")
public class MemberServiceImpl extends ServiceImpl<MemberDao, MemberEntity> implements MemberService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<MemberEntity> page = this.page(
                new Query<MemberEntity>().getPage(params),
                new QueryWrapper<MemberEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    @Retryable(value = Exception.class,maxAttempts = 3,backoff = @Backoff(delay = 2000, multiplier = 1.5))
    public String sendCoupon(int num) throws Exception {

        if (num <= 0 ) {
            throw new Exception("发放的优惠券数量必须大于 0");
        }

        return "success";
    }

    @Override
    public MemberEntity getMemberByUserId(String userId) {
        return baseMapper.getMemberByUserId(userId);
    }

}