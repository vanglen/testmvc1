package blog.data.impl;

import blog.data.BlogRepository;
import blog.pojo.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Administrator on 2017/3/17.
 */
@Repository
public class BlogImpl implements BlogRepository {
    public static final int PARAMETER_INDEX = 3;
    private JdbcOperations jdbc;

    @Autowired
    public BlogImpl(JdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    public List<Blog> findBlogs() {
        List<Blog> blogs = new ArrayList<Blog>();
        Blog blog = new Blog();
        blog.setId(1);
        blog.setTitle("a-title");
        blog.setAuthor("a-author");
        blog.setContent("a-content");
        blog.setCreateTime(new Date());

        Blog blog2 = new Blog();
        blog2.setId(2);
        blog2.setTitle("b-title");
        blog2.setAuthor("b-author");
        blog2.setContent("b-content");
        blog2.setCreateTime(new Date());

        blogs.add(blog);
        blogs.add(blog2);
        return blogs;
    }

    public Blog findBlogById(int id) {
//        Blog blog = new Blog();
//        blog.setId(1);
//        blog.setTitle("a-title");
//        blog.setAuthor("a-author");
//        blog.setContent("a-content");
//        blog.setCreateTime(new Date());
//        return blog;
        List<Blog> result = jdbc.query("select * from t_blog where id=?", new BlogRowMapper(), id);
        if (result.size() > 0) {
            return result.get(0);
        } else {
            return null;
        }
    }

    public int addBlog(Blog blog) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("title", blog.getTitle());
        paramMap.put("author", blog.getAuthor());
        paramMap.put("content", blog.getContent());
        return jdbc.update("insert into t_blog (title, author, content) value (:title, :author, :content);", paramMap);
    }

    public Blog addAndSelectBlog(final Blog blog) {
        KeyHolder key = new GeneratedKeyHolder();
        final String sql = "INSERT INTO t_blog (title, author, content) VALUES (?,?,?)";
        PreparedStatementCreator preparedStatementCreator = new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
                ps.setString(1, blog.getTitle());
                ps.setString(2, blog.getAuthor());
                ps.setString(3, blog.getContent());
                return ps;
            }
        };
        int result = jdbc.update(preparedStatementCreator, key);
        if (result > 0) {
            blog.setId(key.getKey().intValue());
        }
        return blog;
    }

    private static class BlogRowMapper implements RowMapper<Blog> {

        public Blog mapRow(ResultSet resultSet, int i) throws SQLException {
            Blog blog = new Blog();
            blog.setId(resultSet.getInt("id"));
            blog.setTitle(resultSet.getString("title"));
            blog.setAuthor(resultSet.getString("author"));
            blog.setContent(resultSet.getString("content"));
            blog.setCreateTime(resultSet.getDate("createTime"));
            return blog;
        }
    }
}
