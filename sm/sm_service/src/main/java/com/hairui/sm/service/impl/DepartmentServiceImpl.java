package com.hairui.sm.service.impl;

import com.hairui.sm.dao.DepartmentDao;
import com.hairui.sm.entity.Department;
import com.hairui.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
//作为IOC容器中的一个Bean，指定名称 Service代表业务层
@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
    //自动注入
    @Autowired
    private DepartmentDao departmentDao;

    public void add(Department department) {
        departmentDao.insert(department);
    }

    public void remove(Integer id) {
        departmentDao.delete(id);
    }

    public void edit(Department department) {
        departmentDao.update(department);
    }

    public Department get(Integer id) {
        return departmentDao.selectById(id);
    }

    public List<Department> getAll() {
        return departmentDao.selectAll();
    }
}
