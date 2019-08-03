package com.hairui.sm.service;

import com.hairui.sm.entity.Staff;

public interface SelfService {
    Staff login(String account, String password);
    void changePassword(Integer id, String password);
}
