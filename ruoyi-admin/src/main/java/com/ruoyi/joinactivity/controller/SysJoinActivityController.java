package com.ruoyi.joinactivity.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.activity.domain.SysActivities;
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
import com.ruoyi.joinactivity.domain.SysJoinActivity;
import com.ruoyi.joinactivity.service.ISysJoinActivityService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 参加活动Controller
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@RestController
@RequestMapping("/joinactivity/activity")
public class SysJoinActivityController extends BaseController
{
    @Autowired
    private ISysJoinActivityService sysJoinActivityService;

    /**
     * 查询参加活动列表
     */
    @PreAuthorize("@ss.hasPermi('joinactivity:activity:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysJoinActivity sysJoinActivity)
    {
        startPage();
        List<SysJoinActivity> list = sysJoinActivityService.selectSysJoinActivityList(sysJoinActivity);
        return getDataTable(list);
    }

    /**
     * 导出参加活动列表
     */
    @PreAuthorize("@ss.hasPermi('joinactivity:activity:export')")
    @Log(title = "参加活动", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysJoinActivity sysJoinActivity)
    {
        List<SysJoinActivity> list = sysJoinActivityService.selectSysJoinActivityList(sysJoinActivity);
        ExcelUtil<SysJoinActivity> util = new ExcelUtil<SysJoinActivity>(SysJoinActivity.class);
        util.exportExcel(response, list, "参加活动数据");
    }

    /**
     * 获取参加活动详细信息
     */
    @PreAuthorize("@ss.hasPermi('joinactivity:activity:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysJoinActivityService.selectSysJoinActivityById(id));
    }

    /**
     * 新增参加活动
     */
//    @PreAuthorize("@ss.hasPermi('joinactivity:activity:add')")
//    @Log(title = "参加活动", businessType = BusinessType.INSERT)
    @PostMapping("/join")
    public AjaxResult add(@RequestBody SysJoinActivity sysJoinActivity)
    {
        System.out.println("JoinActivity");
        return toAjax(sysJoinActivityService.insertSysJoinActivity(sysJoinActivity));
    }

    /**
     * 修改参加活动
     */
    @PreAuthorize("@ss.hasPermi('joinactivity:activity:edit')")
    @Log(title = "参加活动", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJoinActivity sysJoinActivity)
    {
        return toAjax(sysJoinActivityService.updateSysJoinActivity(sysJoinActivity));
    }

    /**
     * 删除参加活动
     */
//    @PreAuthorize("@ss.hasPermi('joinactivity:activity:remove')")
//    @Log(title = "参加活动", businessType = BusinessType.DELETE)
	@DeleteMapping("/{id}/{activity_id}")
    public AjaxResult remove(@PathVariable("id") Long id,@PathVariable("activity_id") Long activity_id)
    {
        System.out.println("id:"+id+"\n"+"activity_id:"+activity_id);
        return toAjax(sysJoinActivityService.deleteSysJoinActivityById(id,activity_id));
    }

    @GetMapping(value = "/selected/{id}")
    public TableDataInfo  getSelected(SysActivities sysActivities,@PathVariable("id") Long id)
    {
        System.out.println("selected");
        System.out.println("id="+id);
        sysActivities.setId(id);
        startPage();
        List<SysActivities> list = sysJoinActivityService.selected(sysActivities);
        return getDataTable(list);
    }

    @GetMapping(value = "/noSelect/{id}")
    public TableDataInfo getNoSelect(SysActivities sysActivities,@PathVariable("id") Long id)
    {

        System.out.println("noSelect");
        System.out.println("id="+id);
        sysActivities.setId(id);
        startPage();
        List<SysActivities> list = sysJoinActivityService.noSelect(sysActivities);
        return getDataTable(list);
    }

}
