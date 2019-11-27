package com.fantasy.football.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.fantasy.football.service.serviceImpl.AccountServiceImpl;
import com.fantasy.football.service.serviceImpl.LeagueServiceImpl;
import com.fantasy.football.service.serviceImpl.TeamServiceImpl;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import io.leangen.graphql.GraphQLSchemaGenerator;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;

@CrossOrigin(maxAge = 3600)
@RestController
@GraphQLApi
public class GraphQLQueryController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphQLQueryController.class);
   
    private final GraphQL graphQL;
	
		@Autowired
	    public GraphQLQueryController(AccountServiceImpl accountService, LeagueServiceImpl leagueService, TeamServiceImpl teamService) 
	 	{			
	        //Schema generated from query classes
			GraphQLSchema schema = new GraphQLSchemaGenerator()
	                .withBasePackages("com.fantasy.football")
	                .withOperationsFromSingleton(accountService, AccountServiceImpl.class)
	                .withOperationsFromSingleton(leagueService, LeagueServiceImpl.class)
	                .withOperationsFromSingleton(teamService, TeamServiceImpl.class)	        
	                .generate();
	        graphQL = GraphQL.newGraphQL(schema).build();
	        LOGGER.info("Generated GraphQL schema using SPQR");
	    }	
}
