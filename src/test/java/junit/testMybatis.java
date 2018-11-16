package junit;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @program novel
 * @author: sunxiaozhe
 * @create: 2018/10/31 16:45
 */
public class testMybatis {

    @Test
    public void testMybatisConnection() throws FileNotFoundException {
        SqlSession session = new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml")).openSession();

        System.out.println(session);

    }
}
