package com.ruoyi.RecommendationSys.service;

import java.util.List;
import com.ruoyi.RecommendationSys.domain.RecommendationList;
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;

/**
 * 内推历史记录Service接口
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
public interface IRecommendationListService 
{
    /**
     * 查询内推历史记录
     * 
     * @param recLogId 内推历史记录主键
     * @return 内推历史记录
     */
    public RecommendationList selectRecommendationListByRecLogId(Long recLogId);

    /**
     * 查询内推历史记录列表
     * 
     * @param recommendationList 内推历史记录
     * @return 内推历史记录集合
     */
    public List<RecommendationList> selectRecommendationListList(RecommendationList recommendationList);

    /**
     * 新增内推历史记录
     * 
     * @param recommendationList 内推历史记录
     * @return 结果
     */
    public int insertRecommendationList(RecommendationList recommendationList);

    /**
     * 修改内推历史记录
     * 
     * @param recommendationList 内推历史记录
     * @return 结果
     */
    public int updateRecommendationList(RecommendationList recommendationList);

    /**
     * 批量删除内推历史记录
     * 
     * @param recLogIds 需要删除的内推历史记录主键集合
     * @return 结果
     */
    public int deleteRecommendationListByRecLogIds(Long[] recLogIds);

    /**
     * 删除内推历史记录信息
     * 
     * @param recLogId 内推历史记录主键
     * @return 结果
     */
    public int deleteRecommendationListByRecLogId(Long recLogId);

}
