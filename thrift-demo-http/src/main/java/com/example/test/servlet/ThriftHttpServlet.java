//package com.example.test.servlet;
//
//import org.apache.thrift.TProcessor;
//import org.apache.thrift.protocol.TProtocolFactory;
//import org.apache.thrift.server.TServlet;
//import org.apache.thrift.transport.TServerSocket;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 自定义servlet
// *
// * @author xf.chen
// * @date 2022/3/26 23:28
// * @since 1.0.0
// */
//@WebServlet(value = "/thriftHttp" )
//public class ThriftHttpServlet extends TServlet {
//
//    /**
//     * @param processor
//     * @param inProtocolFactory
//     * @param outProtocolFactory
//     * @see HttpServlet#HttpServlet()
//     */
//    public ThriftHttpServlet(TProcessor processor, TProtocolFactory inProtocolFactory, TProtocolFactory outProtocolFactory) {
//        super(processor, inProtocolFactory, outProtocolFactory);
//    }
//
//    /**
//     * @param request
//     * @param response
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//     * response)
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doPost(request, response);
//    }
//
//    /**
//     * @param request
//     * @param response
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//     * response)
//     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        super.doGet(request, response);
//    }
//}
