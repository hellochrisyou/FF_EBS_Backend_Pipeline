package com.fantasy.football.graphQL;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.domain.model.GraphQLDto;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import graphql.servlet.core.internal.GraphQLRequest;

@CrossOrigin(maxAge = 3600)
@RestController
public class GraphQLQueryController {

	private final Logger log = LoggerFactory.getLogger(GraphQLQueryController.class);

	@Autowired
	GraphQLProvider graphQLProvider;

	@CrossOrigin()
	@PostMapping(value = "/graphql")
	public ResponseEntity<Object> mutateGraphQL(@RequestBody GraphQLRequest graphQLRequest) {
		
		ExecutionInput executionInput = ExecutionInput.newExecutionInput()
				.query(graphQLRequest.getQuery())
				.variables(graphQLRequest.getVariables())
				.operationName(graphQLRequest.getOperationName())
				.build();
		ExecutionResult executionResult = graphQLProvider.graphQL().execute(executionInput);
		Object data = executionResult.getData();
		List<GraphQLError> errors = executionResult.getErrors();
		return ResponseEntity.ok(data);
	}
}
