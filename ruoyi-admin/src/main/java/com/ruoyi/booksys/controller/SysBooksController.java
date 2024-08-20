package com.ruoyi.booksys.controller;

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
import com.ruoyi.booksys.domain.SysBooks;
import com.ruoyi.booksys.service.ISysBooksService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 信息查询Controller
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@RestController
@RequestMapping("/booksys/books")
public class SysBooksController extends BaseController
{
    @Autowired
    private ISysBooksService sysBooksService;

    /**
     * 查询信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysBooks sysBooks)
    {
        startPage();
        List<SysBooks> list = sysBooksService.selectSysBooksList(sysBooks);
        return getDataTable(list);
    }

    /**
     * 导出信息查询列表
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:export')")
    @Log(title = "信息查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysBooks sysBooks)
    {
        List<SysBooks> list = sysBooksService.selectSysBooksList(sysBooks);
        ExcelUtil<SysBooks> util = new ExcelUtil<SysBooks>(SysBooks.class);
        util.exportExcel(response, list, "信息查询数据");
    }

    /**
     * 获取信息查询详细信息
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysBooksService.selectSysBooksById(id));
    }

    /**
     * 新增信息查询
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:add')")
    @Log(title = "信息查询", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysBooks sysBooks)
    {
        return toAjax(sysBooksService.insertSysBooks(sysBooks));
    }

    /**
     * 修改信息查询
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:edit')")
    @Log(title = "信息查询", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysBooks sysBooks)
    {
        return toAjax(sysBooksService.updateSysBooks(sysBooks));
    }

    /**
     * 删除信息查询
     */
    @PreAuthorize("@ss.hasPermi('booksys:books:remove')")
    @Log(title = "信息查询", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysBooksService.deleteSysBooksByIds(ids));
    }
}
