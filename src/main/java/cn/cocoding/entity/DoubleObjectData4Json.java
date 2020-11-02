package cn.cocoding.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description: 接收对象-关系-对象 之后拆分存储
 * @author: wuhuzi
 * @create: 2020-10-31 16:53
 **/
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DoubleObjectData4Json implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Object ObjectA;

    private Object ObjectB;

    private String relationship;
    /**
     * 0 代表只存关系
     * 1 代表只存一个
     * 2 全部存储
     */
    private Integer flag;

}
