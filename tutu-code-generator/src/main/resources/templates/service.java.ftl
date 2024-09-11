package ${package.Service};

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${package.Mapper}.${table.mapperName};
import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import org.springframework.stereotype.Service;

/**
 * <p>
 * ${table.comment!} 服务类
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */

@Service
public class ${table.serviceName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${superServiceClass}<${entity}>   {

}

