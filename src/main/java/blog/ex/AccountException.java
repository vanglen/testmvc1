package blog.ex;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Administrator on 2017/3/20.
 */

@ResponseStatus(value = HttpStatus.ACCEPTED,reason = "Account error!")
public class AccountException extends RuntimeException {
}
