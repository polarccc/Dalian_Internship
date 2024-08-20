package com.ruoyi.RecommendationSys.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.recommendationItem.domain.InnerRecommendationItem;
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
import com.ruoyi.RecommendationSys.domain.RecommendationList;
import com.ruoyi.RecommendationSys.service.IRecommendationListService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 内推历史记录Controller
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@RestController
@RequestMapping("/RecommendationSys/InnerRecommendation")
public class RecommendationListController extends BaseController
{
    @Autowired
    private IRecommendationListService recommendationListService;

    /**
     * 查询内推历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:list')")
    @GetMapping("/list")
    public TableDataInfo list(RecommendationList recommendationList)
    {
        startPage();
        List<RecommendationList> list = recommendationListService.selectRecommendationListList(recommendationList);
        return getDataTable(list);
    }

    /**
     * 导出内推历史记录列表
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:export')")
    @Log(title = "内推历史记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, RecommendationList recommendationList)
    {
        List<RecommendationList> list = recommendationListService.selectRecommendationListList(recommendationList);
        ExcelUtil<RecommendationList> util = new ExcelUtil<RecommendationList>(RecommendationList.class);
        util.exportExcel(response, list, "内推历史记录数据");
    }

    /**
     * 获取内推历史记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:query')")
    @GetMapping(value = "/{recLogId}")
    public AjaxResult getInfo(@PathVariable("recLogId") Long recLogId)
    {
        return success(recommendationListService.selectRecommendationListByRecLogId(recLogId));
    }

    /**
     * 新增内推历史记录
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:add')")
    @Log(title = "内推历史记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody RecommendationList recommendationList)
    {
        return toAjax(recommendationListService.insertRecommendationList(recommendationList));
    }

    /**
     * 修改内推历史记录
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:edit')")
    @Log(title = "内推历史记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody RecommendationList recommendationList)
    {
        return toAjax(recommendationListService.updateRecommendationList(recommendationList));
    }

    /**
     * 删除内推历史记录
     */
    @PreAuthorize("@ss.hasPermi('RecommendationSys:InnerRecommendation:remove')")
    @Log(title = "内推历史记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recLogIds}")
    public AjaxResult remove(@PathVariable Long[] recLogIds)
    {
        return toAjax(recommendationListService.deleteRecommendationListByRecLogIds(recLogIds));
    }
}
