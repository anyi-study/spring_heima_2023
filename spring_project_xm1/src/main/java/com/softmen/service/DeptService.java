package com.softmen.service;


import com.softmen.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * 查询全部部门数据
     *
     * @return
     */
    List<Dept> list();

    /**
     * 删除部门
     *
     * @param id
     */
    void delete(Integer id);

    /**
     * 添加部门
     *
     * @param dept
     */
    void add(Dept dept);

    /**
     * 根据ID查询部门
     *
     * @param id
     */
    Dept getDeptById(Integer id);

    /**
     * 根据ID修改部门数据
     *
     * @param dept
     */
    void updateDept(Dept dept);
}
