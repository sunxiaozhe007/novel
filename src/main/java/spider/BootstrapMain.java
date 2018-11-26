package spider;

import spider.Impl.storage.BXWXNovelStorageImpl;
import spider.Impl.storage.KanShuZhongNovelStorageImpl;
import spider.interfaces.Processor;

import java.io.FileNotFoundException;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/11/02 14:42
 */
public class BootstrapMain {

    public static void main(String[] args) throws FileNotFoundException {
        Processor processor= new KanShuZhongNovelStorageImpl();
        processor.process();
        Processor processor1 = new BXWXNovelStorageImpl();
        processor1.process();
    }
}
