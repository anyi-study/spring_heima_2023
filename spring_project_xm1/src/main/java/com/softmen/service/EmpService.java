package com.softmen.service;


import com.softmen.pojo.Emp;
import com.softmen.pojo.PageBean;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

public interface EmpService {
    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @return
     */
    PageBean page(Integer page, Integer pageSize, String name, Short gender, LocalDate begin, LocalDate end);

    /**
     * 批量删除
     *
     * @param ids
     */
    void detele(List<Integer> ids);

    /**
     * 新增员工
     *
     * @param emp
     */
    void save(Emp emp);

    /**
     * 根据ID查询员工
     *
     * @param id
     * @return
     */
    Emp getById(Integer id);

    /**
     * 更新员工
     *
     * @param emp
     */
    void update(Emp emp);

    /**
     * 员工登录
     *
     * @param emp
     * @return
     */
    Emp login(Emp emp);
}