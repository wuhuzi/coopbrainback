package cn.cocoding.mapper;

import cn.cocoding.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-27
 */
public interface UserMapper extends BaseMapper<User> {

    User getUserByUsername(@Param("userName")String userName);

}
