package com.ruoyi.recommendationItem.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
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
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;
import com.ruoyi.recommendationItem.service.IInnerRecommendationItemService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内推发布Controller
 * 
 * @author ruoyi
 * @date 2024-07-26
 */
@RestController
@RequestMapping("/recommendationItem/recommendationItem")
public class InnerRecommendationItemController extends BaseController
{
    @Autowired
    private IInnerRecommendationItemService innerRecommendationItemService;

    /**
     * 查询内推发布列表
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:list')")
    @GetMapping("/list")
    public TableDataInfo list(InnerRecommendationItem innerRecommendationItem)
    {
        startPage();
        List<InnerRecommendationItem> list = innerRecommendationItemService.selectInnerRecommendationItemList(innerRecommendationItem);
        return getDataTable(list);
    }

    /**
     * 导出内推发布列表
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:export')")
    @Log(title = "内推发布", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InnerRecommendationItem innerRecommendationItem)
    {
        List<InnerRecommendationItem> list = innerRecommendationItemService.selectInnerRecommendationItemList(innerRecommendationItem);
        ExcelUtil<InnerRecommendationItem> util = new ExcelUtil<InnerRecommendationItem>(InnerRecommendationItem.class);
        util.exportExcel(response, list, "内推发布数据");
    }

    /**
     * 获取内推发布详细信息
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:query')")
    @GetMapping(value = "/{recName}/{pubName}")
    public AjaxResult getInfo(@PathVariable("recName") String recName,@PathVariable("pubName") String pubName)
    {
        return success(innerRecommendationItemService.selectInnerRecommendationItemByRecName(recName,pubName));
    }

    /**
     * 新增内推发布
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:add')")
    @Log(title = "内推发布", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InnerRecommendationItem innerRecommendationItem)
    {
//        System.out.println("111!");
        return toAjax(innerRecommendationItemService.insertInnerRecommendationItem(innerRecommendationItem));
    }

    /**
     * 修改内推发布
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:edit')")
    @Log(title = "内推发布", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InnerRecommendationItem innerRecommendationItem)
    {
        return toAjax(innerRecommendationItemService.updateInnerRecommendationItem(innerRecommendationItem));
    }

    /**
     * 删除内推发布
     */
    @PreAuthorize("@ss.hasPermi('recommendationItem:recommendationItem:remove')")
    @Log(title = "内推发布", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recNames}")
    public AjaxResult remove(@PathVariable String[] recNames)
    {
        return toAjax(innerRecommendationItemService.deleteInnerRecommendationItemByRecNames(recNames));
    }
}
