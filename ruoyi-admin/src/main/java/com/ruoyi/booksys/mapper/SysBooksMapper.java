package com.ruoyi.booksys.mapper;

import java.util.List;
import com.ruoyi.booksys.domain.SysBooks;

/**
 * 信息查询Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
public interface SysBooksMapper 
{
    /**
     * 查询信息查询
     * 
     * @param id 信息查询主键
     * @return 信息查询
     */
    public SysBooks selectSysBooksById(Long id);

    /**
     * 查询信息查询列表
     * 
     * @param sysBooks 信息查询
     * @return 信息查询集合
     */
    public List<SysBooks> selectSysBooksList(SysBooks sysBooks);

    /**
     * 新增信息查询
     * 
     * @param sysBooks 信息查询
     * @return 结果
     */
    public int insertSysBooks(SysBooks sysBooks);

    /**
     * 修改信息查询
     * 
     * @param sysBooks 信息查询
     * @return 结果
     */
    public int updateSysBooks(SysBooks sysBooks);

    /**
     * 删除信息查询
     * 
     * @param id 信息查询主键
     * @return 结果
     */
    public int deleteSysBooksById(Long id);

    /**
     * 批量删除信息查询
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysBooksByIds(Long[] ids);
}
