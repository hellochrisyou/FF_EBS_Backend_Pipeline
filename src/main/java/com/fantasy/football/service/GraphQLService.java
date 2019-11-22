//package com.fantasy.football.service;
//
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import javax.annotation.PostConstruct;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.stereotype.Service;
//
//import com.fantasy.football.service.dataFetcher.AddPlayerDataFetcher;
//import com.fantasy.football.service.dataFetcher.AddWaiverDataFetcher;
//import com.fantasy.football.service.dataFetcher.AllLeaguesDataFetcher;
//import com.fantasy.football.service.dataFetcher.CreateLeagueDataFetcher;
//import com.fantasy.football.service.dataFetcher.CreateTeamDataFetcher;
//import com.fantasy.football.service.dataFetcher.TogglePlayerDataFetcher;
//import com.fantasy.football.service.dataFetcher.TradeTeamDataFetcher;
//
//import graphql.GraphQL;
//import graphql.schema.GraphQLSchema;
//import graphql.schema.idl.RuntimeWiring;
//import graphql.schema.idl.SchemaGenerator;
//import graphql.schema.idl.SchemaParser;
//import graphql.schema.idl.TypeDefinitionRegistry;
//
//@Service
//public class GraphQLService {
//    private static Logger logger = LoggerFactory.getLogger(GraphQLService.class);
//    private GraphQL graphQL;
//
//    @Autowired
//    private AddPlayerDataFetcher addPlayerDataFetcher;
//
//    @Autowired
//    private AddWaiverDataFetcher addWaiverDataFetcher;
//    
//    @Autowired
//    private AllLeaguesDataFetcher allLeaguesDataFetcher;
//    
//    @Autowired
//    private CreateLeagueDataFetcher createLeagueDataFetcher;
//    
//    @Autowired
//    private CreateTeamDataFetcher createTeamDataFetcher;
//    
//    @Autowired
//    private TogglePlayerDataFetcher togglePlayerDataFetcher;
//    
//    @Autowired
//    private TradeTeamDataFetcher tradeTeamDataFetcher;
//    
//  @PostConstruct
//  public void loadSchema() throws IOException{
//      Resource schemaResource = new ClassPathResource("/account.graphqls");
//      TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(new InputStreamReader(schemaResource.getInputStream()));
//      RuntimeWiring runtimeWiring = buildRuntimeWiring();
//      GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry,runtimeWiring);
//      graphQL = GraphQL.newGraphQL(graphQLSchema).build();
//  }
//
//  private RuntimeWiring buildRuntimeWiring(){
//      return RuntimeWiring.newRuntimeWiring().type(("Mutation"), typeWiring -> typeWiring
////              .dataFetcher("account", accountDataFetcher)                
//              .dataFetcher("allLeagues", allLeaguesDataFetcher)
//              .dataFetcher("createLeague", createLeagueDataFetcher)
//              .dataFetcher("createTeam", createTeamDataFetcher)
//              .dataFetcher("tradeTeam", tradeTeamDataFetcher)
//              .dataFetcher("addWaiver", addWaiverDataFetcher)
//              .dataFetcher("togglePlayer", togglePlayerDataFetcher)
//              .dataFetcher("addPlayer", addPlayerDataFetcher))
//              .build();
//  }
//
//    public GraphQL getGraphQL(){
//        return graphQL;
//    }
//}
