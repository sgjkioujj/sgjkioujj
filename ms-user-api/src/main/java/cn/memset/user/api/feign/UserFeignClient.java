package cn.memset.user.api.feign;



import cn.memset.user.api.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name = "ms-user",
        url = "${cnmemset.ms-user.url:127.0.0.1:8080}",
        fallbackFactory = UserFeignClient.UserFallbackFactory.class)
public interface UserFeignClient {
    @GetMapping("/get/{userId}")
    @ResponseBody
    public User getUserById(@PathVariable("userId") long userId);

    @Component
    public static class UserFallbackFactory implements FallbackFactory<UserFeignClient> {
        private final Logger logger = LoggerFactory.getLogger(UserFallbackFactory.class);

        @Override
        public UserFeignClient create(Throwable cause) {
            return new UserFeignClient() {
                @Override
                public User getUserById(long userId) {
                    logger.error("调用ms-user发生异常：", cause);
                    return null;
                }
            };
        }
    }
}


