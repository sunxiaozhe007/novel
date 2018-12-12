package spider.util;

import spider.Impl.novel.BXWXNovelSpider;
import spider.Impl.novel.KanShuZhongNovelSpider;
import spider.interfaces.INovelSpider;

/**
 * 生成书籍列表的实现类
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/29 20:23
 */
public final class NovelSpiderFactory {

    private NovelSpiderFactory(){}

    public static INovelSpider getNovelSpider(String url){

        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByURL(url);
        switch (novelSiteEnum){
            case BiXiaWenXue:
                return new BXWXNovelSpider();
            case KanShuZhong:
                return new KanShuZhongNovelSpider();
            default:throw new RuntimeException(url + "暂时不被支持");
        }

    }
}
