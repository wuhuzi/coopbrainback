package cn.cocoding.mapper;

import cn.cocoding.entity.SingleObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
public interface SingleObjectMapper extends BaseMapper<SingleObject> {
    /**
     * 存入单个实体
     * @param singleObject
     * @return
     */
    int  insertSingleObject(SingleObject singleObject);

    /**
     * 检测实体有没有存储过 Dao 查询结果不为空认为存储过
     * @param objectLableName
     * @param objectName
     * @return
     */
    SingleObject checkEntity(@Param("objectLableName")String objectLableName, @Param("objectName")String objectName);
}
