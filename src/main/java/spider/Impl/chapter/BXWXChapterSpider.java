package spider.Impl.chapter;

import spider.entitys.Chapter;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 笔下文学章节列表排序
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:16
 */
public class BXWXChapterSpider extends AbstractChapterSpider {

    @Override
    public List<Chapter> getChapter(String url) {
        List<Chapter> chapters = super.getChapter(url);

        Collections.sort(chapters, new Comparator<Chapter>() {
            @Override
            public int compare(Chapter o1, Chapter o2) {
                String o1URL = o1.getUrl();
                String o2URL = o2.getUrl();
                String o1Index = o1URL.substring(o1URL.lastIndexOf('/')+1,o1URL.lastIndexOf('.'));
                String o2Index = o2URL.substring(o2URL.lastIndexOf('/')+1,o2URL.lastIndexOf('.'));
                return o1Index.compareTo(o2Index);
            }
        });
        return chapters;
    }
}
