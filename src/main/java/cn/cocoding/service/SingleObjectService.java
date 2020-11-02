package cn.cocoding.service;

import cn.cocoding.entity.SingleObject;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
public interface SingleObjectService extends IService<SingleObject> {
    /**
     * 存入单个实体
     * @param singleObject
     * @return
     */
    int saveEntityLib(SingleObject singleObject);

    /**
     * 根据lable和name检测该实体有没有存储过
     * @param objectLableName
     * @param objectName
     * @return
     */
    SingleObject checkEntity(String objectLableName, String objectName);
}
