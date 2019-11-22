package com.fantasy.football.graphQL;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.domain.model.GraphQLDto;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQLError;

@CrossOrigin(maxAge = 3600)
@RestController
public class GraphQLQueryController {

	private final Logger log = LoggerFactory.getLogger(GraphQLQueryController.class);

	@Autowired
	GraphQLProvider graphQLProvider;

	@CrossOrigin()
	@PostMapping(value = "/graphql", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> mutateGraphQL(@RequestBody @Valid final GraphQLDto queryRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dto", queryRequest.getVariables().get("variables"));
		ExecutionInput executionInput = ExecutionInput.newExecutionInput().query(queryRequest.getQuery()).variables(map)
				.build();
		ExecutionResult executionResult = graphQLProvider.graphQL().execute(executionInput);
		Object data = executionResult.getData();
		List<GraphQLError> errors = executionResult.getErrors();
//	    [graphql.schema.CoercingParseValueException: Variable 'dto' has an invalid value. Expected type 'Map' but was 'Dto'. Variables for input objects must be an instance of type 'Map'.]
		return ResponseEntity.ok(data);
	}
}
