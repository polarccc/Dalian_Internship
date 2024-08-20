package com.ruoyi.recommendationItem.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内推发布对象 inner_recommendation_item
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
public class InnerRecommendationItem extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 内推人姓名 */
    @Excel(name = "内推人姓名")
    private String recName;

    /** 发布人姓名 */
    @Excel(name = "发布人姓名")
    private String pubName;

    /** 内推人情况简介 */
    @Excel(name = "内推人情况简介")
    private String recCondition;

    /** 内推人简历pdf文件 */
    @Excel(name = "内推人简历pdf文件")
    private String recResume;

    /** 内推时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "内推时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recTime;

    /** 内推意向 */
    @Excel(name = "内推意向")
    private String recExpect;

    public void setRecName(String recName) 
    {
        this.recName = recName;
    }

    public String getRecName() 
    {
        return recName;
    }
    public void setPubName(String pubName) 
    {
        this.pubName = pubName;
    }

    public String getPubName() 
    {
        return pubName;
    }
    public void setRecCondition(String recCondition) 
    {
        this.recCondition = recCondition;
    }

    public String getRecCondition() 
    {
        return recCondition;
    }
    public void setRecResume(String recResume) 
    {
        this.recResume = recResume;
    }

    public String getRecResume() 
    {
        return recResume;
    }
    public void setRecTime(Date recTime) 
    {
        this.recTime = recTime;
    }

    public Date getRecTime() 
    {
        return recTime;
    }
    public void setRecExpect(String recExpect) 
    {
        this.recExpect = recExpect;
    }

    public String getRecExpect() 
    {
        return recExpect;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recName", getRecName())
            .append("pubName", getPubName())
            .append("recCondition", getRecCondition())
            .append("recResume", getRecResume())
            .append("recTime", getRecTime())
            .append("recExpect", getRecExpect())
            .toString();
    }
}
