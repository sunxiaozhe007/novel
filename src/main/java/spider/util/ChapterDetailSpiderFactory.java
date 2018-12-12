package spider.util;

import spider.Impl.chapter.DefaultChapterDetailSpider;
import spider.interfaces.IChapterDetailSpider;

/**
 * 获取章节实体工厂方法
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:42
 */
public final class ChapterDetailSpiderFactory {

    private ChapterDetailSpiderFactory(){

    }

    /**
     * 通过给定url获取具体实现类
     * @param url
     * @return
     */
    public static IChapterDetailSpider getIChapterDetailDetailSpider(String url){

        IChapterDetailSpider spider = null;

        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByURL(url);

        switch (novelSiteEnum){
            case DingDianXiaoShuo:
            case BiQuGe:
            case BiXiaWenXue:
            case KanShuZhong:
                spider = new DefaultChapterDetailSpider();
                break;
        }
        return spider;
    }
}
