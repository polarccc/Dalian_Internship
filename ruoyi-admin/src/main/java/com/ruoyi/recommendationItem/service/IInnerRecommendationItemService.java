package com.ruoyi.recommendationItem.service;

import java.util.List;
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;

/**
 * 内推发布Service接口
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
public interface IInnerRecommendationItemService 
{
    /**
     * 查询内推发布
     * 
     * @param recName 内推发布主键
     * @return 内推发布
     */
    public InnerRecommendationItem selectInnerRecommendationItemByRecName(String recName,String pubName);

    /**
     * 查询内推发布列表
     * 
     * @param innerRecommendationItem 内推发布
     * @return 内推发布集合
     */
    public List<InnerRecommendationItem> selectInnerRecommendationItemList(InnerRecommendationItem innerRecommendationItem);

    /**
     * 新增内推发布
     * 
     * @param innerRecommendationItem 内推发布
     * @return 结果
     */
    public int insertInnerRecommendationItem(InnerRecommendationItem innerRecommendationItem);

    /**
     * 修改内推发布
     * 
     * @param innerRecommendationItem 内推发布
     * @return 结果
     */
    public int updateInnerRecommendationItem(InnerRecommendationItem innerRecommendationItem);

    /**
     * 批量删除内推发布
     * 
     * @param recNames 需要删除的内推发布主键集合
     * @return 结果
     */
    public int deleteInnerRecommendationItemByRecNames(String[] recNames);

    /**
     * 删除内推发布信息
     * 
     * @param recName 内推发布主键
     * @return 结果
     */
    public int deleteInnerRecommendationItemByRecName(String recName);
}
