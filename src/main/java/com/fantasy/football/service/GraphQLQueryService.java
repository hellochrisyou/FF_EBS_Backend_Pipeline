package com.fantasy.football.service;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fantasy.football.dao.repository.AccountRepository;
import com.fantasy.football.dao.repository.LeagueRepository;
import com.fantasy.football.service.dataFetcher.AccountDataFetcher;
import com.fantasy.football.service.dataFetcher.AllLeaguesDataFetcher;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@Service
public class GraphQLQueryService {
//    private static Logger logger = LoggerFactory.getLogger(GraphQLService.class);
//    
//    @Autowired
//	private AccountRepository accountRepository;
//    
//    @Autowired
//	private LeagueRepository leagueRepository;
//	
//    @Value("classpath:account.graphql")
//    Resource resource;
//    
//    private GraphQL graphQL;
//    
//    @Autowired
//    private AccountDataFetcher accountDataFetcher;
//
//    @Autowired
//    private AllLeaguesDataFetcher allLeaguesDataFetcher;
//
//    // load schema at application start up
//    @PostConstruct
//    private void loadSchema() throws IOException {        
//        // get the schema
//        File schemaFile = resource.getFile();
//        // parse schema
//        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
//        RuntimeWiring wiring = buildRuntimeWiring();
//        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, wiring);
//        graphQL = GraphQL.newGraphQL(schema).build();
//    }
//    
//    private RuntimeWiring buildRuntimeWiring() {
//  	  return RuntimeWiring.newRuntimeWiring()
//                .type("Query", typeWiring -> typeWiring
//                		.dataFetcher("account", accountDataFetcher)
//                		.dataFetcher("allLeagues", allLeaguesDataFetcher))
//                .build();
//    }
//
//    public GraphQL getGraphQL(){
//        return graphQL;
//    }
}