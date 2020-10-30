package cn.cocoding.controller;


import cn.cocoding.config.lang.Result;
import cn.cocoding.entity.User;
import cn.cocoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wuhuzi
 * @since 2020-10-27
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

//    @GetMapping("/{id}")
//    public Object userId(@PathVariable("id") Integer id){
//        return userService.getById(id);
//    }


    @RequestMapping(value="/login",method=RequestMethod.POST)
    public Result login(@RequestBody User user){
        String result = userService.login(user);
        if("success".equals(result)){
           return Result.succ(result);
        }
        return Result.fail(result);
    }


}
