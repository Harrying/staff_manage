package com.hairui.sm.dao;

import com.hairui.sm.entity.Staff;
import org.springframework.stereotype.Repository;

@Repository("selfDao")
public interface SelfDao {
    Staff selectByAccount(String account);
    /*其他操作比如修改和删除在StaffDao已经存在*/
}
