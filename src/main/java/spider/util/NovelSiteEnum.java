package spider.util;

/**
 * 已经支持的小说网站枚举
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 10:35
 */
public enum NovelSiteEnum {

    DingDianXiaoShuo(1,"23us.so"),
    BiQuGe(2,"biquge.com.tw/"),
    BiXiaWenXue(4,"bxwx9.org"),
    KanShuZhong(3,"kanshuzhong.com");

    private int id;
    private String url;

    private NovelSiteEnum(int id,String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static NovelSiteEnum getEnumById(int id){
        switch (id){
            case 1:return DingDianXiaoShuo;
            case 2:return BiQuGe;
            default:throw new RuntimeException("id="+id+"是不被支持的小说网站");
        }
    }

    public static NovelSiteEnum getEnumByURL(String url){
        for (NovelSiteEnum novelSiteEnum : values()){
            if (url.contains(novelSiteEnum.url)){
                return novelSiteEnum;
            }
        }
        throw new RuntimeException("url=" + url +"是不被支持的小说网站");
    }
}
