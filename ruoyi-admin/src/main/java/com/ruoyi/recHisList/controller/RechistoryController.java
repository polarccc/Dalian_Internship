package com.ruoyi.recHisList.controller;

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
import com.ruoyi.recHisList.domain.Rechistory;
import com.ruoyi.recHisList.service.IRechistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内推历史Controller
 * 
 * @author ruoyi
 * @date 2024-07-27
 */
@RestController
@RequestMapping("/recHisList/rechistory")
public class RechistoryController extends BaseController
{
    @Autowired
    private IRechistoryService rechistoryService;

    /**
     * 查询内推历史列表
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Rechistory rechistory)
    {
        startPage();
        List<Rechistory> list = rechistoryService.selectRechistoryList(rechistory);
        return getDataTable(list);
    }

    /**
     * 导出内推历史列表
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:export')")
    @Log(title = "内推历史", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Rechistory rechistory)
    {
        List<Rechistory> list = rechistoryService.selectRechistoryList(rechistory);
        ExcelUtil<Rechistory> util = new ExcelUtil<Rechistory>(Rechistory.class);
        util.exportExcel(response, list, "内推历史数据");
    }

    /**
     * 获取内推历史详细信息
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:query')")
    @GetMapping(value = "/{recLogId}")
    public AjaxResult getInfo(@PathVariable("recLogId") Long recLogId)
    {
        return success(rechistoryService.selectRechistoryByRecLogId(recLogId));
    }

    /**
     * 新增内推历史
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:add')")
    @Log(title = "内推历史", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Rechistory rechistory)
    {
        return toAjax(rechistoryService.insertRechistory(rechistory));
    }

    /**
     * 修改内推历史
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:edit')")
    @Log(title = "内推历史", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Rechistory rechistory)
    {
        return toAjax(rechistoryService.updateRechistory(rechistory));
    }

    /**
     * 删除内推历史
     */
    @PreAuthorize("@ss.hasPermi('recHisList:rechistory:remove')")
    @Log(title = "内推历史", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recLogIds}")
    public AjaxResult remove(@PathVariable Long[] recLogIds)
    {
        return toAjax(rechistoryService.deleteRechistoryByRecLogIds(recLogIds));
    }
}
