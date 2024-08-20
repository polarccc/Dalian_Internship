package com.ruoyi.article.mapper;

import java.util.List;
import com.ruoyi.article.domain.SysArticles;
import org.apache.ibatis.annotations.Mapper;

/**
 * 博文Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
@Mapper
public interface SysArticlesMapper 
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
     * 删除博文
     * 
     * @param id 博文主键
     * @return 结果
     */
    public int deleteSysArticlesById(Long id);

    /**
     * 批量删除博文
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysArticlesByIds(Long[] ids);
}
