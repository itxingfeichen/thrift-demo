package com.example.test.server;

import com.example.test.api.ExampleService;
import com.example.test.api.impl.ExampleServiceImpl;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 基于TThreadPoolServer的服务端
 *
 * @author xf.chen
 * @date 2022/3/20 21:20
 * @since 1.0.0
 */
public class ServerWithTThreadedSelectorServer {

    public static void main(String[] args) throws TTransportException {
        final ExampleService.Processor<ExampleService.Iface> processor = new ExampleService.Processor<>(new ExampleServiceImpl());
        TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(7777);
        try {
            TThreadedSelectorServer.Args tArgs = new TThreadedSelectorServer.Args(serverTransport);
            tArgs.protocolFactory(new TBinaryProtocol.Factory());
            tArgs.transportFactory(new TFramedTransport.Factory());
            tArgs.processor(processor);
            // worker线程池
            int threads = Runtime.getRuntime().availableProcessors() ;
            final ThreadPoolExecutor executorService = new ThreadPoolExecutor(threads, threads + 1, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), new DefaultThreadFactory());
            tArgs.executorService(executorService);
            // 这里留个疑问，这里的主线程和worker线程都是使用上面的线程池定义的？待验证
            tArgs.selectorThreads(1);
            tArgs.workerThreads(Runtime.getRuntime().availableProcessors() + 1);
            tArgs.acceptQueueSizePerThread(1000);
            TServer server = new TThreadedSelectorServer(tArgs);
            server.serve();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final String namePrefix;

        DefaultThreadFactory() {
            SecurityManager s = System.getSecurityManager();
            group = (s != null) ? s.getThreadGroup() :
                    Thread.currentThread().getThreadGroup();
            namePrefix = "ServerWithTThreadedSelectorServer-" +
                    poolNumber.getAndIncrement() +
                    "-thread-";
        }

        public Thread newThread(Runnable r) {
            Thread t = new Thread(group, r,
                    namePrefix + threadNumber.getAndIncrement(),
                    0);
            if (t.isDaemon())
                t.setDaemon(false);
            if (t.getPriority() != Thread.NORM_PRIORITY)
                t.setPriority(Thread.NORM_PRIORITY);
            return t;
        }
    }
}
