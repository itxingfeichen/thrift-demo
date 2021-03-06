package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * 服务端
 *
 * @author xf.chen
 * @date 2022/3/19 19:25
 * @since 1.0.0
 */
@Slf4j
public class ThriftServer {

    public static void main(String[] args) throws TTransportException {

        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        TServerSocket serverSocket = new TServerSocket(8080);
        try {
            TServer.Args tArgs = new TServer.Args(serverSocket);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.processor(processor);
            TServer server = new TSimpleServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
