package spider.util;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/25 10:33
 */
public final class NovelSpiderUtil {

    private static final Map<NovelSiteEnum, Map<String, String>> CONTEXT_MAP = new HashMap<NovelSiteEnum, Map<String, String>>();

    static {
        init();
    }

    private NovelSpiderUtil() {

    }

    @SuppressWarnings("unchecked")
    private static void init() {
        SAXReader reader = new SAXReader();
        try {

            ClassLoader classLoader = PropertiesUtil.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream("conf/Spider-Rule.xml");
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List<Element> sites = root.elements("site");
            for (Element site : sites) {
                List<Element> subs = site.elements();
                Map<String, String> subMap = new HashMap<String, String>();
                for (Element sub : subs) {
                    String name = sub.getName();
                    String text = sub.getTextTrim();
                    subMap.put(name, text);
                }
                CONTEXT_MAP.put(NovelSiteEnum.getEnumByURL(subMap.get("url")), subMap);
            }

        } catch (DocumentException e) {
            throw new RuntimeException("未加载到配置文件");
        }
    }

    /**
     * 拿到对应网站的解析规则
     *
     * @param novelSiteEnum
     * @return
     */
    public static Map<String, String> getContext(NovelSiteEnum novelSiteEnum) {
        return CONTEXT_MAP.get(novelSiteEnum);
    }


    /**
     * 多个文件合并为一个文件，合并规则：按文件名分割排序
     * @param path   基础目录，该根目录下的所有文本文件都会被合并到mergeToFile
     * @param mergeToFile  被合并的文本文件，这个参数可以为null，合并后的文件保存在path/merge。txt
     */
    public static void multiFileMerge(String path, String mergeToFile,boolean deleteThisFile) throws FileNotFoundException, UnsupportedEncodingException {

        mergeToFile = mergeToFile == null ? path + "/merge.txt" : mergeToFile;
        File[] files = new File(path).listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
             return name.endsWith(".txt");
            }
        });
        Arrays.sort(files, new Comparator<File>() {
            @Override
            public int compare(File o1, File o2) {
                int o1Index = Integer.parseInt(o1.getName().split("\\-")[0]);
                int o2Index = Integer.parseInt(o2.getName().split("\\-")[0]);
                if (o1Index > o2Index){
                    return 1;
                }else if (o1Index == o2Index){
                    return 0;
                }else {
                    return -1;
                }
            }
        });
        PrintWriter out = null;
        try {
         out = new PrintWriter(new File(mergeToFile),"utf-8");
        for (File file : files) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "utf-8"));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                out.println(line);
            }
            bufferedReader.close();

            if (deleteThisFile){
                file.delete();
            }
        }
           }catch (Exception e){
               throw new RuntimeException(e);
           }finally {
            out.close();
        }
       }

    /**
     * 获取书籍状态
     * @param status
     * @return
     */
    public static int getNovelStatus(String status){
        if (status.contains("连载")){
            return 1;
        }else if (status.contains("完结") || status.contains("完本") || status.contains("已完成")){
            return 2;
        }else {
            throw new RuntimeException("不支持的书籍状态：" + status);
        }
       }

    /**
     * 格式化日期字符串
     * @param dateStr
     * @param patten
     * @return
     * @throws ParseException
     */
       public static Date getDate(String dateStr,String patten) throws ParseException {
           if ("MM-dd".equals(patten)){
               patten = "yyyy-MM-dd";
               dateStr = getDateField(Calendar.YEAR)+ "-" + dateStr;
           }
           SimpleDateFormat sdf = new SimpleDateFormat(patten);
           Date date = sdf.parse(dateStr);
           return date;
       }

    /**
     * 获取本时刻自负量
     * @param field
     * @return
     */
    public static String getDateField(int field){
           Calendar cal = new GregorianCalendar();
           return cal.get(field) + "";
       }
    }

