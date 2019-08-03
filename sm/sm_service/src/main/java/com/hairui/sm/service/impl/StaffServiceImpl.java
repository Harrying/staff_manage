package com.hairui.sm.service.impl;

import com.hairui.sm.dao.StaffDao;
import com.hairui.sm.entity.Staff;
import com.hairui.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service("staffService")
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;

    public void add(Staff staff) {
        //表现层无法设置的属性进行默认设置
        staff.setPassword("123456");
        staff.setWorkTime(new Date());//取系统当前时间
        staff.setStatus("正常");//账户状态（如果需要审核则可以设置false，不能直接登录）
        staffDao.insert(staff);
    }

    public void remove(Integer id) {
        staffDao.delete(id);
    }

    public void edit(Staff staff) {
        staffDao.update(staff);
    }

    public Staff get(Integer id) {
        return staffDao.selectById(id);
    }

    public List<Staff> getAll() {
        return staffDao.selectAll();
    }
}
