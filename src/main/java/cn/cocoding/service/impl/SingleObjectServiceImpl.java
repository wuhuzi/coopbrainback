package cn.cocoding.service.impl;

import cn.cocoding.entity.AttributeSubEntity;
import cn.cocoding.entity.SingleObject;
import cn.cocoding.entity.UploadFileResultData;
import cn.cocoding.entity.UploadFileUrlConvert;
import cn.cocoding.mapper.SingleObjectMapper;
import cn.cocoding.service.SingleObjectService;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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
        /**
         * 封装attribute 方案一
         * // 封装attribute
         *         String attributeBuilder = "{\"" ;
         *         JSONArray jarr=JSONArray.parseArray(singleObject.getAttribute());//JSON.parseArray(jsonStr);
         *         for (Iterator iterator = jarr.iterator(); iterator.hasNext();) {
         *             JSONObject job=(JSONObject)iterator.next();
         *             String key=jo  b.get("key").toString();
         *             String value=job.get("value").toString();
         *             System.out.println(key);
         *             System.out.println(value);
         *             System.out.println( attributeBuilder  + key + "\"" + ":" + "\"" + value + "\"");
         *             attributeBuilder = attributeBuilder  + key + "\"" + ":" + "\"" + value + "\"" + ",";
         *         }
         *         attributeBuilder = attributeBuilder + "\"brandOwner\":" + "\"" + singleObject.getBrandOwner() + "\"" + "}";
         */

        // 封装attribute 方案二
        String attributeBuilder = "" ;
        JSONArray jarr=JSONArray.parseArray(singleObject.getAttribute());//JSON.parseArray(jsonStr);
        ArrayList<AttributeSubEntity> arrayList = new ArrayList<AttributeSubEntity>();
        for (Iterator iterator = jarr.iterator(); iterator.hasNext();) {
            JSONObject job=(JSONObject)iterator.next();
            String key=job.get("key").toString();
            String value=job.get("value").toString();
            String label = "属性"; // 固定值
            AttributeSubEntity attributeSubEntity = new AttributeSubEntity();
            attributeSubEntity.setKey(key).setValue(value).setLabel(label);
            // 封装成list
            arrayList.add(attributeSubEntity);

        }
        arrayList.add(new AttributeSubEntity()
                .setValue("颜色")
                .setKey(singleObject.getBrandOwner())
                .setLabel("属性"));

        attributeBuilder = JSON.toJSONString(arrayList);
        singleObject.setAttribute(attributeBuilder);
        System.out.println(singleObject);
        /**
         * 重新封装 fileUrl
         */

        String fileUrl = singleObject.getFileUrl();
        fileUrl = "[" + fileUrl + "]";
        System.out.println("newFileUrl:" +  fileUrl);
        List<UploadFileResultData> uploadFileResultDataList = JSON.parseArray(fileUrl, UploadFileResultData.class);
        // 构造map treeMap
        Map<String, String> treeMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    public int compare(String obj1, String obj2) {
                        // 降序排序
                        return obj2.compareTo(obj1);
                    }
                });
        for (UploadFileResultData uploadFileResultData:uploadFileResultDataList
             ) {
            treeMap.put(uploadFileResultData.getFileName(),uploadFileResultData.getFileUrl());

        }
        // 通过map 构建一个嵌套list  ArrayList<List<UploadFileUrlConvert>>
        ArrayList<List<UploadFileUrlConvert>> listArrayList = new ArrayList<>();
        Set<String> keySet = treeMap.keySet();
        int count = 1;

        ArrayList<UploadFileUrlConvert> innerList4Upload =  new ArrayList<>();;
        for (String key: keySet
             ) {

            if(innerList4Upload != null){
                // todo 重构list
//                uploadFileResultDataLists.add(new UploadFileResultData()
//                        .setFileName(key)
//                        .setFileUrl(treeMap.get(key)));
                UploadFileUrlConvert temp = new UploadFileUrlConvert();
                // 取得后缀

                if(key.contains("r")){
                    temp.setKey("pic");
                    System.out.println("setkey pic success!!!!!!!!!!!!!!!!");
                }else if(key.contains("e")){
                    temp.setKey("negative_pose");
                }else if(key.contains("o")){
                    temp.setKey("positive_pose");
                }else {
                    temp.setKey("pcd");
                }

                temp.setLabel("link");
                temp.setValue(treeMap.get(key));
                innerList4Upload.add(temp);
            }

            if(count % 4 == 0){
                System.out.println("innerList4Upload:" + innerList4Upload);
                listArrayList.add(innerList4Upload);
                innerList4Upload = new ArrayList<>();
                System.out.println("uploadFileResultDataArrayLists:" + innerList4Upload);
            }
            count ++;

            System.out.println(key);
        }
//        String finalFileUrl = "[" ;
//        // 拼接file url
//        for (List<UploadFileUrlConvert> arrayListUploadData:listArrayList
//             ) {
//            finalFileUrl += JSON.toJSONString(arrayListUploadData);
//            finalFileUrl += ",";
//            System.out.println(finalFileUrl);
//        }
//        finalFileUrl += ']';
//        System.out.println("finalFileUrl:" + finalFileUrl);
        singleObject.setFileType("jpg");
        singleObject.setFileUrl(JSON.toJSONString(listArrayList));
        return singleObjectMapper.insertSingleObject(singleObject);
    }

    @Override
    public SingleObject checkEntity(String objectLableName, String objectName) {
        return singleObjectMapper.checkEntity(objectLableName,objectName);
    }
}
