package com.ruoyi.recHisList.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 内推历史对象 rechistory
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
public class Rechistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 内推ID */
    private Long recLogId;

    /** 内推人姓名 */
    @Excel(name = "内推人姓名")
    private String recName;

    /** 内推人情况 */
    @Excel(name = "内推人情况")
    private String candidateCondition;

    /** 内推结果 */
    @Excel(name = "内推结果")
    private String recResult;

    /** 状态（0正常 1失败） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=失败")
    private String recCondition;

    /** 创建时间 */
    @Excel(name = "创建时间")
    private String recTime;

    /** 发布人姓名 */
    @Excel(name = "发布人姓名")
    private String pubName;

    public void setRecLogId(Long recLogId) 
    {
        this.recLogId = recLogId;
    }

    public Long getRecLogId() 
    {
        return recLogId;
    }
    public void setRecName(String recName) 
    {
        this.recName = recName;
    }

    public String getRecName() 
    {
        return recName;
    }
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
    public void setPubName(String pubName) 
    {
        this.pubName = pubName;
    }

    public String getPubName() 
    {
        return pubName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recLogId", getRecLogId())
            .append("recName", getRecName())
            .append("candidateCondition", getCandidateCondition())
            .append("recResult", getRecResult())
            .append("recCondition", getRecCondition())
            .append("recTime", getRecTime())
            .append("pubName", getPubName())
            .toString();
    }
}
