package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

/**
 * 基于TThreadPoolServer的服务端
 *
 * @author xf.chen
 * @date 2022/3/20 21:20
 * @since 1.0.0
 */
public class ServerWithTHsHaServer {

    public static void main(String[] args) throws TTransportException {
        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(8888);
        try {
            THsHaServer.Args tArgs = new THsHaServer.Args(serverTransport);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.maxWorkerThreads(20);
            tArgs.minWorkerThreads(2);
            tArgs.processor(processor);
            TServer server = new THsHaServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
