package com.ruoyi.RecommendationSys.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内推历史记录对象 recommendation_list
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
public class RecommendationList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 内推ID */
    private Long recLogId;

    /** 内推人姓名 */
    @Excel(name = "内推人姓名")
    private String recName;

    @Excel(name = "内推发布人")
    private String pubName;

    /** 内推人情况 */
    @Excel(name = "内推人情况")
    private String candidateCondition;

    /** 发布人姓名 */

    /** 内推结果 */
    @Excel(name = "内推结果")
    private String recResult;

    /** 状态（0正常 1失败） */
    @Excel(name = "内推审核状态")
    private String recCondition;

    /** 创建时间 */
    @Excel(name = "审核意见")
    private String recTime;

    private String recResume;

    public void setRecLogId(Long recLogId) 
    {
        this.recLogId = recLogId;
    }

    public String getRecResume(){return recResume;}
    public Long getRecLogId() 
    {
        return recLogId;
    }
    public void setRecName(String recName) 
    {
        this.recName = recName;
    }
    public void setPubName(String pubName){this.pubName = pubName;}

    public String getRecName() 
    {
        return recName;
    }
    public String getPubName(){return pubName;}
    public void setCandidateCondition(String candidateCondition) 
    {
        this.candidateCondition = candidateCondition;
    }

    public String getCandidateCondition() 
    {
        return candidateCondition;
    }
    public void setRecResult(String recResult) 
    {
        this.recResult = recResult;
    }

    public String getRecResult() 
    {
        return recResult;
    }
    public void setRecCondition(String recCondition) 
    {
        this.recCondition = recCondition;
    }

    public String getRecCondition() 
    {
        return recCondition;
    }
    public void setRecTime(String recTime) 
    {
        this.recTime = recTime;
    }

    public String getRecTime() 
    {
        return recTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recLogId", getRecLogId())
            .append("recName", getRecName())
                .append("pubName", getPubName())
            .append("candidateCondition", getCandidateCondition())
            .append("recResult", getRecResult())
            .append("recCondition", getRecCondition())
            .append("recTime", getRecTime())
            .toString();
    }
}
