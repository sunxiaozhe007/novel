package spider.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;

import java.net.URI;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/29 16:08
 */
public class NovelSpiderHttpGet extends HttpGet {

    public NovelSpiderHttpGet(){

    }

    public NovelSpiderHttpGet(URI uri){
        super(uri);
    }

    public NovelSpiderHttpGet(String uri){
        super(uri);
        setDefaultConfig();
    }

    private void setDefaultConfig(){
        this.setConfig(RequestConfig.custom()
                .setSocketTimeout(2000)
                .setConnectTimeout(10000)      //设置链接服务器的超时时间
                .setConnectionRequestTimeout(10000)    //设置从服务器读取数据的超时时间
                .build());
        this.setHeader("User-Agent","NovelSpider");//设置请求头
    }
}
