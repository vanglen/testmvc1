package blog.web;

import blog.data.BlogRepository;
import blog.ex.NotFoundException;
import blog.pojo.ApiError;
import blog.pojo.Blog;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2017/3/17.
 */
@Controller
@RequestMapping("/blogs")
public class BlogController {

    private BlogRepository blogRepository;

    @Autowired
    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<Blog> list() {
        return blogRepository.findBlogs();
    }

//    @RequestMapping(method = RequestMethod.GET)
//    public String list(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
//        model.addAttribute("blogList", blogRepository.findBlogs());
//        return "blogs";
//    }

    @ResponseBody
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public String getString(@RequestParam(value = "id", defaultValue = "5") int id) {
        Blog blog = blogRepository.findBlogById(id);
        Gson gson = new Gson();
        return gson.toJson(blog);
    }

    @ResponseBody
    @RequestMapping(value = "/get2/{id}", method = RequestMethod.GET, produces = "application/json")
    public Blog getJson(@PathVariable("id") int id) {
        return blogRepository.findBlogById(id);

    }

    @ResponseBody
    @RequestMapping(value = "/get3/{id}", method = RequestMethod.GET, produces = "application/xml")
    public Blog getXml(@PathVariable("id") int id) {
        return blogRepository.findBlogById(id);

    }

    /**
     * 使用json传输数据
     *
     * @param blog eg:{"title":"aa","author":"aa","content":"aa"}
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Blog addBlogByJson(@RequestBody Blog blog) {
        return blogRepository.addAndSelectBlog(blog);
    }

    /**
     * 使用xml传输数据
     *
     * @param blog eg:<?xml version="1.0"encoding="UTF-8"standalone="yes"?><blog><author>aa</author><content>aa</content><id>12</id><title>aa</title></blog>
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/add2", method = RequestMethod.POST, consumes = "application/xml", produces = "application/xml")
    public Blog addBlogByXml(@RequestBody Blog blog) {
        return blogRepository.addAndSelectBlog(blog);
    }

    @RequestMapping(value = "/getentity/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Blog> getEntity(@PathVariable int id) {
        Blog blog = blogRepository.findBlogById(id);
        HttpStatus status = blog != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<Blog>(blog, status);
    }

    @RequestMapping(value = "/getentity2/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getEntity2(@PathVariable int id) {
        Blog blog = blogRepository.findBlogById(id);
        if (blog != null) {
            return new ResponseEntity<Blog>(blog, HttpStatus.OK);
        } else {
            throw new NotFoundException("not found entity:" + id);
        }
    }
}
