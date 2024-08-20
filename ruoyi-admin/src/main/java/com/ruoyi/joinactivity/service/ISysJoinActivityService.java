package com.ruoyi.joinactivity.service;

import java.util.List;

import com.ruoyi.activity.domain.SysActivities;
import com.ruoyi.joinactivity.domain.SysJoinActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参加活动Service接口
 * 
 * @author ruoyi
 * @date 2024-07-26
 */

public interface ISysJoinActivityService 
{
    /**
     * 查询参加活动
     * 
     * @param id 参加活动主键
     * @return 参加活动
     */
    public SysJoinActivity selectSysJoinActivityById(Long id);

    /**
     * 查询参加活动列表
     * 
     * @param sysJoinActivity 参加活动
     * @return 参加活动集合
     */
    public List<SysJoinActivity> selectSysJoinActivityList(SysJoinActivity sysJoinActivity);

    /**
     * 新增参加活动
     * 
     * @param sysJoinActivity 参加活动
     * @return 结果
     */
    public int insertSysJoinActivity(SysJoinActivity sysJoinActivity);

    /**
     * 修改参加活动
     * 
     * @param sysJoinActivity 参加活动
     * @return 结果
     */
    public int updateSysJoinActivity(SysJoinActivity sysJoinActivity);

    /**
     * 批量删除参加活动
     * 
     * @param ids 需要删除的参加活动主键集合
     * @return 结果
     */
    public int deleteSysJoinActivityByIds(Long[] ids);

    /**
     * 删除参加活动信息
     * 
     * @param id 参加活动主键
     * @return 结果
     */
    public int deleteSysJoinActivityById(Long id,Long activity_id);

    public List<SysActivities> selected(SysActivities sysActivities);
    public List<SysActivities> noSelect(SysActivities sysActivities);
}
