package com.example.test.client;

import com.example.test.api.ExampleService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

/**
 * thrift客户端
 *
 * @author xf.chen
 * @date 2022/3/19 19:26
 * @since 1.0.0
 */
public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 8080);
            // 协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            ExampleService.Client client = new ExampleService.Client(protocol);
            transport.open();
            client.setExample('1', "2");
            System.out.println(client.get(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != transport) {
                transport.close();
            }
        }
    }
}
