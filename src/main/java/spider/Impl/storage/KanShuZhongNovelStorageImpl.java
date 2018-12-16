package spider.Impl.storage;

import spider.interfaces.Processor;

import java.io.FileNotFoundException;
import java.util.*;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/31 17:09
 */
public class KanShuZhongNovelStorageImpl extends AbstractNovelStorage implements Processor {

    private Map<String,String> tasks= new HashMap<String, String>();

    public KanShuZhongNovelStorageImpl() throws FileNotFoundException {
        tasks.put("1","http://www.kanshuzhong.com/booktype/1/1/");
        tasks.put("2","http://www.kanshuzhong.com/booktype/2/1/");
        tasks.put("3","http://www.kanshuzhong.com/booktype/3/1/");
        tasks.put("4","http://www.kanshuzhong.com/booktype/4/1/");
        tasks.put("5","http://www.kanshuzhong.com/booktype/5/1/");
        tasks.put("6","http://www.kanshuzhong.com/booktype/6/1/");
        tasks.put("7","http://www.kanshuzhong.com/booktype/7/1/");
        tasks.put("8","http://www.kanshuzhong.com/booktype/8/1/");
    }

    @Override
    public void process() {
        super.process(tasks);
    }
}
