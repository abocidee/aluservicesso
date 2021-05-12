package xmltest;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.alu.itoken.service.sso.mapper.UserInfoMapper;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class XmlMybatisTest {

    private Object getMapper(Class claz) {
        String resource = "config/mybatis-config.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        return (Object)sqlSession.getMapper(claz);
    }
    
    @Test
    public void testFindRoelbyid(){
        UserInfoMapper mapper = (UserInfoMapper)getMapper(UserInfoMapper.class);
        mapper.findRoleByUid("1");
    }

    @Test
    public void testPermission() {
        UserInfoMapper mapper = (UserInfoMapper)getMapper(UserInfoMapper.class);
     mapper.findPermissionByUid("1");
        
    	
    }


  

}
