package cn.source.system.mapper;

import cn.source.system.domain.FlowProcDefDto;

import java.util.List;

/**
 * @author Andy
 * @date 2022/10/31
 */
public interface FlowDeployMapper {
    /**
     * 流程定义列表
     * @param name
     * @return
     */
    List<FlowProcDefDto> selectDeployList(String name);
}
