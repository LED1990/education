package app.cache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

@Configuration
@EnableCaching
public class EhcacheConfig {

    @Bean
    public KeyGenerator usersCacheKeygen(){
        return (Object target, Method method, Object... params) ->
                method.getName() + "_" + Arrays.toString(params);
    }

//    @Bean
//    public CacheManagerCustomizer<ConcurrentMapCacheManager> cacheManagerCustomizer() {
//        return cacheManager -> cacheManager.setAllowNullValues(false);
//    }
}
