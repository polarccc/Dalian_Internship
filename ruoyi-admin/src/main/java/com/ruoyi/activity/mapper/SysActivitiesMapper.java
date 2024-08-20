package com.ruoyi.activity.mapper;

import java.util.List;
import com.ruoyi.activity.domain.SysActivities;
import org.apache.ibatis.annotations.Mapper;

/**
 * 新建活动Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@Mapper
public interface SysActivitiesMapper 
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
     * 删除新建活动
     * 
     * @param id 新建活动主键
     * @return 结果
     */
    public int deleteSysActivitiesById(String id);

    /**
     * 批量删除新建活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysActivitiesByIds(String[] ids);
}
