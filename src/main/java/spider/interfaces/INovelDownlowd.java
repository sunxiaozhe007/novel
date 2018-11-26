package spider.interfaces;

import spider.configuration.Configuration;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:03
 */
public interface INovelDownlowd {

    /**
     * 提供需要下载的小说章节列表url
     * @param url
     * @return
     */
    String downLoad(String url, Configuration configuration) throws FileNotFoundException, UnsupportedEncodingException;
}



