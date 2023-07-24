package com.softmen.controller;

import com.softmen.anno.Log;
import com.softmen.pojo.Emp;
import com.softmen.pojo.PageBean;
import com.softmen.pojo.Result;
import com.softmen.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j //日志注解
@RestController
@RequestMapping("/emps")
public class EmpController {
    //注入接口，面向接口开发
    @Autowired
    private EmpService empService;

    @GetMapping
    public Result page(@RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       String name,
                       Short gender,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
                       @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
//        if (page == null) page = 1; //比较繁琐  使用注解 @RequestParam(defaultValue = "1")
        log.info("分页查询,参数:{},{}", page, pageSize, name, gender, begin, end);
        //调用service
        PageBean pageBean = empService.page(page, pageSize, name, gender, begin, end);
        return Result.success(pageBean);
    }

    @Log
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("批量删除操作:", ids);
        empService.detele(ids);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result save(@RequestBody Emp emp) {
        log.info("新增员工:", emp);
        empService.save(emp);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询员工信息:{}", id);
        Emp emp = empService.getById(id);
        return Result.success(emp);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("更新员工:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
