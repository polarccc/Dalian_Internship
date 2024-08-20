package com.ruoyi.booksys2.mapper;

import java.util.List;
import com.ruoyi.booksys2.domain.SysBooks2;

/**
 * 信息查询2Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
public interface SysBooks2Mapper 
{
    /**
     * 查询信息查询2
     * 
     * @param id 信息查询2主键
     * @return 信息查询2
     */
    public SysBooks2 selectSysBooks2ById(Long id);

    /**
     * 查询信息查询2列表
     * 
     * @param sysBooks2 信息查询2
     * @return 信息查询2集合
     */
    public List<SysBooks2> selectSysBooks2List(SysBooks2 sysBooks2);

    /**
     * 新增信息查询2
     * 
     * @param sysBooks2 信息查询2
     * @return 结果
     */
    public int insertSysBooks2(SysBooks2 sysBooks2);

    /**
     * 修改信息查询2
     * 
     * @param sysBooks2 信息查询2
     * @return 结果
     */
    public int updateSysBooks2(SysBooks2 sysBooks2);

    /**
     * 删除信息查询2
     * 
     * @param id 信息查询2主键
     * @return 结果
     */
    public int deleteSysBooks2ById(Long id);

    /**
     * 批量删除信息查询2
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBooks2ByIds(Long[] ids);
}
