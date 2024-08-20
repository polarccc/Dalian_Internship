package com.ruoyi.article.controller.system;

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
import com.ruoyi.article.domain.SysArticles;
import com.ruoyi.article.service.ISysArticlesService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博文Controller
 * 
 * @author ruoyi
 * @date 2024-07-29
 */
@RestController
@RequestMapping("/system/articles")
public class SysArticlesController extends BaseController
{
    @Autowired
    private ISysArticlesService sysArticlesService;

    /**
     * 查询博文列表
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysArticles sysArticles)
    {
        startPage();
        List<SysArticles> list = sysArticlesService.selectSysArticlesList(sysArticles);
        return getDataTable(list);
    }

    /**
     * 导出博文列表
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:export')")
    @Log(title = "博文", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysArticles sysArticles)
    {
        List<SysArticles> list = sysArticlesService.selectSysArticlesList(sysArticles);
        ExcelUtil<SysArticles> util = new ExcelUtil<SysArticles>(SysArticles.class);
        util.exportExcel(response, list, "博文数据");
    }

    /**
     * 获取博文详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        System.out.println("selectSysArticlesById\nid:"+id+"\n\n");
        return success(sysArticlesService.selectSysArticlesById(id));
    }

    /**
     * 新增博文
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:add')")
    @Log(title = "博文", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysArticles sysArticles)
    {
        return toAjax(sysArticlesService.insertSysArticles(sysArticles));
    }

    /**
     * 修改博文
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:edit')")
    @Log(title = "博文", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysArticles sysArticles)
    {
        return toAjax(sysArticlesService.updateSysArticles(sysArticles));
    }

    /**
     * 删除博文
     */
//    @PreAuthorize("@ss.hasPermi('system:articles:remove')")
    @Log(title = "博文", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysArticlesService.deleteSysArticlesByIds(ids));
    }
}
