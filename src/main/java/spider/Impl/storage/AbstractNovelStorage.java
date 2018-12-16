package spider.Impl.storage;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.util.PropertiesUtil;
import spider.entitys.Novel;
import spider.interfaces.INovelSpider;
import spider.interfaces.Processor;
import spider.util.NovelSpiderFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/11/01 13:23
 */
public abstract class AbstractNovelStorage implements Processor {

    ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
    InputStream in = classLoader.getResourceAsStream("conf/SqlMapConfig.xml");

    protected SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

    protected AbstractNovelStorage() throws FileNotFoundException {
    }



    public void process(Map<String,String> tasks) {
        ExecutorService service = Executors.newFixedThreadPool(tasks.size());
        List<Future<String>> futures = new ArrayList<Future<String>>(tasks.size());
        for (Map.Entry<String,String> entry : tasks.entrySet()){
            final String key = entry.getKey();
            final String value = entry.getValue();
            futures.add(service.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    INovelSpider spider = NovelSpiderFactory.getNovelSpider(value);
                    Iterator<List<Novel>> iterator = spider.iterator(value,10);
                    while (iterator.hasNext()){
                        System.err.println("开始抓取【" + key + "】的URL：" + spider.next());
                        List<Novel> novels = iterator.next();
                        SqlSession session = sqlSessionFactory.openSession();
                        session.insert("batchInsert",novels);
                        session.commit();
                        session.close();
                        Thread.sleep(1000);
                    }
                    return key;
                }
            }));
        }
        service.shutdown();

        for (Future<String> future : futures){
            try {
                System.out.println("抓取【"+future.get()+"】结束！");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
