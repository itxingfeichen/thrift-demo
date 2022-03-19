package com.example.test.api.impl;

import com.example.test.api.ExampleService;
import com.example.test.api.MyException;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.TException;

/**
 * 服务实现类
 *
 * @author xf.chen
 * @date 2022/3/19 19:30
 * @since 1.0.0
 */
@Slf4j
public class ExampleServiceImpl implements ExampleService.Iface {
    @Override
    public void setExample(int key, String value) throws TException {
        log.info("key is {},value is {} ,save successful",key,value);
    }

    @Override
    public String get(int key) throws MyException, TException {
        log.info("12334");
        return "example.get is " + key;
    }

    @Override
    public void deleteExample(int key) throws TException {
        log.info("delete successful,the key is {}",key);
    }
}
