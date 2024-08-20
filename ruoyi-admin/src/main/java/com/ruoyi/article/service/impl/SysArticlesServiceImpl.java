package com.ruoyi.article.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.article.mapper.SysArticlesMapper;
import com.ruoyi.article.domain.SysArticles;
import com.ruoyi.article.service.ISysArticlesService;

/**
 * 博文Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
@Service
public class SysArticlesServiceImpl implements ISysArticlesService 
{
    @Autowired
    private SysArticlesMapper sysArticlesMapper;

    /**
     * 查询博文
     * 
     * @param id 博文主键
     * @return 博文
     */
    @Override
    public SysArticles selectSysArticlesById(Long id)
    {
        return sysArticlesMapper.selectSysArticlesById(id);
    }

    /**
     * 查询博文列表
     * 
     * @param sysArticles 博文
     * @return 博文
     */
    @Override
    public List<SysArticles> selectSysArticlesList(SysArticles sysArticles)
    {
        return sysArticlesMapper.selectSysArticlesList(sysArticles);
    }

    /**
     * 新增博文
     * 
     * @param sysArticles 博文
     * @return 结果
     */
    @Override
    public int insertSysArticles(SysArticles sysArticles)
    {
        sysArticles.setCreateTime(DateUtils.getNowDate());
        System.out.println(sysArticles);
        return sysArticlesMapper.insertSysArticles(sysArticles);
    }

    /**
     * 修改博文
     * 
     * @param sysArticles 博文
     * @return 结果
     */
    @Override
    public int updateSysArticles(SysArticles sysArticles)
    {
        sysArticles.setUpdateTime(DateUtils.getNowDate());
        return sysArticlesMapper.updateSysArticles(sysArticles);
    }

    /**
     * 批量删除博文
     * 
     * @param ids 需要删除的博文主键
     * @return 结果
     */
    @Override
    public int deleteSysArticlesByIds(Long[] ids)
    {
        return sysArticlesMapper.deleteSysArticlesByIds(ids);
    }

    /**
     * 删除博文信息
     * 
     * @param id 博文主键
     * @return 结果
     */
    @Override
    public int deleteSysArticlesById(Long id)
    {
        return sysArticlesMapper.deleteSysArticlesById(id);
    }
}
