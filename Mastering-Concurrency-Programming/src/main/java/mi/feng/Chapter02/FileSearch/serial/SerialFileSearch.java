package mi.feng.Chapter02.FileSearch.serial;

import mi.feng.Chapter02.FileSearch.utils.Result;

import java.io.File;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/22 14:20
 * @Description:
 */
public class SerialFileSearch {

    public static void searchFiles(File file, String fileName, Result result) {
        File[] contents = file.listFiles();

        if ((contents==null) || contents.length==0) {
            return;
        }

        for (File content : contents) {
            if (content.isDirectory()) {
                searchFiles(content, fileName, result);
            } else {
                if (content.getName().equals(fileName)) {
                    result.setPath(content.getAbsolutePath());
                    result.setFound(true);

                    System.out.printf("Serial Search: Path: %s%n", result.getPath());
                    return;
                }
            }

            if (result.isFound()) {
                return;
            }
        }
    }
}
