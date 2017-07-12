package blog.data;

import blog.pojo.Account;

/**
 * Created by Administrator on 2017/3/17.
 */
public interface AccountRepository {
    int register(Account account);
    int login(Account account);
}
