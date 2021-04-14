package com.zn.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfig {
    public CacheConfig() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }


//    @Bean
//    public Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer() {
//        final Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
//        final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder
//                .json().build();
//        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
//        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
//        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);
//        return jackson2JsonRedisSerializer;
//    }
//
//
//    @Bean
//    public CacheManager redisCacheManager() {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate());
//
//        SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
//        simpleCacheManager.setCaches(Arrays.asList());
//        return simpleCacheManager;
//    }
}
