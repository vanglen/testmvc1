package blog.web;

import blog.data.BlogRepository;
import blog.pojo.Blog;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/3/22.
 */
@RestController
public class BlogApiController {

    private BlogRepository blogRepository;

    @Autowired
    public BlogApiController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getString(@RequestParam(value = "id", defaultValue = "5") int id) {
        Blog blog = blogRepository.findBlogById(id);
        Gson gson = new Gson();
        return gson.toJson(blog);
    }
}
