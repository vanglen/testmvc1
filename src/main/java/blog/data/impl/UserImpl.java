package blog.data.impl;

import blog.data.UserRepository;
import blog.mapper.TUserMapper;
import blog.pojo.TUser;
import blog.pojo.TUserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/24.
 */
@Repository
public class UserImpl implements UserRepository {

    private TUserMapper tUserMapper;

    @Autowired
    public UserImpl(TUserMapper tUserMapper) {
        this.tUserMapper = tUserMapper;
    }

    public TUser getById(int id) {
        TUserExample example = new TUserExample();
        example.or().andIdGreaterThan(0);
        List<TUser> result = tUserMapper.selectByExample(example);
        return result.get(0);
    }
}
