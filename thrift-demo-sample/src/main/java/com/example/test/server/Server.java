package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.apache.thrift.transport.TTransportFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * Created by huyanshi on 2019/03/28.
 */
public class Server {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    private void startServer() {
        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        try {
            TServerTransport transport = new TServerSocket(9090);
            TThreadPoolServer.Args tArgs = new TThreadPoolServer.Args(transport);
            tArgs.processor(processor);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TTransportFactory());
            tArgs.minWorkerThreads(10);
            tArgs.maxWorkerThreads(20);
            TServer server = new TThreadPoolServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
 
    public static void main(String[] args) {
        Server server = new Server();
        server.startServer();
    }
}
