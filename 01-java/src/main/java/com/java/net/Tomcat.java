package com.java.net;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Tomcat {
    private static int corePoolSize=8;
    private static int maximumPoolSize=128;
    private static int keepAliveTime=60;
    private static BlockingQueue<Runnable> workQueue=
            new ArrayBlockingQueue<>(64);


    public static void main(String[] args) throws IOException {
        //1.创建一个Server
        ServerSocket server=new ServerSocket(9999);
        System.out.println("server start ok");
        ThreadPoolExecutor threadPoolExecutor=
                new ThreadPoolExecutor(corePoolSize, maximumPoolSize
                , keepAliveTime, TimeUnit.SECONDS,workQueue);

        //2.不断等待客户端连接
        boolean flag=true;
        while(flag){
            Socket client = server.accept();//阻塞式方法
            //3.处理客户端请求;
            threadPoolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        OutputStream out = client.getOutputStream();
                        String data = "HTTP/1.1 200 OK \n\r" +
                                "Content-Type: text/html;charset=utf-8 \n\r" +
                                "\n\r" +
                                "Hello Client Browser";
                        out.write(data.getBytes());
                        out.flush();
                        client.close();
                    }catch (Exception e){e.printStackTrace();}
                }
            });

        }
    }
}
