package junit;

import spider.Impl.storage.BXWXNovelStorageImpl;
import spider.Impl.storage.KanShuZhongNovelStorageImpl;
import spider.Impl.chapter.BXWXChapterSpider;
import spider.Impl.chapter.DefaultChapterDetailSpider;
import spider.Impl.chapter.DefaultChapterSpider;
import spider.Impl.download.NovelDownLoad;
import spider.configuration.Configuration;
import spider.entitys.Chapter;
import spider.entitys.Novel;
import spider.interfaces.IChapterDetailSpider;
import spider.interfaces.IChapterSpider;
import spider.interfaces.INovelSpider;
import org.junit.Test;
import spider.interfaces.Processor;
import spider.util.NovelSiteEnum;
import spider.util.NovelSpiderFactory;
import spider.util.NovelSpiderUtil;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/24 22:04
 */
public class testcase {

    @Test
    public void test1(){
        IChapterSpider spider = new DefaultChapterSpider();

        List<Chapter> chapters = spider.getChapter("http://www.kanshuzhong.com/book/49179/");

        for (Chapter chapter : chapters){
            System.out.println(chapter);
        }
    }

    @Test
    public void testGetSiteContext(){
        System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByURL("http://www.biquge.com.tw/14_14055/")));
    }

    @Test
    public void testGetDetail(){
        IChapterDetailSpider spider = new DefaultChapterDetailSpider();
        System.out.println(spider.getChapterDetails("http://www.kanshuzhong.com/book/49179/10604325.html"));
    }

    @Test
    public void testBXWX(){
        IChapterSpider spider = new BXWXChapterSpider();

        List<Chapter> chapters = spider.getChapter("https://www.bxwx9.org/b/47/47750/index.html");

        for (Chapter chapter : chapters){
            System.out.println(chapter);
        }
    }

    @Test
    public void testDownLoad() throws FileNotFoundException, UnsupportedEncodingException {
        NovelDownLoad downLoad = new NovelDownLoad();
        Configuration configuration = new Configuration();
        configuration.setPath("/Users/sunxiaozhe/1");
        configuration.setSize(50);
        System.out.println("文件保存在：" + downLoad.downLoad("https://www.bxwx9.org/b/213/213015/index.html",configuration));
    }


    @Test
    public void testMultiFile() throws FileNotFoundException, UnsupportedEncodingException {
        NovelSpiderUtil.multiFileMerge("/Users/sunxiaozhe/1",null,true);
    }

    @Test
    public void testKanShuZhong(){
       INovelSpider spider =  NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/booktype/5/1/");
       List<Novel> novels = spider.getNovel("http://www.kanshuzhong.com/booktype/5/1/",10);
       for (Novel novel : novels){
           System.out.println(novel);
       }
    }

    @Test
    public void testBXWXNovel(){
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("https://www.bxwx9.org/bsort4/0/1.htm");
        List<Novel> novels = spider.getNovel("https://www.bxwx9.org/bsort5/0/1.htm",10);
        for (Novel novel : novels){
            System.out.println(novel);
        }
    }

    @Test
    public void testKanshuzhongIterator(){
        INovelSpider spider = NovelSpiderFactory.getNovelSpider("http://www.kanshuzhong.com/booktype/7/1/");
        Iterator<List<Novel>> iterator = spider.iterator("http://www.kanshuzhong.com/booktype/7/1/",10);
        while (iterator.hasNext()){
            List<Novel> novels = iterator.next();
            System.err.println("URL:"+spider.next());
//            for (Novel novel : novels){
//                System.out.println(novel);
//            }
        }

    }


    @Test
    public void testKanshuzhongProcess() throws FileNotFoundException {
        Processor processor = new KanShuZhongNovelStorageImpl();
        processor.process();
    }


    @Test
    public void tesrBXWXProcess() throws FileNotFoundException {
        Processor processor = new BXWXNovelStorageImpl();
        processor.process();
    }

}
