package pers.hfl.hflblog.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName SwaggerConfig
 * @Author HFL
 * @Date 2020/10/23/023
 * @Version V1.0
 **/
@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment) {
        // 如果在dev环境（开发环境）就开启Swagger
        boolean isDev = environment.acceptsProfiles(Profiles.of("dev"));
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //分组操作
                .groupName("默认接口")
                .enable(isDev)
                .select()
                //指定要扫描的包
                .apis(RequestHandlerSelectors.basePackage("pers.hfl.hflblog.controller"))
                //过滤的路径
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置Swagger的ApiInfo
     *
     * @return API配置信息
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("HFL博客后端接口文档")
                .description("通过此文档可以查看、测试后端api")
                .contact(new Contact("HFL", "https://gitee.com/hflsc30", "hflsc30@163.com"))
                .version("v1.0.0")
                .build();
    }
}
