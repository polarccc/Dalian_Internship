package com.ruoyi.recommendationItem.mapper;

import java.util.List;
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;
import org.apache.ibatis.annotations.Param;

/**
 * 内推发布Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
public interface InnerRecommendationItemMapper 
{
    /**
     * 查询内推发布
     * 
     * @param recName 内推发布主键
     * @return 内推发布
     */
    public InnerRecommendationItem selectInnerRecommendationItemByRecName(  @Param("recName") String recName,
                                                                            @Param("pubName") String pubName);

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
     * 需要同时更新内推一览中的表项
     * @param innerRecommendationItem 内推发布
     * @return 更新发布一览表
     */
    public int updateRecommendationSys(InnerRecommendationItem innerRecommendationItem);

    /**
     * 对内推一览的插入工作
     * @param innerRecommendationItem 内推发布
     * @return 插入到新的一览表中
     */
    public int insertRecommendationSys(InnerRecommendationItem innerRecommendationItem);
    /**
     * 删除内推发布
     * 
     * @param recName 内推发布主键
     * @return 结果
     */
    public int deleteInnerRecommendationItemByRecName(String recName);

    /**
     * 删除recommendationList中的信息
     * @param innerRecommendationItem 内推表信息
     * @return 删除recommendationList中的内容
     */
    public int deleteInRecommendationList(InnerRecommendationItem innerRecommendationItem);
    /**
     * 批量删除内推发布
     * 
     * @param recNames 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteInnerRecommendationItemByRecNames(String[] recNames);
}
