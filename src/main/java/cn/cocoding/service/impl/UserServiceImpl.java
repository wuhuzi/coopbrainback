package cn.cocoding.service.impl;

import cn.cocoding.entity.User;
import cn.cocoding.mapper.UserMapper;
import cn.cocoding.service.UserService;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;


/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    UserMapper userMapper;



    @Override
    public String login(User user) {
        User userByUsername = userMapper.getUserByUsername(user.getUserName());
        if(userByUsername.isEmpty(userByUsername)){
            return "fail";
        }
//        Assert.notNull(userByUsername,"object user is required in UserServiceImpl Class and login method");

        if (user.getUserPassword().equals(userByUsername.getUserPassword())){
            return "success";
        }
        return "fail";

    }

}
