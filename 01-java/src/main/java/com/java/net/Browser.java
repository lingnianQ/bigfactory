package com.java.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Browser {
    public static void main(String[] args) throws IOException {
        //1.创建Socket对象，建立与baidu服务器的连接
        String host="www.baidu.com";
        int port=80;
        Socket socket=new Socket(host,port);
        //2.创建流对象用于实现与远端服务的数据交互
        InputStream in=socket.getInputStream();
        OutputStream out=socket.getOutputStream();
        //3.向服务端发起请求
        out.write("GET /index.html HTTP/1.1\n\r".getBytes());//注意这里的空格
        out.write("\n\r".getBytes());
        out.flush();
        //4.读取服务端响应的数据
        byte[] buf=new byte[1024];
        int len=-1;
        while((len=in.read(buf))!=-1){
            System.out.println(new String(buf,0,len));
        }
        out.close();
        in.close();
    }
}
