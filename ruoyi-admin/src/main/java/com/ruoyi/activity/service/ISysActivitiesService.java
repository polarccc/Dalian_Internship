package com.ruoyi.activity.service;

import java.util.List;
import com.ruoyi.activity.domain.SysActivities;

/**
 * 新建活动Service接口
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
public interface ISysActivitiesService 
{
    /**
     * 查询新建活动
     * 
     * @param id 新建活动主键
     * @return 新建活动
     */
    public SysActivities selectSysActivitiesById(String id);

    /**
     * 查询新建活动列表
     * 
     * @param sysActivities 新建活动
     * @return 新建活动集合
     */
    public List<SysActivities> selectSysActivitiesList(SysActivities sysActivities);

    /**
     * 新增新建活动
     * 
     * @param sysActivities 新建活动
     * @return 结果
     */
    public int insertSysActivities(SysActivities sysActivities);

    /**
     * 修改新建活动
     * 
     * @param sysActivities 新建活动
     * @return 结果
     */
    public int updateSysActivities(SysActivities sysActivities);

    /**
     * 批量删除新建活动
     * 
     * @param ids 需要删除的新建活动主键集合
     * @return 结果
     */
    public int deleteSysActivitiesByIds(String[] ids);

    /**
     * 删除新建活动信息
     * 
     * @param id 新建活动主键
     * @return 结果
     */
    public int deleteSysActivitiesById(String id);
}
