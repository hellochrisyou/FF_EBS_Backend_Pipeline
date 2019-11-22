package com.fantasy.football.domain.model;
import java.util.Map;

import javax.validation.constraints.NotNull;

import org.dataloader.DataLoaderRegistry;
import org.springframework.validation.annotation.Validated;

import graphql.cachecontrol.CacheControl;
 
@Validated
public class GraphQLDto{

    @NotNull
    private String query;

    private  Map<String , Dto> variables;
    
    private String operationName;
    
    GraphQLDto() {}

    /**
     * @param query string
     */
    public GraphQLDto(String query) {
        super();
        this.query = query;
    }


    public String getQuery() {
        return this.query;
    }


    public void setQuery(String query) {
        this.query = query;
    }

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Map<String, Dto> getVariables() {
		return variables;
	}

	public void setVariables(Map<String, Dto> variables) {
		this.variables = variables;
	}


}