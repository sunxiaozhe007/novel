package spider.interfaces;

import spider.entitys.Chapter;

import java.util.List;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/24 21:49
 */
public interface IChapterSpider {

    /**
     * 提供完整url，返回所有的章节列表
     * @param url
     * @return
     */
    List<Chapter> getChapter(String url);
}
