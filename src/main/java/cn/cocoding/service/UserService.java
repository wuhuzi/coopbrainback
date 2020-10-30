package cn.cocoding.service;

import cn.cocoding.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-27
 */
public interface UserService extends IService<User> {
    String login(User user);


}
