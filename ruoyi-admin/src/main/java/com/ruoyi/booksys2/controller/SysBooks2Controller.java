package com.ruoyi.booksys2.controller;

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
import com.ruoyi.booksys2.domain.SysBooks2;
import com.ruoyi.booksys2.service.ISysBooks2Service;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信息查询2Controller
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@RestController
@RequestMapping("/booksys2/books2")
public class SysBooks2Controller extends BaseController
{
    @Autowired
    private ISysBooks2Service sysBooks2Service;

    /**
     * 查询信息查询2列表
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBooks2 sysBooks2)
    {
        startPage();
        List<SysBooks2> list = sysBooks2Service.selectSysBooks2List(sysBooks2);
        return getDataTable(list);
    }

    /**
     * 导出信息查询2列表
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:export')")
    @Log(title = "信息查询2", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBooks2 sysBooks2)
    {
        List<SysBooks2> list = sysBooks2Service.selectSysBooks2List(sysBooks2);
        ExcelUtil<SysBooks2> util = new ExcelUtil<SysBooks2>(SysBooks2.class);
        util.exportExcel(response, list, "信息查询2数据");
    }

    /**
     * 获取信息查询2详细信息
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysBooks2Service.selectSysBooks2ById(id));
    }

    /**
     * 新增信息查询2
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:add')")
    @Log(title = "信息查询2", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBooks2 sysBooks2)
    {
        return toAjax(sysBooks2Service.insertSysBooks2(sysBooks2));
    }

    /**
     * 修改信息查询2
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:edit')")
    @Log(title = "信息查询2", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBooks2 sysBooks2)
    {
        return toAjax(sysBooks2Service.updateSysBooks2(sysBooks2));
    }

    /**
     * 删除信息查询2
     */
    @PreAuthorize("@ss.hasPermi('booksys2:books2:remove')")
    @Log(title = "信息查询2", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBooks2Service.deleteSysBooks2ByIds(ids));
    }
}
