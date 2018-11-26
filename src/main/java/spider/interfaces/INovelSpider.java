package spider.interfaces;

import spider.entitys.Novel;

import java.util.Iterator;
import java.util.List;

/**
 * 爬取某个站点的小说列表
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/29 18:47
 */
public interface INovelSpider {

    //抓取某一页面时最大的尝试次数
    static final int MAX_TRY_TIMES = 3;

    /**
     * 提供一个url，返回一个小说实体的集合
     * @param url
     * @return
     */
    List<Novel> getNovel(String url,Integer maxTryTimes);


    public boolean hasNext();
    public String next();
    public Iterator<List<Novel>> iterator(String firstPage,Integer maxTryTimes);
}
