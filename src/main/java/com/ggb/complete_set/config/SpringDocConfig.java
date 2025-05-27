package com.ggb.complete_set.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

//@Configuration 注解的作用是标识一个类为配置类，
// 它告诉 Spring 这个类包含了一个或多个 @Bean 方法的定义，
// Spring 容器会使用这些方法来创建和管理 Bean，
// 这个注解通常与 @Bean 注解一起使用，
// 用于替代传统的 XML 配置文件，实现基于 Java 的配置方式。
@Configuration
//@OpenAPIDefinition 注解用于定义 Swagger/OpenAPI 文档的基本信息，
// 包括 API 的标题、描述、版本、联系人信息等，
// 它通常用在配置类中，用于自定义 API 文档的元数据，
// 使文档更加清晰和易于理解。
@OpenAPIDefinition(
        info = @Info(
                title = "您的项目API文档", // API文档标题
                description = "这是一个演示Spring Boot后端API的文档。", // API描述
                version = "1.0", // API版本
                contact = @Contact(
                        name = "您的名字", // 联系人
                        email = "your.email@example.com", // 联系邮箱
                        url = "https://yourwebsite.com" // 联系URL
                ),
                license = @License(
                        name = "Apache 2.0", // 许可证
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "开发环境") // 服务器URL
                // @Server(url = "https://api.yourdomain.com", description = "生产环境")
        }
)
@SecurityScheme( // 配置JWT认证方式
        name = "bearerAuth", // 安全方案的名称，后续controller中引用
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
//配置 Swagger/OpenAPI 文档的主要作用是为后端 API 接口自动生成交互式文档，
// 开发人员可以通过这个文档直观地查看所有接口的详细信息（包括请求方法、参数、响应格式等），
// 并且可以直接在文档页面上测试接口，大大提高了前后端协作效率，
// 同时也方便了接口的维护和测试。
public class SpringDocConfig {

}
