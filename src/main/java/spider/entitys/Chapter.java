package spider.entitys;

import java.io.Serializable;

/**
 * 小说章节实体
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/24 21:43
 */
public class Chapter implements Serializable {

    private String title;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "title='" + title + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
