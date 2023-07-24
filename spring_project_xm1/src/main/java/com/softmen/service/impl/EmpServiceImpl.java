package com.softmen.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.softmen.mapper.EmpMapper;
import com.softmen.pojo.Emp;
import com.softmen.pojo.PageBean;
import com.softmen.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;

    @Override
//    方法一
//    public PageBean page(Integer page, Integer pageSize) {
////        获取总记录数
//        Long count = empMapper.count();
////        获取数据列表
//        Integer start = (page-1)*pageSize;
//        List<Emp> empList = empMapper.page(start, pageSize);
////        封装bean
//        PageBean pageBean = new PageBean(count,empList);
//        return pageBean;
//    }
//    ----------
//    分页方法二
    public PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end) {
//        设置参数
        PageHelper.startPage(page, pageSize);
//        执行查询
        List<Emp> empList = empMapper.list(name, gender, begin, end);
        Page<Emp> p = (Page<Emp>) empList;
//        封装bean
        PageBean pageBean = new PageBean(p.getTotal(), p.getResult());
        return pageBean;
    }

    @Override
    public void detele(List<Integer> ids) {
        empMapper.detele(ids);
    }

    @Override
    public void save(Emp emp) {
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.insert(emp);
    }

    @Override
    public Emp getById(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.update(emp);
    }

    @Override
    public Emp login(Emp emp) {

        return empMapper.getByUsernameAndPassword(emp);
    }
}
