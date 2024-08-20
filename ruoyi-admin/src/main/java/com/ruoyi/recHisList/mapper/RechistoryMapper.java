package com.ruoyi.recHisList.mapper;

import java.util.List;
import com.ruoyi.recHisList.domain.Rechistory;

/**
 * 内推历史Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public interface RechistoryMapper 
{
    /**
     * 查询内推历史
     * 
     * @param recLogId 内推历史主键
     * @return 内推历史
     */
    public Rechistory selectRechistoryByRecLogId(Long recLogId);

    /**
     * 查询内推历史列表
     * 
     * @param rechistory 内推历史
     * @return 内推历史集合
     */
    public List<Rechistory> selectRechistoryList(Rechistory rechistory);

    /**
     * 新增内推历史
     * 
     * @param rechistory 内推历史
     * @return 结果
     */
    public int insertRechistory(Rechistory rechistory);

    /**
     * 修改内推历史
     * 
     * @param rechistory 内推历史
     * @return 结果
     */
    public int updateRechistory(Rechistory rechistory);

    /**
     * 删除内推历史
     * 
     * @param recLogId 内推历史主键
     * @return 结果
     */
    public int deleteRechistoryByRecLogId(Long recLogId);

    /**
     * 删除内推中的一览部分
     */
    public int deleteInnerRecommendationItem(Long recLogId);
    /**
     * 批量删除内推历史
     * 
     * @param recLogIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteRechistoryByRecLogIds(Long[] recLogIds);
}
