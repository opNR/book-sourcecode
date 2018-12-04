package mi.feng.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/12/4 10:01
 * @Description:
 */
public class IOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8000);

        new Thread(() -> {
            System.out.println("---------" + Thread.currentThread().getName());

            while (true) {
                try {
                    socket.getOutputStream().write((new Date() + ": Hello world").getBytes());
                    Thread.sleep(5000);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
