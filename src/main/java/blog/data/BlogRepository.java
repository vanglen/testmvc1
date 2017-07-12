package blog.data;

import blog.pojo.Blog;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface BlogRepository {
    List<Blog> findBlogs();

    Blog findBlogById(int id);

    int addBlog(Blog blog);

    Blog addAndSelectBlog(Blog blog);
}
