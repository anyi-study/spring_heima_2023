package com.softmen.controller;

import com.softmen.anno.Log;
import com.softmen.pojo.Dept;
import com.softmen.pojo.Result;
import com.softmen.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j  //日志注解
@RestController
@RequestMapping("/depts")
public class DeptController {
    //private static Logger log = LoggerFactory.getLogger(DeptController.class);
//    查询部门
    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)  //比较繁琐
    @GetMapping
    public Result list() {
        log.info("查询所有部门数据");
        List<Dept> deptList = deptService.list();
        return Result.success(deptList);
    }


    /**
     * 删除部门数据
     *
     * @return
     */
    @Log
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("根据id删除部门", id);
//        调用service删除部门
        deptService.delete(id);
        return Result.success();
    }

    /**
     * 添加部门
     *
     * @return
     */
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept) {
        log.info("添加部门:{}", dept);
        deptService.add(dept);
        return Result.success();
    }

    /**
     * 根据id查询部门
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getDeptById(@PathVariable Integer id) {
        log.info("查询部门ID为:", id);
        Dept dept = deptService.getDeptById(id);
        return Result.success(dept);
    }

    /**
     * 修改部门信息
     *
     * @param dept
     * @return
     */
    @PutMapping
    public Result updateDept(@RequestBody Dept dept) {
        log.info("修改成功ID:", dept);
        deptService.updateDept(dept);
        return Result.success();
    }

}
