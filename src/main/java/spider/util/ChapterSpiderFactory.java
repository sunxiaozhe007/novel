package spider.util;

import spider.Impl.chapter.BXWXChapterSpider;
import spider.Impl.chapter.DefaultChapterSpider;
import spider.interfaces.IChapterSpider;

import java.util.ArrayList;

/**
 * 获取章节列表工厂方法
 * 通过给定的url，返回应该使用的实现类
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:38
 */
public final class ChapterSpiderFactory {

    public static IChapterSpider getChapterSpider(String url){

        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByURL(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum){
            case BiXiaWenXue:2
                chapterSpider = new BXWXChapterSpider();
                break;
            case BiQuGe:
                chapterSpider = new DefaultChapterSpider();
            case DingDianXiaoShuo:
                chapterSpider = new DefaultChapterSpider();
            case KanShuZhong:
                chapterSpider = new DefaultChapterSpider();
        }
        return chapterSpider;
    }
}
