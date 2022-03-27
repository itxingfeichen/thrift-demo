package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.*;

/**
 * 基于TThreadPoolServer的服务端
 *
 * @author xf.chen
 * @date 2022/3/20 21:20
 * @since 1.0.0
 */
public class ServerWithTNonblockingServer {

    public static void main(String[] args) throws TTransportException {
        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(8888);
        try {
            TNonblockingServer.Args tArgs = new TNonblockingServer.Args(serverTransport);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.processor(processor);
            TServer server = new TNonblockingServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
