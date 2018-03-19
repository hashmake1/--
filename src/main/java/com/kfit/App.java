package com.kfit;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
//其中@SpringBootApplication申明让spring boot自动给程序进行必要的配置，等价于以默认属性使用@Configuration，@EnableAutoConfiguration和@ComponentScan
@SpringBootApplication
public class App {
	
	@Bean  
    public MultipartConfigElement multipartConfigElement() {  
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //// 设置文件大小限制 ,超了，页面会抛出异常信息，这时候就需要进行异常信息的处理了;
        factory.setMaxFileSize("512KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("10MB");  
        //Sets the directory location where files will be stored.
       // factory.setLocation("http://localhost:8080/upload");
        return factory.createMultipartConfig();  
    }  
	
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
