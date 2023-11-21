package cn.memset.user.web;

import cn.memset.user.api.dto.User;
import cn.memset.user.api.feign.UserFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class controllers implements UserFeignClient {

    private static final Map<Long, User> users = new HashMap<>();

    static {
        users.put(10000L, new User(10000L, "张三"));
        users.put(10001L, new User(10001L, "李四"));
    }

    @Override
        @GetMapping("/get/{userId}")
    public User getUserById(@PathVariable("userId") long userId) {
        return users.get(userId);
    }
}

