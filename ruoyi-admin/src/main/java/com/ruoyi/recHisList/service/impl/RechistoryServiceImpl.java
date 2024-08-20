package com.ruoyi.recHisList.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recHisList.mapper.RechistoryMapper;
import com.ruoyi.recHisList.domain.Rechistory;
import com.ruoyi.recHisList.service.IRechistoryService;

/**
 * 内推历史Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@Service
public class RechistoryServiceImpl implements IRechistoryService 
{
    @Autowired
    private RechistoryMapper rechistoryMapper;

    /**
     * 查询内推历史
     * 
     * @param recLogId 内推历史主键
     * @return 内推历史
     */
    @Override
    public Rechistory selectRechistoryByRecLogId(Long recLogId)
    {
        return rechistoryMapper.selectRechistoryByRecLogId(recLogId);
    }

    /**
     * 查询内推历史列表
     * 
     * @param rechistory 内推历史
     * @return 内推历史
     */
    @Override
    public List<Rechistory> selectRechistoryList(Rechistory rechistory)
    {
        return rechistoryMapper.selectRechistoryList(rechistory);
    }

    /**
     * 新增内推历史
     * 
     * @param rechistory 内推历史
     * @return 结果
     */
    @Override
    public int insertRechistory(Rechistory rechistory)
    {
        return rechistoryMapper.insertRechistory(rechistory);
    }

    /**
     * 修改内推历史
     * 
     * @param rechistory 内推历史
     * @return 结果
     */
    @Override
    public int updateRechistory(Rechistory rechistory)
    {
        return rechistoryMapper.updateRechistory(rechistory);
    }

    /**
     * 批量删除内推历史
     * 
     * @param recLogIds 需要删除的内推历史主键
     * @return 结果
     */
    @Override
    public int deleteRechistoryByRecLogIds(Long[] recLogIds)
    {
        return rechistoryMapper.deleteRechistoryByRecLogIds(recLogIds);
    }

    /**
     * 删除内推历史信息
     * 
     * @param recLogId 内推历史主键
     * @return 结果
     */
    @Override
    public int deleteRechistoryByRecLogId(Long recLogId)
    {
//        rechistoryMapper.deleteInnerRecommendationItem(recLogId);
        return rechistoryMapper.deleteRechistoryByRecLogId(recLogId);
    }
}
