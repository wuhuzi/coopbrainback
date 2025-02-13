package cn.cocoding.service;

import cn.cocoding.entity.DoubleObjectRelation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
public interface DoubleObjectRelationService extends IService<DoubleObjectRelation> {

    String saveEntity2EntityLib(String doubleObjectData4Json);

}
