package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;
import org.apache.thrift.transport.TTransportFactory;

/**
 * 基于TThreadPoolServer的服务端
 *
 * @author xf.chen
 * @date 2022/3/20 21:20
 * @since 1.0.0
 */
public class ServerWithTThreadPoolServer {

    public static void main(String[] args) throws TTransportException {
        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        TServerSocket serverSocket = new TServerSocket(9999);
        try {
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(serverSocket);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.processor(processor);
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
