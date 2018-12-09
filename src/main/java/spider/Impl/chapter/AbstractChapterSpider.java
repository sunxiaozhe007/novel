package spider.Impl.chapter;

import spider.Impl.AbstractSpider;
import spider.entitys.Chapter;
import spider.interfaces.IChapterSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import spider.util.NovelSiteEnum;
import spider.util.NovelSpiderUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取任意网站的章节列表
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/24 21:52
 */
public class AbstractChapterSpider extends AbstractSpider implements IChapterSpider {

    @Override
    public List<Chapter> getChapter(String url) {
        String result = crawl(url);
        Document doc = Jsoup.parse(result);
        doc.setBaseUri(url);
        Elements elements = doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByURL(url)).get("chapter-list-selecter"));
        List<Chapter> chapters = new ArrayList<>();
        for (Element a : elements){

            Chapter chapter = new Chapter();

            chapter.setTitle(a.text());

            chapter.setUrl(a.absUrl("href"));

            chapters.add(chapter);
        }
        return chapters;
    }
}
