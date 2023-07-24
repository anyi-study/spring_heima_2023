package com.softmen.service.impl;

import com.softmen.mapper.DeptMapper;
import com.softmen.mapper.EmpMapper;
import com.softmen.pojo.Dept;
import com.softmen.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private EmpMapper empMapper;

    @Override
    public List<Dept> list() {
        return deptMapper.list();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)//spring事务管理 (事务回滚)
    public void delete(Integer id) {
        deptMapper.deleteById(id);//根据ID删除部门数据
        empMapper.deleteByDeptId(id);//根据删除的部门ID删除对应员工数据
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.insert(dept);
    }

    @Override
    public Dept getDeptById(Integer id) {

        return deptMapper.getDeptById(id);
    }

    @Override
    public void updateDept(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.updateDept(dept);
    }
}
