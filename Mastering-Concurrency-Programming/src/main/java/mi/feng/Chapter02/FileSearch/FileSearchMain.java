package mi.feng.Chapter02.FileSearch;

import mi.feng.Chapter02.FileSearch.serial.SerialFileSearch;
import mi.feng.Chapter02.FileSearch.utils.Result;

import java.io.File;
import java.util.Date;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/22 14:58
 * @Description:
 */
public class FileSearchMain {

    public static void main(String[] args) {

        File file = new File("C:\\Windows\\");
        String regex = "hosts";
        Date start, end;

        Result result = new Result();
        start = new Date();
        SerialFileSearch.searchFiles(file, regex, result);
        end = new Date();
        System.out.printf("Serial Search: Execution Time: %d%n", end.getTime() - start.getTime());


    }
}
