package com.minhaempresa.meusaas.web.mapper;

import com.minhaempresa.meusaas.core.entity.Rule;
import com.minhaempresa.meusaas.web.dto.response.RuleResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-10T16:58:36-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.8 (Homebrew)"
)
@Component
public class RuleMapperImpl implements RuleMapper {

    @Override
    public RuleResponse toResponse(Rule rule) {
        if ( rule == null ) {
            return null;
        }

        UUID id = null;
        String code = null;
        String name = null;
        String description = null;
        String module = null;

        id = rule.getId();
        code = rule.getCode();
        name = rule.getName();
        description = rule.getDescription();
        module = rule.getModule();

        RuleResponse ruleResponse = new RuleResponse( id, code, name, description, module );

        return ruleResponse;
    }

    @Override
    public List<RuleResponse> toResponse(List<Rule> rules) {
        if ( rules == null ) {
            return null;
        }

        List<RuleResponse> list = new ArrayList<RuleResponse>( rules.size() );
        for ( Rule rule : rules ) {
            list.add( toResponse( rule ) );
        }

        return list;
    }
}
