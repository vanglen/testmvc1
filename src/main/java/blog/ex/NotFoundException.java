package blog.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2017/3/20.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found!")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String info) {
        this.setInfo(info);
    }

    private String info;

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
