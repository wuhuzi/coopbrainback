package cn.cocoding.controller;


import cn.cocoding.config.lang.Result;
import cn.cocoding.entity.DoubleObjectData4Json;
import cn.cocoding.entity.SingleObject;
import cn.cocoding.service.DoubleObjectRelationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-30
 */
@RestController
@RequestMapping("/doubleObjectRelation")
public class DoubleObjectRelationController {
    private static final Logger logger = LoggerFactory.getLogger(DoubleObjectRelationController.class);
    @Autowired
    DoubleObjectRelationService doubleObjectRelationService;

    /**
     * 存储带有关系的实体 实体库中带有关系的实体主要是指手机内部
     * @param doubleObjectData4Json
     * @return
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public Result saveEntity2Entity(@RequestBody String doubleObjectData4Json){

        logger.info("test info");
        String result = doubleObjectRelationService.saveEntity2EntityLib(doubleObjectData4Json);

        if ("success".equals(result)) {
            return Result.succ(result);
        }
        return Result.fail(String.valueOf(result));

    }


}
