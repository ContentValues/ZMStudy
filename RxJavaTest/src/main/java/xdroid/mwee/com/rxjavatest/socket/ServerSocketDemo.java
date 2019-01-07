package xdroid.mwee.com.rxjavatest.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhangmin on 2018/12/2.
 */

public class ServerSocketDemo {

    public static void main(String[] args) throws IOException {

        //1 监听端口
        final ServerSocket serverSocket = new ServerSocket(9999);

        //建立连接

        while (true) {

            final Socket socket = serverSocket.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    //得到输入流
                    try {

                        InputStream inputStream = socket.getInputStream();
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

                        String str = "";


                        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));


                        while ((str = bufferedReader.readLine()) != null) {

                            System.out.println("服务端收到推送-->" + str);

                            bufferedWriter.write("服务端回馈消息-->" + str);
                            bufferedWriter.write("\n");
                            bufferedWriter.flush();

                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }).start();
        }

    }



}
