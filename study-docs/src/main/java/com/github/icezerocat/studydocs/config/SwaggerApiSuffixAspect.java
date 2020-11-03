package com.github.icezerocat.studydocs.config;

import io.swagger.models.Path;
import io.swagger.models.Swagger;
import org.aspectj.lang.annotation.AfterReturning;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Description: SwaggerApi后缀切面
 * CreateDate:  2020/10/15 10:08
 *
 * @author zero
 * @version 1.0
 */
//@Aspect
//@EnableAspectJAutoProxy
//@Component
public class SwaggerApiSuffixAspect {

    @Resource
    private SwaggerProperties swaggerProperties;

    @AfterReturning(pointcut = "execution(public io.swagger.models.Swagger springfox.documentation.swagger2.mappers.ServiceModelToSwagger2MapperImpl.mapDocumentation(..))",
            returning = "swagger")
    public void doBeforeBussinessCheck(Swagger swagger) {
        //自定义api请求，追加后缀
        String suffix = Optional.ofNullable(this.swaggerProperties)
                .map(o -> Optional.ofNullable(o.getUrlSuffix()).orElse(""))
                .orElse("");
        Map<String, Path> paths = swagger.getPaths();

        if (null != paths) {
            Map<String, Path> newPaths = new HashMap<>(paths);
            paths.clear();
            for (String oldKey : newPaths.keySet()) {
                String newKey = oldKey + suffix;
                paths.put(newKey, newPaths.get(oldKey));
            }
        }
    }

}
