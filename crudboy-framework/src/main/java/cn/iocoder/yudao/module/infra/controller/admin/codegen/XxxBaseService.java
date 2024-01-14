package cn.iocoder.yudao.module.infra.controller.admin.codegen;

import cn.iocoder.yudao.module.infra.dal.dataobject.codegen.CodegenTableDO;
import cn.iocoder.yudao.module.infra.service.codegen.BaseService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;

class XxxBaseService implements BaseService<CodegenTableDO> {

    @Override
    public boolean saveBatch(Collection<CodegenTableDO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdateBatch(Collection<CodegenTableDO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean updateBatchById(Collection<CodegenTableDO> entityList, int batchSize) {
        return false;
    }

    @Override
    public boolean saveOrUpdate(CodegenTableDO entity) {
        return false;
    }

    @Override
    public CodegenTableDO getOne(Wrapper<CodegenTableDO> queryWrapper, boolean throwEx) {
        return null;
    }

    @Override
    public Map<String, Object> getMap(Wrapper<CodegenTableDO> queryWrapper) {
        return null;
    }

    @Override
    public <V> V getObj(Wrapper<CodegenTableDO> queryWrapper, Function<? super Object, V> mapper) {
        return null;
    }

    @Override
    public BaseMapper<CodegenTableDO> getBaseMapper() {
        return null;
    }

    @Override
    public Class<CodegenTableDO> getEntityClass() {
        return null;
    }
}