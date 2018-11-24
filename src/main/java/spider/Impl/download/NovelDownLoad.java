package spider.Impl.download;

import spider.configuration.Configuration;
import spider.entitys.Chapter;
import spider.entitys.ChapterDetail;
import spider.interfaces.IChapterDetailSpider;
import spider.interfaces.IChapterSpider;
import spider.interfaces.INovelDownlowd;
import spider.util.ChapterDetailSpiderFactory;
import spider.util.ChapterSpiderFactory;
import spider.util.NovelSiteEnum;
import spider.util.NovelSpiderUtil;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 15:07
 */
public class NovelDownLoad implements INovelDownlowd {


    @Override
    public String downLoad(String url,Configuration configuration) throws FileNotFoundException, UnsupportedEncodingException {

        IChapterSpider spider = ChapterSpiderFactory.getChapterSpider(url);
        List<Chapter> chapters = spider.getChapter(url);
        //某个线程下载完毕后，需要告诉主线程：下载好了
        //所有线程下载完毕，主线程进行合并；
        Integer size = configuration.getSize();

        //需要的线程数量
        Integer maxThreadSize = (int) Math.ceil(chapters.size() * 1.0 / size);


        Map<String,List<Chapter>> downloadTaskAlloc = new HashMap<String, List<Chapter>>();
        for (int i = 0; i < maxThreadSize; i++){
            //开始章节数
            int fromIndex = i * (configuration.getSize());
            if (i == maxThreadSize -1){
                int endIndex = chapters.size() - 1;
            }
            //结束章节数
            int endIndex = i == maxThreadSize -1 ? chapters.size() : i * (configuration.getSize()) + configuration.getSize();
            downloadTaskAlloc.put(fromIndex + "-" + endIndex,chapters.subList(fromIndex,endIndex));
        }

        ExecutorService executorService = Executors.newFixedThreadPool(maxThreadSize);

        Set<String> keySet = downloadTaskAlloc.keySet();

        List<Future<String>> tasks = new ArrayList<Future<String>>();

        //创建缺失的路径
        String savePath = configuration.getPath() + "/" + NovelSiteEnum.getEnumByURL(url).getUrl();
        new File(savePath).mkdirs();

        for (String key : keySet){

            tasks.add(executorService.submit(new DownloadCallable(savePath + "/" + key + ".txt",downloadTaskAlloc.get(key),configuration.getTryTimes())));
        }
        executorService.shutdown();

        for (Future<String> future : tasks){
            try {
                System.out.println(future.get() + ",下载完成");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        NovelSpiderUtil.multiFileMerge(savePath,null,true);
        return savePath + "/merge.txt";
    }


    class DownloadCallable implements Callable<String>{

        private List<Chapter> chapters;
        private String path;
        private int tryTimes;

        public DownloadCallable(String path,List<Chapter> chapters,int tryTimes){
            this.path = path;
            this.chapters = chapters;
            this.tryTimes = tryTimes;
        }
        @Override
        public String call() throws Exception {

            try (
                    PrintWriter out = new PrintWriter(new File(path),"utf-8");
            ){

                for (Chapter chapter : chapters){
                    IChapterDetailSpider spider = ChapterDetailSpiderFactory.getIChapterDetailDetailSpider(chapter.getUrl());
                    ChapterDetail chapterDetail = null;

                    for (int i = 0; i < tryTimes; i++){
                        try {
                            chapterDetail = spider.getChapterDetails(chapter.getUrl());
                            out.println(chapterDetail.getTitle());
                            out.println(chapterDetail.getContent());
                            break;
                        }catch (RuntimeException e){
                            System.err.println("尝试第【" + (i+1) + "/" + tryTimes + "】次下载失败！");
                        }
                    }
                }

            }catch (IOException e){
                throw new RuntimeException(e);
            }

            return path;
        }
    }

}
