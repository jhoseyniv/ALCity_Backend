package com.alcity;


import com.alcity.dto.search.ContentSearchCriteriaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.convert.RedisCustomConversions;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.Arrays;

@Configuration
@EnableCaching

public class CachingConfig {
    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private int redisPort;

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration(redisHost, redisPort);

        return new LettuceConnectionFactory(configuration);
    }

//  @Bean
//  public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
//    return RedisCacheManager.create(connectionFactory);
//  }

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration cacheConfig = myDefaultCacheConfig(Duration.ofMinutes(10)).disableCachingNullValues();

        return RedisCacheManager.builder(redisConnectionFactory())
                .cacheDefaults(cacheConfig)
                .withCacheConfiguration("JourneyCache", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getRadarChart", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("getBinaryContent", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getPuzzleGroups", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .build();

    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }
    /*
    public static String objectToString(Object object) {
        String result = "";
        if (object != null) {
            try {
                result = (new ObjectMapper()).writeValueAsString(object);
            } catch (JsonProcessingException var3) {
                JsonProcessingException e = var3;
                // catching exception if any
            }
        }
        return result;
    }

    @Bean
    public RedisCustomConversions redisCustomConversions(OffsetDateTimeToBytesConverter offsetToBytes,
                                                         BytesToOffsetDateTimeConverter bytesToOffset) {
        return new RedisCustomConversions(Arrays.asList(offsetToBytes, bytesToOffset));
    }

    @Component
    @WritingConverter
    public class OffsetDateTimeToBytesConverter implements Converter<ContentSearchCriteriaDTO, String> {
        @Override
        public String convert(final ContentSearchCriteriaDTO source) {
            return objectToString(source);
        }
    }

    @Component
    @ReadingConverter
    public class BytesToOffsetDateTimeConverter implements Converter<ContentSearchCriteriaDTO, String> {


        @Override
        public String convert(ContentSearchCriteriaDTO source) {
            return objectToString(source);
        }
    }
*/

}
