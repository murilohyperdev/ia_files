package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.web.dto.response.RuleResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RuleMapper {

    RuleResponse toResponse(Rule rule);

    List<RuleResponse> toResponse(List<Rule> rules);
}
