package spider.configuration;

import java.io.Serializable;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:57
 */
public class Configuration implements Serializable {

    //每个线程默认可以下载的最大章节数量
    public static final Integer DEFAULT_SIZE = 100;

    //默认尝试次数
    public static final int DEFAULT_TRY_TIMES = 3;

    //下载后存储的路径
    private String path;

    //每个线程下载的章节数量
    private Integer size;

    //尝试次数
    private int tryTimes;

    public Configuration() {
        this.size = DEFAULT_SIZE;
        this.tryTimes = DEFAULT_TRY_TIMES;
    }

    public int getTryTimes() {
        return tryTimes;
    }

    public void setTryTimes(int tryTimes) {
        this.tryTimes = tryTimes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
