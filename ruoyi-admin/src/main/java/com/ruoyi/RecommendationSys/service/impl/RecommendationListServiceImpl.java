package com.ruoyi.RecommendationSys.service.impl;

import java.util.List;

import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.RecommendationSys.mapper.RecommendationListMapper;
import com.ruoyi.RecommendationSys.domain.RecommendationList;
import com.ruoyi.RecommendationSys.service.IRecommendationListService;

/**
 * 内推历史记录Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@Service
public class RecommendationListServiceImpl implements IRecommendationListService 
{
    @Autowired
    private RecommendationListMapper recommendationListMapper;

    /**
     * 查询内推历史记录
     * 
     * @param recLogId 内推历史记录主键
     * @return 内推历史记录
     */
    @Override
    public RecommendationList selectRecommendationListByRecLogId(Long recLogId)
    {
        return recommendationListMapper.selectRecommendationListByRecLogId(recLogId);
    }

    /**
     * 查询内推历史记录列表
     * 
     * @param recommendationList 内推历史记录
     * @return 内推历史记录
     */
    @Override
    public List<RecommendationList> selectRecommendationListList(RecommendationList recommendationList)
    {
        return recommendationListMapper.selectRecommendationListList(recommendationList);
    }

    /**
     * 新增内推历史记录
     * 
     * @param recommendationList 内推历史记录
     * @return 结果
     */
    @Override
    public int insertRecommendationList(RecommendationList recommendationList)
    {
        return recommendationListMapper.insertRecommendationList(recommendationList);
    }

    /**
     * 修改内推历史记录
     * 
     * @param recommendationList 内推历史记录
     * @return 结果
     */
    @Override
    public int updateRecommendationList(RecommendationList recommendationList)
    {
        return recommendationListMapper.updateRecommendationList(recommendationList);
    }

    /**
     * 批量删除内推历史记录
     * 
     * @param recLogIds 需要删除的内推历史记录主键
     * @return 结果
     */
    @Override
    public int deleteRecommendationListByRecLogIds(Long[] recLogIds)
    {
        return recommendationListMapper.deleteRecommendationListByRecLogIds(recLogIds);
    }

    /**
     * 删除内推历史记录信息
     * 
     * @param recLogId 内推历史记录主键
     * @return 结果
     */
    @Override
    public int deleteRecommendationListByRecLogId(Long recLogId)
    {
        return recommendationListMapper.deleteRecommendationListByRecLogId(recLogId);
    }

}
