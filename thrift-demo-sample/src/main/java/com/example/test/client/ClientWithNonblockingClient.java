package com.example.test.client;

import com.example.test.api.ExampleService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * NonblockingClient客户端
 *
 * @author xf.chen
 * @date 2022/3/20 21:34
 * @since 1.0.0
 */
public class ClientWithNonblockingClient {

    public static void main(String[] args) {
        TTransport transport = null;
        try {
            transport = new TSocket("localhost", 7777);
            // 服务端如果使用TNonblockingServer，客户端必须使用TFramedTransport
            transport = new TFramedTransport(transport);
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
