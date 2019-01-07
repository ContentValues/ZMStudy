package xdroid.mwee.com.rxjavatest.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * Created by zhangmin on 2018/12/2.
 */

public class ClientSocketDemo {

    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream = socket.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
            //bufferedWriter.write(Thread.currentThread().getName() + "客户端建立的第一个连接");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));

            BufferedReader readerFromSever = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true) {
                bufferedWriter.write(bufferedReader.readLine());
                bufferedWriter.write("\n");
                bufferedWriter.flush();

                String str = readerFromSever.readLine();
                if (str != null) {
                    System.out.println("客户端收到服务端回馈消息-->" + str);
                }

            }
            //socket.close() 将socket关闭连接，那边如果有服务端给客户端反馈信息，此时客户端是收不到的
            //socket.close();
            //socket.shutdownOutput()是将输出流关闭，此时，如果服务端有信息返回，则客户端是可以正常接受的
            //socket.shutdownOutput();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
