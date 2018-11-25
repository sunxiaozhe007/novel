package spider.interfaces;

import spider.entitys.ChapterDetail;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 13:31
 */
public interface IChapterDetailSpider {

    /**
     * 提供一个url，返回一个章节内容详细内容实体
     * @param url
     * @return
     */
    ChapterDetail getChapterDetails(String url);
}
