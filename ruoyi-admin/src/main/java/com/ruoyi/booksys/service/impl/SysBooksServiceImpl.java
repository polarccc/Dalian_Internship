package com.ruoyi.booksys.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.booksys.mapper.SysBooksMapper;
import com.ruoyi.booksys.domain.SysBooks;
import com.ruoyi.booksys.service.ISysBooksService;

/**
 * 信息查询Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@Service
public class SysBooksServiceImpl implements ISysBooksService 
{
    @Autowired
    private SysBooksMapper sysBooksMapper;

    /**
     * 查询信息查询
     * 
     * @param id 信息查询主键
     * @return 信息查询
     */
    @Override
    public SysBooks selectSysBooksById(Long id)
    {
        return sysBooksMapper.selectSysBooksById(id);
    }

    /**
     * 查询信息查询列表
     * 
     * @param sysBooks 信息查询
     * @return 信息查询
     */
    @Override
    public List<SysBooks> selectSysBooksList(SysBooks sysBooks)
    {
        return sysBooksMapper.selectSysBooksList(sysBooks);
    }

    /**
     * 新增信息查询
     * 
     * @param sysBooks 信息查询
     * @return 结果
     */
    @Override
    public int insertSysBooks(SysBooks sysBooks)
    {
        return sysBooksMapper.insertSysBooks(sysBooks);
    }

    /**
     * 修改信息查询
     * 
     * @param sysBooks 信息查询
     * @return 结果
     */
    @Override
    public int updateSysBooks(SysBooks sysBooks)
    {
        return sysBooksMapper.updateSysBooks(sysBooks);
    }

    /**
     * 批量删除信息查询
     * 
     * @param ids 需要删除的信息查询主键
     * @return 结果
     */
    @Override
    public int deleteSysBooksByIds(Long[] ids)
    {
        return sysBooksMapper.deleteSysBooksByIds(ids);
    }

    /**
     * 删除信息查询信息
     * 
     * @param id 信息查询主键
     * @return 结果
     */
    @Override
    public int deleteSysBooksById(Long id)
    {
        return sysBooksMapper.deleteSysBooksById(id);
    }
}
