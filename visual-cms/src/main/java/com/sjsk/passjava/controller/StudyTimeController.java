package com.sjsk.passjava.controller;

import java.util.Arrays;
import java.util.Map;

import com.sjsk.passjava.model.StudyTimeEntity;
import com.sjsk.passjava.service.StudyTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sjsk.passjava.common.utils.PageUtils;
import com.sjsk.passjava.common.utils.R;



/**
 * 学习-用户学习时常表
 *
 * @author 公众号：悟空聊架构
 * @site www.passjava.cn
 * @date 2020-04-15 17:50:49
 */
@RestController
@RequestMapping("study/studytime")
public class StudyTimeController {
    @Autowired
    private StudyTimeService studyTimeService;

    @GetMapping("/member/list/test/{id}")
    public R memberStudyTimeTest(@PathVariable("id") Long id) {
        StudyTimeEntity studyTimeEntity = new StudyTimeEntity();
        studyTimeEntity.setTotalTime(100); // 学习时长：100分钟
        studyTimeEntity.setQuesTypeId(1L); // 题目类型：1 （javaBasic）

        return R.ok().put("studytime", Arrays.asList(studyTimeEntity));
    }

    /**
     * 列表
     */
    @PostMapping("/list")
    //@RequiresPermissions("study:studytime:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = studyTimeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    //@RequiresPermissions("study:studytime:info")
    public R info(@PathVariable("id") Long id){
		StudyTimeEntity studyTime = studyTimeService.getById(id);

        return R.ok().put("studyTime", studyTime);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    //@RequiresPermissions("study:studytime:save")
    public R save(@RequestBody StudyTimeEntity studyTime){
		studyTimeService.save(studyTime);

        return R.ok();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    //@RequiresPermissions("study:studytime:update")
    public R update(@RequestBody StudyTimeEntity studyTime){
		studyTimeService.updateById(studyTime);

        return R.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/delete")
    //@RequiresPermissions("study:studytime:delete")
    public R delete(@RequestBody Long[] ids){
		studyTimeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
