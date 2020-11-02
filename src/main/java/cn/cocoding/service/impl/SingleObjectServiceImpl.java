package cn.cocoding.service.impl;

import cn.cocoding.entity.SingleObject;
import cn.cocoding.mapper.SingleObjectMapper;
import cn.cocoding.service.SingleObjectService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * <p>
 *  服务实现类
 * </p>dd
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
@Service
public class SingleObjectServiceImpl extends ServiceImpl<SingleObjectMapper, SingleObject> implements SingleObjectService {
    @Resource
    SingleObjectMapper singleObjectMapper;

    @Override
    public int saveEntityLib(SingleObject singleObject) {
        if(singleObject ==null){
            return 0;
        }
        String attributeBuilder = "{\"" ;
        JSONArray jarr=JSONArray.parseArray(singleObject.getAttribute());//JSON.parseArray(jsonStr);
        for (Iterator iterator = jarr.iterator(); iterator.hasNext();) {
            JSONObject job=(JSONObject)iterator.next();
            String key=job.get("key").toString();
            String value=job.get("value").toString();
            System.out.println(key);
            System.out.println(value);
            System.out.println( attributeBuilder  + key + "\"" + ":" + "\"" + value + "\"");
            attributeBuilder = attributeBuilder  + key + "\"" + ":" + "\"" + value + "\"" + ",";
        }
        attributeBuilder = attributeBuilder + "\"brandOwner\":" + "\"" + singleObject.getBrandOwner() + "\"" + "}";
        singleObject.setAttribute(attributeBuilder);
        System.out.println(singleObject);
        return singleObjectMapper.insertSingleObject(singleObject);
    }

    @Override
    public SingleObject checkEntity(String objectLableName, String objectName) {
        return singleObjectMapper.checkEntity(objectLableName,objectName);
    }
}
