package com.example.trainline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger配置类
 *
 * @author zhangc
 * @date 2019-07-02
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * swagger2的配置
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .enable(true)
                .select()
                //接口包的路径
                .apis(RequestHandlerSelectors.basePackage("com.example.trainline.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 构建 api文档的详细信息
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //页面标题
                .title("地铁线路后台api接口")
                //创建人 名称 网址 邮箱
                .contact(new Contact("zhangc", "", "zhangc@jsyl.com.cn"))
                //版本号
                .version("1.0")
                //描述
                .description("")
                .build();
    }
}