package spider.Impl.chapter;

import spider.Impl.AbstractSpider;
import spider.entitys.ChapterDetail;
import spider.interfaces.IChapterDetailSpider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import spider.util.NovelSiteEnum;
import spider.util.NovelSpiderUtil;

import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 13:33
 */
public class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider {


    @Override
    public ChapterDetail getChapterDetails(String url) {
        try {
            String result = super.crawl(url);
            result = result.replace("&nbsp;"," ").replace("<br />","${line}").replace("<br/>","${line}");
            Document document = Jsoup.parse(result);
            document.setBaseUri(url);
            Map<String,String> contexts = NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByURL(url));
            ChapterDetail detail = new ChapterDetail();

            //获取标题
            String titleSelecter = contexts.get("chapter-detail-title-selecter");
            String [] splits = titleSelecter.split("\\,");
            splits = parseSelecter(splits);
            detail.setTitle(document.select(splits[0]).get(Integer.parseInt(splits[1])).text());

            //获取文章内容
            String contentSelecter = contexts.get("chapter-detail-content-selecter");
            detail.setContent(document.select(contentSelecter).first().text().replace("${line}","\n"));

            //获取前一章节url
            String prevSelecter = contexts.get("chapter-detail-prev-selecter");
            splits = prevSelecter.split("\\,");
            splits = parseSelecter(splits);
            detail.setPrev(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //获取后一章节url
            String nextSelecter = contexts.get("chapter-detail-next-selecter");
            splits = nextSelecter.split("\\,");
            splits = parseSelecter(splits);
            detail.setNext(document.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));

            //返回实体
            return detail;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String[] parseSelecter(String[] splits){
        String[] newSplits = new String[2];
        if (splits.length ==1){
            newSplits[0] = splits[0];
            newSplits[1] = "0";
            return newSplits;
        }else {
            return splits;
        }
    }
}
