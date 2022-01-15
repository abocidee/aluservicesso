package com.alu.itoken.service.sso.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class EduConfig {


        /**
         * 乐观锁配置
         * @return
         */
        @Bean
        public OptimisticLockerInterceptor optimisticLockerInterceptor(){
            return new OptimisticLockerInterceptor();
        }

        /**
         * 分页配置
         * @return
         */
        @Bean
        public PaginationInterceptor paginationInterceptor(){
            return new PaginationInterceptor();
        }



        /**
         * 逻辑删除
         * @return
         */
        @Bean
        public ISqlInjector iSqlInjector(){
            return new LogicSqlInjector();
        }

        /**
         * SQL执行效率插件
         */
        @Bean
        @Profile({"dev"})// 设置 dev test 环境开启
        public PerformanceInterceptor performanceInterceptor() {


            PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
            performanceInterceptor.setMaxTime(100);
            performanceInterceptor.setFormat(true);
            return performanceInterceptor;
        }
//
//        @Bean
//    public JavaMailSender getJavaMailsender(){
//            return new JavaMailSenderImpl();
//        }
        @Bean
        public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        	return (factory) -> factory.addContextCustomizers(
        			(context) -> context.setCookieProcessor(new LegacyCookieProcessor()));
        }

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);


        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<Object, Object> template = new RedisTemplate<Object, Object>();
        template.setConnectionFactory(redisConnectionFactory);

        //template.setKeySerializer(jackson2JsonRedisSerializer);
        //使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());

        //template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;

    }
    }
