package com.funo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Properties;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    //@Bean
    //public Docket docket(){
        //Profiles profiles=Profiles.of("dev","test");
         //boolean b = environment.acceptsProfiles(profiles);

        //匿名内部类
       // return  new Docket(DocumentationType.SWAGGER_2);//ui界面
               // .apiInfo(apiInfo())//swagger配置信息
                //.groupName("")
                //.enable(b)
                //.select()//接口信息
                //.apis(RequestHandlerSelectors.basePackage())//接口包扫描
                //.paths(PathSelectors.ant(""))




        /**
         * RequestHandlerSelectors
         * 扫描全部 any  全部不扫描none
         * 扫描类上的注解  withMethod/ClassAnnotation  还有方法上的注解
         *paths 过率路径
         *
         */
   // }

    /**
     *     private final String version;
     *     private final String title;
     *     private final String description;
     *     private final String termsOfServiceUrl;
     *     private final String license;
     *     private final String licenseUrl;
     *     private final Contact contact;
     *     private final List<VendorExtension> vendorExtensions;
     *
     *
     *public ApiInfo apiInfo(){
     *         return  new ApiInfo();
     *
     *
     *
     *     }
     *
     *

     * @return
     */

}
