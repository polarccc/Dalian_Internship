package com.ruoyi.joinactivity.service.impl;

import java.util.List;

import com.ruoyi.activity.domain.SysActivities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.joinactivity.mapper.SysJoinActivityMapper;
import com.ruoyi.joinactivity.domain.SysJoinActivity;
import com.ruoyi.joinactivity.service.ISysJoinActivityService;

/**
 * 参加活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@Service
public class SysJoinActivityServiceImpl implements ISysJoinActivityService 
{
    @Autowired
    private SysJoinActivityMapper sysJoinActivityMapper;

    /**
     * 查询参加活动
     * 
     * @param id 参加活动主键
     * @return 参加活动
     */
    @Override
    public SysJoinActivity selectSysJoinActivityById(Long id)
    {
        return sysJoinActivityMapper.selectSysJoinActivityById(id);
    }

    /**
     * 查询参加活动列表
     * 
     * @param sysJoinActivity 参加活动
     * @return 参加活动
     */
    @Override
    public List<SysJoinActivity> selectSysJoinActivityList(SysJoinActivity sysJoinActivity)
    {
        return sysJoinActivityMapper.selectSysJoinActivityList(sysJoinActivity);
    }

    /**
     * 新增参加活动
     * 
     * @param sysJoinActivity 参加活动
     * @return 结果
     */
    @Override
    public int insertSysJoinActivity(SysJoinActivity sysJoinActivity)
    {
        System.out.println(sysJoinActivity.getActivityId());
        sysJoinActivityMapper.activityAcountAdd(sysJoinActivity);
        return sysJoinActivityMapper.insertSysJoinActivity(sysJoinActivity);
    }

    /**
     * 修改参加活动
     * 
     * @param sysJoinActivity 参加活动
     * @return 结果
     */
    @Override
    public int updateSysJoinActivity(SysJoinActivity sysJoinActivity)
    {
        return sysJoinActivityMapper.updateSysJoinActivity(sysJoinActivity);
    }

    /**
     * 批量删除参加活动
     * 
     * @param ids 需要删除的参加活动主键
     * @return 结果
     */
    @Override
    public int deleteSysJoinActivityByIds(Long[] ids)
    {
        return sysJoinActivityMapper.deleteSysJoinActivityByIds(ids);
    }

    /**
     * 删除参加活动信息
     * 
     * @param id 参加活动主键
     * @return 结果
     */
    @Override
    public int deleteSysJoinActivityById(Long id,Long activity_id)
    {
        sysJoinActivityMapper.activityAcountReduce(activity_id);
        return sysJoinActivityMapper.deleteSysJoinActivityById(id,activity_id);
    }

    @Override
    public List<SysActivities> selected(SysActivities sysActivities) {
        System.out.println("impl"+"\t"+"selected");
//        System.out.println("id="+id);
//        System.out.println(sysJoinActivityMapper.selected(SysActivities sysActivities));
        return sysJoinActivityMapper.selected(sysActivities);
    }

    @Override
    public List<SysActivities> noSelect(SysActivities sysActivities) {
        System.out.println("impl"+"\t"+"noSelect");
//        System.out.println("id="+id);
//        System.out.println(sysJoinActivityMapper.noSelect(SysActivities sysActivities));
        return sysJoinActivityMapper.noSelect(sysActivities);
    }
}
