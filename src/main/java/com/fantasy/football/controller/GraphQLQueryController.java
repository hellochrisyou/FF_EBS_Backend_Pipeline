package com.fantasy.football.controller;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.service.dataFetcher.AccountDataFetcher;
import com.fantasy.football.service.dataFetcher.AddPlayerDataFetcher;
import com.fantasy.football.service.dataFetcher.AddWaiverDataFetcher;
import com.fantasy.football.service.dataFetcher.AllLeaguesDataFetcher;
import com.fantasy.football.service.dataFetcher.CreateLeagueDataFetcher;
import com.fantasy.football.service.dataFetcher.CreateTeamDataFetcher;
import com.fantasy.football.service.dataFetcher.TogglePlayerDataFetcher;
import com.fantasy.football.service.dataFetcher.TradeTeamDataFetcher;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
public class GraphQLQueryController {
	
	@Autowired
    private AccountDataFetcher accountDataFetcher;

    @Autowired
    private AddPlayerDataFetcher addPlayerDataFetcher;

    @Autowired
    private AddWaiverDataFetcher addWaiverDataFetcher;
    
    @Autowired
    private AllLeaguesDataFetcher allLeaguesDataFetcher;
    
    @Autowired
    private CreateLeagueDataFetcher createLeagueDataFetcher;
    
    @Autowired
    private CreateTeamDataFetcher createTeamDataFetcher;
    
    @Autowired
    private TogglePlayerDataFetcher togglePlayerDataFetcher;
    
    @Autowired
    private TradeTeamDataFetcher tradeTeamDataFetcher;
    
    private GraphQL graphQL;

    @PostConstruct
    public void loadSchema() throws IOException{
        Resource schemaResource = new ClassPathResource("/account.graphqls");
        TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(new InputStreamReader(schemaResource.getInputStream()));
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    private RuntimeWiring buildRuntimeWiring(){
        return RuntimeWiring.newRuntimeWiring().type(("Query"), typeWiring -> typeWiring
                .dataFetcher("account", accountDataFetcher)                
                .dataFetcher("allLeagues", allLeaguesDataFetcher)
                .dataFetcher("createLeague", createLeagueDataFetcher)
                .dataFetcher("createTeam", createTeamDataFetcher)
                .dataFetcher("tradeTeam", tradeTeamDataFetcher)
                .dataFetcher("addWaiver", addWaiverDataFetcher)
                .dataFetcher("togglePlayer", togglePlayerDataFetcher)
                .dataFetcher("addPlayer", addPlayerDataFetcher))
                .build();
    }
    
    @PostMapping(value = "/graphql")
    public ResponseEntity<Object> graphQLQuery(Dto mutation){
    	System.out.println("mutation");
        ExecutionResult result = graphQL.execute(mutation.getOtherTeamName());
        if(!result.getErrors().isEmpty()){
            return ResponseEntity.ok(result.getErrors());
        }
        return ResponseEntity.ok(result.getData());
    }
    
    @PostMapping(value = "/mutate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> mutate(@RequestBody String mutation){
        ExecutionResult result = graphQL.execute(mutation);
        if(!result.getErrors().isEmpty()){
            return ResponseEntity.ok(result.getErrors());
        }
        return ResponseEntity.ok(result.getData());
    }
}

