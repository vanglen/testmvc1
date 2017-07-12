package blog.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by Administrator on 2017/3/17.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "blog.mapper")
public class DataConfig {

    @Profile("local")
    @Bean(name = "localDataSource")
    public DataSource localDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://HAOKANGLE;databaseName=test");
        ds.setUsername("sa");
        ds.setPassword("1234@qwer");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        return ds;
    }

    @Profile("dev")
    @Bean(name = "devDataSource")
    public DataSource devDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://10.168.100.104;databaseName=test");
        ds.setUsername("sa");
        ds.setPassword("AutoDbps123");
        ds.setInitialSize(5);
        ds.setMaxTotal(10);
        return ds;
    }

    @Bean(name = "jdbcTemplate")
    public JdbcOperations jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        return sqlSessionFactoryBean.getObject();
    }

//    @Bean(name = "sqlSessionFactoryBean")
//    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSource);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        Resource[] resources;
//        resources = resolver.getResources("classpath*:blog/mapper/*.xml");
//        sqlSessionFactoryBean.setMapperLocations(resources);
//        return sqlSessionFactoryBean;
//    }

//    @Bean(name = "mapperScannerConfigurer")
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        mapperScannerConfigurer.setBasePackage("blog.mapper");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
//        return mapperScannerConfigurer;
//    }
}
