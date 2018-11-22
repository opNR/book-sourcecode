package mi.feng.Chapter02.FileSearch.utils;

/**
 * @Auther: MiFeng
 * @Date: 2018/11/22 14:17
 * @Description:
 */
public class Result {

    private String path;
    private boolean found = false;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }
}
