package cn.cocoding.controller;


import cn.cocoding.config.lang.Result;
import cn.cocoding.entity.SingleObject;
import cn.cocoding.service.SingleObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/singleObject")
public class SingleObjectController {
    @Autowired
    SingleObjectService singleObjectService;

    @RequestMapping(value="/save",method= RequestMethod.POST)
    public Result saveEntity(@RequestBody SingleObject singleObject){
        System.out.println(singleObject);
        singleObject.setUserId(1);
        int result = singleObjectService.saveEntityLib(singleObject);
        System.out.println(result);
        if (result > 0) {
            return Result.succ(result);
        }
        return Result.fail(String.valueOf(result));
    }

    /**
     * 检测带有关系的实体是否存储过
     * @param objectLableName
     * @param objectName
     * @return
     */
    @RequestMapping(value="/check/{objectLableName}/{objectName}",method= RequestMethod.GET)
    public Result checkEntity(@PathVariable("objectLableName") String objectLableName,@PathVariable("objectName")String objectName){
        System.out.println(objectLableName);
        System.out.println(objectName);

        SingleObject singleObject = singleObjectService.checkEntity(objectLableName, objectName);

        if (SingleObject.isEmpty(singleObject)) {
            // 可以存储
            return Result.succ("success");
        }
        return Result.fail("fail");
    }

}
