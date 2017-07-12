package blog.data;

import blog.pojo.TUser;

/**
 * Created by Administrator on 2017/3/24.
 */
public interface UserRepository {
    TUser getById(int id);
}
