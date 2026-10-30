package com.zc.mybackcrud.service.Impl;

import com.zc.mybackcrud.mapper.EmpLogMapper;
import com.zc.mybackcrud.pojo.EmpLog;
import com.zc.mybackcrud.service.EmpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EmpLogServiceImpl implements EmpLogService {

    @Autowired
    private EmpLogMapper empLogMapper;

    // 这个事务不依赖前一个事务，无论插入成功失败都可以提交到log
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertLog(EmpLog empLog) {
        empLogMapper.insert(empLog);
    }
}
