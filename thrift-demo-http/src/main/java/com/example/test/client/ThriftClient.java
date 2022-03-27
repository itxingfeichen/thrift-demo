package com.example.test.client;

import com.example.test.api.ExampleService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.THttpClient;
import org.apache.thrift.transport.TTransport;

/**
 * @author xf.chen
 * @date 2022/3/26 23:49
 * @since 1.0.0
 */
public class ThriftClient {


    public static void httpClient() {
        String url = "http://localhost:8080/thrifttest";
        try {
            TTransport transport = new THttpClient(url);
            // HTTP通信协议
            TProtocol protocol = new TCompactProtocol(transport);
            ExampleService.Client client = new ExampleService.Client(protocol);

            transport.open();
            // 调用方法
            String val = client.get(10);
            System.out.println("Val: " + val);

            transport.close();
        } catch (TException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        httpClient();
    }
}
