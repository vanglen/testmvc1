package blog.data.impl;

import blog.data.AccountRepository;
import blog.pojo.Account;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/17.
 */
@Repository
public class AccountImpl implements AccountRepository {
    public int register(Account account) {
        return 0;
    }

    public int login(Account account) {
        return 0;
    }
}
