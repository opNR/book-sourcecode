package mi.feng.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/30 13:54
 * @Description:
 */
public class SimpleIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",9090);
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        System.out.println(reader.readLine());
    }
}
