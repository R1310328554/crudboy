package cn.iocoder.yudao.module.infra.controller.admin.codegen;

import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.infra.dal.dataobject.codegen.CodegenTableDO;
import cn.iocoder.yudao.module.infra.service.codegen.BaseController;
import cn.iocoder.yudao.module.infra.service.codegen.BaseService;
import cn.iocoder.yudao.module.infra.service.codegen.BaseServiceImpl;
import cn.iocoder.yudao.module.infra.service.codegen.CodegenService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

@RestController
@RequestMapping("/infra/codegenTable")
@Validated
public class CodegenTableController extends BaseController<AABaseService, CodegenTableDO> {

    @Resource
    private CodegenService codegenService;

}

@Component
class AABaseService extends BaseServiceImpl<MaaBaseMapper, CodegenTableDO> {

}

@Mapper
interface MaaBaseMapper extends BaseMapperX<CodegenTableDO> {

}
