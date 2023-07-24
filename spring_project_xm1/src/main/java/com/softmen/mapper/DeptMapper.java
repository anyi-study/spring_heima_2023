package com.softmen.mapper;

import com.softmen.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * 查询所有部门数据
     *
     * @return
     */
    @Select("select * from dept")
    List<Dept> list();

    /**
     * 根据ID删除部门
     *
     * @param id
     */
    @Delete("delete from dept where id = #{id}")
    void deleteById(Integer id);

    /**
     * 添加部门
     *
     * @param dept
     */
    @Insert("insert into dept(name,create_time,update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Dept dept);

    /**
     * 根据ID查询部门
     *
     * @param id
     */
    @Select("select * from dept where id = #{id}")
    Dept getDeptById(Integer id);

    /**
     * 根据ID修改部门数据
     *
     * @param dept
     */
    @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
    void updateDept(Dept dept);
}
