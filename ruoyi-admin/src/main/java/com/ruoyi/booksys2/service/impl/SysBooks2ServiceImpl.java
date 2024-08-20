package com.ruoyi.booksys2.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.booksys2.mapper.SysBooks2Mapper;
import com.ruoyi.booksys2.domain.SysBooks2;
import com.ruoyi.booksys2.service.ISysBooks2Service;

/**
 * 信息查询2Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-25
 */
@Service
public class SysBooks2ServiceImpl implements ISysBooks2Service 
{
    @Autowired
    private SysBooks2Mapper sysBooks2Mapper;

    /**
     * 查询信息查询2
     * 
     * @param id 信息查询2主键
     * @return 信息查询2
     */
    @Override
    public SysBooks2 selectSysBooks2ById(Long id)
    {
        return sysBooks2Mapper.selectSysBooks2ById(id);
    }

    /**
     * 查询信息查询2列表
     * 
     * @param sysBooks2 信息查询2
     * @return 信息查询2
     */
    @Override
    public List<SysBooks2> selectSysBooks2List(SysBooks2 sysBooks2)
    {
        return sysBooks2Mapper.selectSysBooks2List(sysBooks2);
    }

    /**
     * 新增信息查询2
     * 
     * @param sysBooks2 信息查询2
     * @return 结果
     */
    @Override
    public int insertSysBooks2(SysBooks2 sysBooks2)
    {
        return sysBooks2Mapper.insertSysBooks2(sysBooks2);
    }

    /**
     * 修改信息查询2
     * 
     * @param sysBooks2 信息查询2
     * @return 结果
     */
    @Override
    public int updateSysBooks2(SysBooks2 sysBooks2)
    {
        return sysBooks2Mapper.updateSysBooks2(sysBooks2);
    }

    /**
     * 批量删除信息查询2
     * 
     * @param ids 需要删除的信息查询2主键
     * @return 结果
     */
    @Override
    public int deleteSysBooks2ByIds(Long[] ids)
    {
        return sysBooks2Mapper.deleteSysBooks2ByIds(ids);
    }

    /**
     * 删除信息查询2信息
     * 
     * @param id 信息查询2主键
     * @return 结果
     */
    @Override
    public int deleteSysBooks2ById(Long id)
    {
        return sysBooks2Mapper.deleteSysBooks2ById(id);
    }
}
