package cn.service;

import cn.bean.SysLog;

import java.util.List;

public interface SysLogService {
    public void save(SysLog syslog);
    public List<SysLog> findAll();
}
