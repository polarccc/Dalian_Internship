package com.ruoyi.article.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 博文对象 sys_articles
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
public class SysArticles extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 文章id */
    @Excel(name = "文章id")
    private Long id;

    /** 作者id */
    @Excel(name = "作者id")
    private Long userId;

    /** 文章标题 */
    @Excel(name = "文章标题")
    private String articleTitle;

    /** 文章内容 */
    @Excel(name = "文章内容")
    private String articleContent;

    /** 是否通过审核（0：未审核，1：审核通过，2：审核未通过） */
    @Excel(name = "是否通过审核", readConverterExp = "0=：未审核，1：审核通过，2：审核未通过")
    private Long auditStatus;

    /** 是否删除（0：未删除，1：已删除） */
    @Excel(name = "是否删除", readConverterExp = "0=：未删除，1：已删除")
    private Long isDeleted;

    /** 作者名称 */
    @Excel(name = "作者名称")
    private String autherName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setArticleTitle(String articleTitle) 
    {
        this.articleTitle = articleTitle;
    }

    public String getArticleTitle() 
    {
        return articleTitle;
    }
    public void setArticleContent(String articleContent) 
    {
        this.articleContent = articleContent;
    }

    public String getArticleContent() 
    {
        return articleContent;
    }
    public void setAuditStatus(Long auditStatus) 
    {
        this.auditStatus = auditStatus;
    }

    public Long getAuditStatus() 
    {
        return auditStatus;
    }
    public void setIsDeleted(Long isDeleted) 
    {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() 
    {
        return isDeleted;
    }
    public void setAutherName(String autherName) 
    {
        this.autherName = autherName;
    }

    public String getAutherName() 
    {
        return autherName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("articleTitle", getArticleTitle())
            .append("articleContent", getArticleContent())
            .append("auditStatus", getAuditStatus())
            .append("isDeleted", getIsDeleted())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("autherName", getAutherName())
            .toString();
    }
}
