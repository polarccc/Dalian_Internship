package com.ruoyi.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.activity.mapper.SysActivitiesMapper;
import com.ruoyi.activity.domain.SysActivities;
import com.ruoyi.activity.service.ISysActivitiesService;

/**
 * 新建活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@Service
public class SysActivitiesServiceImpl implements ISysActivitiesService 
{
    @Autowired
    private SysActivitiesMapper sysActivitiesMapper;

    /**
     * 查询新建活动
     * 
     * @param id 新建活动主键
     * @return 新建活动
     */
    @Override
    public SysActivities selectSysActivitiesById(String id)
    {
        return sysActivitiesMapper.selectSysActivitiesById(id);
    }

    /**
     * 查询新建活动列表
     * 
     * @param sysActivities 新建活动
     * @return 新建活动
     */
    @Override
    public List<SysActivities> selectSysActivitiesList(SysActivities sysActivities)
    {
        return sysActivitiesMapper.selectSysActivitiesList(sysActivities);
    }

    /**
     * 新增新建活动
     * 
     * @param sysActivities 新建活动
     * @return 结果
     */
    @Override
    public int insertSysActivities(SysActivities sysActivities)
    {
        return sysActivitiesMapper.insertSysActivities(sysActivities);
    }

    /**
     * 修改新建活动
     * 
     * @param sysActivities 新建活动
     * @return 结果
     */
    @Override
    public int updateSysActivities(SysActivities sysActivities)
    {
        return sysActivitiesMapper.updateSysActivities(sysActivities);
    }

    /**
     * 批量删除新建活动
     * 
     * @param ids 需要删除的新建活动主键
     * @return 结果
     */
    @Override
    public int deleteSysActivitiesByIds(String[] ids)
    {
        return sysActivitiesMapper.deleteSysActivitiesByIds(ids);
    }

    /**
     * 删除新建活动信息
     * 
     * @param id 新建活动主键
     * @return 结果
     */
    @Override
    public int deleteSysActivitiesById(String id)
    {
        return sysActivitiesMapper.deleteSysActivitiesById(id);
    }
}
