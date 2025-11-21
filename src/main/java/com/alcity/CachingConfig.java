package com.alcity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

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
                .withCacheConfiguration("getBinaryContent", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getPuzzleGroups", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("all-PuzzleCategoryDTO", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("all-TemplateDTO", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("all-PLDTO", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getPuzzleLevelContents", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getFileContent", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getFileByDeviceType", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getBinaryContentByIdAndDevice", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getBinaryContentById", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getRelatedPuzzleGroupsOfACategory", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getRelatedPuzzleLevelTemplatesOfACategory", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("all-LearningSkillDTO", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getLearningSkillsByType", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getLearnSkillTreeById", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getLearningSkillByCriteria", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getJourneysByUserId", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getGamePlayByUserIdAndPuzzleLevel", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("getAllPlayGameForUser", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("getAppMemberBySearchCriteria", myDefaultCacheConfig(Duration.ofMinutes(10)))
                .withCacheConfiguration("all-PGDTO", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("all-JourneyDTO", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getLearningSKillsForPuzzleGroupById", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getXPByDate", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getXPForAppMemberBySubSetSkillAll", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getSubSkillScores", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getXPForAppMemberBySkillId", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getXPByLastWeek", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("all-AppMemberDTO", myDefaultCacheConfig(Duration.ofMinutes(30)))
                .withCacheConfiguration("getAvatarById", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getAvatarByUserName", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getPublicPuzzleLevels", myDefaultCacheConfig(Duration.ofMinutes(50)))
                .withCacheConfiguration("getPLNotPlayedByMember", myDefaultCacheConfig(Duration.ofMinutes(50)))

                .build();

    }

    private RedisCacheConfiguration myDefaultCacheConfig(Duration duration) {
        return RedisCacheConfiguration
                .defaultCacheConfig()
                .entryTtl(duration)
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }


    /*
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
