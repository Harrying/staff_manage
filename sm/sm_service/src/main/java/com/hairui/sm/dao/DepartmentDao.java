package com.hairui.sm.dao;

import com.hairui.sm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;
//作为IOC容器中的一个Bean，指定名称，Repository代表持久层
@Repository("departmentDao")
public interface DepartmentDao {
    void insert(Department department);
    void delete(Integer id);
    void update(Department department);
    Department selectById(Integer id);
    List<Department> selectAll();
}
