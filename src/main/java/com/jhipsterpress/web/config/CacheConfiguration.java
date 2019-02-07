package com.jhipsterpress.web.config;

import java.time.Duration;

import org.ehcache.config.builders.*;
import org.ehcache.jsr107.Eh107Configuration;

import io.github.jhipster.config.jcache.BeanClassLoaderAwareJCacheRegionFactory;
import io.github.jhipster.config.JHipsterProperties;

import org.springframework.boot.autoconfigure.cache.JCacheManagerCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;

@Configuration
@EnableCaching
public class CacheConfiguration {

    private final javax.cache.configuration.Configuration<Object, Object> jcacheConfiguration;

    public CacheConfiguration(JHipsterProperties jHipsterProperties) {
        BeanClassLoaderAwareJCacheRegionFactory.setBeanClassLoader(this.getClass().getClassLoader());
        JHipsterProperties.Cache.Ehcache ehcache =
            jHipsterProperties.getCache().getEhcache();

        jcacheConfiguration = Eh107Configuration.fromEhcacheCacheConfiguration(
            CacheConfigurationBuilder.newCacheConfigurationBuilder(Object.class, Object.class,
                ResourcePoolsBuilder.heap(ehcache.getMaxEntries()))
                .withExpiry(ExpiryPolicyBuilder.timeToLiveExpiration(Duration.ofSeconds(ehcache.getTimeToLiveSeconds())))
                .build());
    }

    @Bean
    public JCacheManagerCustomizer cacheManagerCustomizer() {
        return cm -> {
            cm.createCache(com.jhipsterpress.web.repository.UserRepository.USERS_BY_LOGIN_CACHE, jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.repository.UserRepository.USERS_BY_EMAIL_CACHE, jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.User.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Authority.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.User.class.getName() + ".authorities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.PersistentToken.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.User.class.getName() + ".persistentTokens", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Blog.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Blog.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Post.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Post.class.getName() + ".comments", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Post.class.getName() + ".tags", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Post.class.getName() + ".topics", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Topic.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Topic.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Tag.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Tag.class.getName() + ".posts", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Comment.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cmessage.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Message.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Notification.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Uprofile.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Uprofile.class.getName() + ".interests", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Uprofile.class.getName() + ".activities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Uprofile.class.getName() + ".celebs", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".blogs", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".csenders", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".creceivers", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cfolloweds", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cfollowings", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cblockedusers", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cblockingusers", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".calbums", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cinterests", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".cactivities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Community.class.getName() + ".ccelebs", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Follow.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Blockuser.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Album.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Album.class.getName() + ".photos", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Calbum.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Calbum.class.getName() + ".photos", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Photo.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Interest.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Interest.class.getName() + ".uprofiles", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Activity.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Activity.class.getName() + ".uprofiles", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Celeb.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Celeb.class.getName() + ".uprofiles", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cinterest.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cinterest.class.getName() + ".communities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cactivity.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cactivity.class.getName() + ".communities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cceleb.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Cceleb.class.getName() + ".communities", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Urllink.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Frontpageconfig.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vtopic.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vtopic.class.getName() + ".vquestions", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vquestion.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vquestion.class.getName() + ".vanswers", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vquestion.class.getName() + ".vthumbs", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vanswer.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vanswer.class.getName() + ".vthumbs", jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Vthumb.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Newsletter.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.Feedback.class.getName(), jcacheConfiguration);
            cm.createCache(com.jhipsterpress.web.domain.ConfigVariables.class.getName(), jcacheConfiguration);
            // jhipster-needle-ehcache-add-entry
        };
    }
}
