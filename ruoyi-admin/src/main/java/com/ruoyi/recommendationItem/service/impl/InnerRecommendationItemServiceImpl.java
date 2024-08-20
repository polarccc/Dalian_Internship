package com.ruoyi.recommendationItem.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.recommendationItem.mapper.InnerRecommendationItemMapper;
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;
import com.ruoyi.recommendationItem.service.IInnerRecommendationItemService;

/**
 * 内推发布Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@Service
public class InnerRecommendationItemServiceImpl implements IInnerRecommendationItemService 
{
    @Autowired
    private InnerRecommendationItemMapper innerRecommendationItemMapper;

    /**
     * 查询内推发布
     * 
     * @param recName 内推发布主键
     * @return 内推发布
     */
    @Override
    public InnerRecommendationItem selectInnerRecommendationItemByRecName(String recName, String pubName)
    {
        return innerRecommendationItemMapper.selectInnerRecommendationItemByRecName(recName,pubName);
    }

    /**
     * 查询内推发布列表
     * 
     * @param innerRecommendationItem 内推发布
     * @return 内推发布
     */
    @Override
    public List<InnerRecommendationItem> selectInnerRecommendationItemList(InnerRecommendationItem innerRecommendationItem)
    {
        return innerRecommendationItemMapper.selectInnerRecommendationItemList(innerRecommendationItem);
    }

    /**
     * 新增内推发布
     * 
     * @param innerRecommendationItem 内推发布
     * @return 结果
     */
    @Override
    public int insertInnerRecommendationItem(InnerRecommendationItem innerRecommendationItem)
    {
//        System.out.println("charu");
        return innerRecommendationItemMapper.insertInnerRecommendationItem(innerRecommendationItem);
    }

    /**
     * 修改内推发布
     * 
     * @param innerRecommendationItem 内推发布
     * @return 结果
     */
    @Override
    public int updateInnerRecommendationItem(InnerRecommendationItem innerRecommendationItem)
    {
        System.out.println("genggai");
        if(innerRecommendationItemMapper.updateRecommendationSys(innerRecommendationItem)==0){
            System.out.println("1");
            innerRecommendationItemMapper.insertRecommendationSys(innerRecommendationItem);
        }
        if(innerRecommendationItemMapper.updateInnerRecommendationItem(innerRecommendationItem)==0){
            System.out.println("2");
            insertInnerRecommendationItem(innerRecommendationItem);
        }
        return innerRecommendationItemMapper.updateInnerRecommendationItem(innerRecommendationItem);
    }

    /**
     * 批量删除内推发布
     * 
     * @param recNames 需要删除的内推发布主键
     * @return 结果
     */
    @Override
    public int deleteInnerRecommendationItemByRecNames(String[] recNames)
    {
        return innerRecommendationItemMapper.deleteInnerRecommendationItemByRecNames(recNames);
    }

    /**
     * 删除内推发布信息
     * 
     * @param recName 内推发布主键
     * @return 结果
     */
    @Override
    public int deleteInnerRecommendationItemByRecName(String recName)
    {
        return innerRecommendationItemMapper.deleteInnerRecommendationItemByRecName(recName);
    }
}
