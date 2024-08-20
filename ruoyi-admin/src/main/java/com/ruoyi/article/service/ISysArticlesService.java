package com.ruoyi.article.service;

import java.util.List;
import com.ruoyi.article.domain.SysArticles;

/**
 * 博文Service接口
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
public interface ISysArticlesService 
{
    /**
     * 查询博文
     * 
     * @param id 博文主键
     * @return 博文
     */
    public SysArticles selectSysArticlesById(Long id);

    /**
     * 查询博文列表
     * 
     * @param sysArticles 博文
     * @return 博文集合
     */
    public List<SysArticles> selectSysArticlesList(SysArticles sysArticles);

    /**
     * 新增博文
     * 
     * @param sysArticles 博文
     * @return 结果
     */
    public int insertSysArticles(SysArticles sysArticles);

    /**
     * 修改博文
     * 
     * @param sysArticles 博文
     * @return 结果
     */
    public int updateSysArticles(SysArticles sysArticles);

    /**
     * 批量删除博文
     * 
     * @param ids 需要删除的博文主键集合
     * @return 结果
     */
    public int deleteSysArticlesByIds(Long[] ids);

    /**
     * 删除博文信息
     * 
     * @param id 博文主键
     * @return 结果
     */
    public int deleteSysArticlesById(Long id);
}
