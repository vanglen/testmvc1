package blog.ex;

import blog.pojo.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2017/3/20.
 */
@ControllerAdvice
public class AppWideException {

    @ResponseBody
    @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found!")
    @ExceptionHandler(NotFoundException.class)
    public ApiError notFoundHandler(NotFoundException ex) {
        ApiError apiError = new ApiError();
        apiError.setCode(-1);
        apiError.setMessage(ex.getInfo());
        return apiError;
    }

    @ExceptionHandler(AccountException.class)
    public String accountHandler() {
        return "/error/account";
    }
}
