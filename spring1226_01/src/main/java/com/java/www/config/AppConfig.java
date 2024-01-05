package com.java.www.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

@Configuration
public class AppConfig {
	
	@Bean //myBatis객체를 생성
	public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
		//dataSource는 application # oracle DB 설정 # 정보를 불러옴
		
		//db연결(myBatis에 있는 session연결) - application.properties에서 정보를 가져와 db의 dataSource를 가져옴
		SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		sessionFactory.setDataSource(dataSource);
		
		//mapper연결 - query문이 담긴 mapper파일을 모두 가져와서 session에 넣어줌
		Resource[] res = new PathMatchingResourcePatternResolver().getResources(
				"classpath:/mapper/**/*.xml"  //classpath는 src/main/resources
				// **/*.xml - 모든폴더에 모든xml을 불러와라.
				);
		sessionFactory.setMapperLocations(res);
		
		return sessionFactory.getObject();  //myBatis에 db+mapper정보가 들어가 있는 모든 객체
	}
	
	//myBatis객체 1개를 리턴
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);   //myBatis에 사용할 1개 객체를 가져옴.
	}

}
