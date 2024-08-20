package com.ruoyi.activity.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.activity.domain.SysActivities;
import com.ruoyi.activity.service.ISysActivitiesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新建活动Controller
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@RestController
@RequestMapping("/activity/activities")
public class SysActivitiesController extends BaseController
{
    @Autowired
    private ISysActivitiesService sysActivitiesService;

    /**
     * 查询新建活动列表
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysActivities sysActivities)
    {
        System.out.println("getList of SysActivities");
        System.out.println(sysActivities);
        startPage();
        List<SysActivities> list = sysActivitiesService.selectSysActivitiesList(sysActivities);
        return getDataTable(list);
    }

    /**
     * 导出新建活动列表
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:export')")
    @Log(title = "新建活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysActivities sysActivities)
    {
        List<SysActivities> list = sysActivitiesService.selectSysActivitiesList(sysActivities);
        ExcelUtil<SysActivities> util = new ExcelUtil<SysActivities>(SysActivities.class);
        util.exportExcel(response, list, "新建活动数据");
    }

    /**
     * 获取新建活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") String id)
    {
        return success(sysActivitiesService.selectSysActivitiesById(id));
    }

    /**
     * 新增新建活动
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:add')")
    @Log(title = "新建活动", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysActivities sysActivities)
    {
        return toAjax(sysActivitiesService.insertSysActivities(sysActivities));
    }

    /**
     * 修改新建活动
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:edit')")
    @Log(title = "新建活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysActivities sysActivities)
    {
        return toAjax(sysActivitiesService.updateSysActivities(sysActivities));
    }

    /**
     * 删除新建活动
     */
    @PreAuthorize("@ss.hasPermi('activity:activities:remove')")
    @Log(title = "新建活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids)
    {
        return toAjax(sysActivitiesService.deleteSysActivitiesByIds(ids));
    }
}
