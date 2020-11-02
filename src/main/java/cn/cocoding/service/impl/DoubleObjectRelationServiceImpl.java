package cn.cocoding.service.impl;

import cn.cocoding.controller.DoubleObjectRelationController;
import cn.cocoding.entity.DoubleObjectRelation;
import cn.cocoding.entity.ObjectRelationObject;
import cn.cocoding.entity.SingleObject;
import cn.cocoding.mapper.DoubleObjectRelationMapper;
import cn.cocoding.mapper.ObjectRelationObjectMapper;
import cn.cocoding.mapper.SingleObjectMapper;
import cn.cocoding.service.DoubleObjectRelationService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oracle.tools.packager.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
@Service
public class DoubleObjectRelationServiceImpl extends ServiceImpl<DoubleObjectRelationMapper, DoubleObjectRelation> implements DoubleObjectRelationService {
    private static final Logger logger = LoggerFactory.getLogger(DoubleObjectRelationServiceImpl.class);
    @Resource
    DoubleObjectRelationMapper doubleObjectRelationMapper;

    @Resource
    SingleObjectMapper singleObjectMapper;

    @Resource
    ObjectRelationObjectMapper objectRelationObjectMapper;


    @Override
    public String saveEntity2EntityLib(String doubleObjectData4Json) {

        // 解析
        JSONObject jsonObject = new JSONObject().parseObject(doubleObjectData4Json);
        logger.info("jsonObject : " + jsonObject);
        System.out.println("------------------------------------------");
        logger.info("objectA json : " + jsonObject.getString("objectA"));

        JSONObject objectA = new JSONObject().parseObject(jsonObject.getString("objectA"));

        logger.info("objectA"+objectA);

        logger.info("objectA"+objectA.getString("objectLableName"));
        logger.info("objectA"+objectA.getString("ObjectName"));
        JSONObject objectB = new JSONObject().parseObject(jsonObject.getString("objectB"));

        /**
         * todo
        // 如果都已经存在 只存关系
        String flag = jsonObject.getString("flag");
        if ("0".equals(flag)){

        }
        // 如果 一个存在一个不存在 只存一个对象和一个关系
        JSONObject objectA = JSON.parseObject(jsonObject.getString("ObjectA"));
        JSONObject objectB = JSON.parseObject(jsonObject.getString("ObjectB"));
        if("1".equals(flag)){
            if("".equals(objectA.getString("attribute"))){
                // 存B和关系
            }else{
                // 存A和关系
            }
        }
        // 如果都不在 那就都存 A B 关系

         */
        // 解析出attribute
        String attributeBuilderA = "{\"" ;
        JSONArray jarr=JSONArray.parseArray(objectA.getString("attribute"));//JSON.parseArray(jsonStr);
        for (Iterator iterator = jarr.iterator(); iterator.hasNext();) {
            JSONObject job=(JSONObject)iterator.next();
            String key=job.get("key").toString();
            String value=job.get("value").toString();
            System.out.println(key);
            System.out.println(value);
            System.out.println( attributeBuilderA  + key + "\"" + ":" + "\"" + value + "\"");
            attributeBuilderA = attributeBuilderA  + key + "\"" + ":" + "\"" + value + "\"" + ",";
        }
        attributeBuilderA = attributeBuilderA + "\"brandOwner\":" + "\"" + objectA.getString("brandOwner") + "\"" + "}";

        // 插入A
        SingleObject singleObjectA = new SingleObject();
        singleObjectA.setUserId(1);
        singleObjectA.setObjectLableName(objectA.getString("objectLableName"));
        singleObjectA.setObjectName(objectA.getString("objectName"));
        singleObjectA.setAttribute(attributeBuilderA);
        System.out.println(attributeBuilderA);

        // 解析出attribute
        String attributeBuilderB = "{\"" ;
        JSONArray jarrB=JSONArray.parseArray(objectB.getString("attribute"));//JSON.parseArray(jsonStr);
        for (Iterator iterator = jarrB.iterator(); iterator.hasNext();) {
            JSONObject job=(JSONObject)iterator.next();
            String key=job.get("key").toString();
            String value=job.get("value").toString();
            System.out.println(key);
            System.out.println(value);
            System.out.println( attributeBuilderB  + key + "\"" + ":" + "\"" + value + "\"");
            attributeBuilderB = attributeBuilderB  + key + "\"" + ":" + "\"" + value + "\"" + ",";
        }
        attributeBuilderB = attributeBuilderB + "\"brandOwner\":" + "\"" + objectB.getString("brandOwner") + "\"" + "}";
        System.out.println(attributeBuilderB);
        // 插入B
        SingleObject singleObjectB = new SingleObject();
        singleObjectB.setUserId(1);
        singleObjectB.setObjectLableName(objectB.getString("objectLableName"));
        singleObjectB.setObjectName(objectB.getString("objectName"));
        singleObjectB.setAttribute(attributeBuilderB);

        // 存入实体A
        int A = singleObjectMapper.insertSingleObject(singleObjectA);
        if(A > 0){
            // 获得返回的id
            System.out.println("singleObjectA.getId()"+singleObjectA.getId());
        }
        // 存入实体B
        int B = singleObjectMapper.insertSingleObject(singleObjectB);
        if(B > 0){
            // 获得返回的id
            System.out.println("singleObjectB.getId()"+singleObjectB.getId());
        }
        // 获取 relation
        String relationship = jsonObject.getString("relationship");
        // 存入数据到 cbb_object_relation_object
        ObjectRelationObject objectRelationObject = new ObjectRelationObject();
        objectRelationObject.setObjectOneId(singleObjectA.getId())
                .setObjectTwoId(singleObjectB.getId())
                .setRelationName(relationship)
                .setRelationAttribute("{" + "}");
        int row = objectRelationObjectMapper.insertObjectReationObject(objectRelationObject);
        if(row > 0){
            return "success";
        }

        return "fail";


    }
}
