package mi.feng.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/30 13:53
 * @Description:
 */
public class SimpleIOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9090);

        try{
            while (true){
                Socket socket = serverSocket.accept();
                try {
                    PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                    printWriter.println(new Date().toString());
                } finally {
                    System.out.println("--------socket close");
                    socket.close();
                }
            }
        } finally {
            System.out.println("--------server socket close");
            serverSocket.close();
        }

    }
}
