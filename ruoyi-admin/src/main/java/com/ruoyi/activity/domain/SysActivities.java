package com.ruoyi.activity.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新建活动对象 sys_activities
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
public class SysActivities extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String name;

    /** 活动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "活动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 活动时长 */
    @Excel(name = "活动时长")
    private Long time;

    /** 活动发起人 */
    @Excel(name = "活动发起人")
    private String organizer;

    /** 活动内容 */
    @Excel(name = "活动内容")
    private String content;

    /** 活动人数 */
    @Excel(name = "活动人数")
    private Long max;

    /** 活动地点 */
    private String address;

    /** 已报名人数 */
    @Excel(name = "已报名人数")
    private Long account;

    /** 活动状态 */
    @Excel(name = "活动状态")
    private String status;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setTime(Long time) 
    {
        this.time = time;
    }

    public Long getTime() 
    {
        return time;
    }
    public void setOrganizer(String organizer) 
    {
        this.organizer = organizer;
    }

    public String getOrganizer() 
    {
        return organizer;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setMax(Long max) 
    {
        this.max = max;
    }

    public Long getMax() 
    {
        return max;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setAccount(Long account) 
    {
        this.account = account;
    }

    public Long getAccount() 
    {
        return account;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("date", getDate())
            .append("time", getTime())
            .append("organizer", getOrganizer())
            .append("content", getContent())
            .append("max", getMax())
            .append("address", getAddress())
            .append("account", getAccount())
            .append("status", getStatus())
            .toString();
    }
}
