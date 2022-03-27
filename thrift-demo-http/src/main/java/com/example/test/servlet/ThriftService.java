package com.example.test.servlet;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TExtensibleServlet;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebServlet;

/**
 * Thrift servlet
 */
//@WebServlet(name="thrifttest",value="/thrifttest")
public class ThriftService extends TExtensibleServlet {
	private static final long serialVersionUID = 1L;
 
	@Override
	protected TProtocolFactory getInProtocolFactory() {
		TProtocolFactory factory = new TCompactProtocol.Factory();
		return factory;
	}
 
	@Override
	protected TProtocolFactory getOutProtocolFactory() {
		TProtocolFactory factory = new TCompactProtocol.Factory();
		return factory;
	}

	/**
	 * 在这里实现代理
	 * @return
	 */
	@Override
	protected TProcessor getProcessor() {
		// 接口实现类
		ExampleServiceImpl impl = new ExampleServiceImpl();
		// 返回处理器
		TProcessor tProcesser = new ExampleService.Processor<ExampleService.Iface>(impl);
		return tProcesser;
	}
}