package com.fantasy.football.graphQL;

import java.io.IOException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Component
public class GraphQLProvider {

	@Autowired
	GraphQLDataFetchers graphQLDataFetchers;

	private GraphQL graphQL;

	@PostConstruct
	public void init() throws IOException {
		URL url = Resources.getResource("account.graphqls");
		String sdl = Resources.toString(url, Charsets.UTF_8);
		GraphQLSchema graphQLSchema = buildSchema(sdl);
		this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
	}

	private GraphQLSchema buildSchema(String sdl) {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
		RuntimeWiring runtimeWiring = buildWiring();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	private RuntimeWiring buildWiring() {
		return RuntimeWiring.newRuntimeWiring().type(newTypeWiring("Mutation")
				.dataFetcher("addPlayer", graphQLDataFetchers.addPlayer())
				.dataFetcher("addWaiver", graphQLDataFetchers.addWaiver())
				.dataFetcher("createLeague", graphQLDataFetchers.createLeague())
				.dataFetcher("createTeam", graphQLDataFetchers.createTeam())
				.dataFetcher("tradeTeam", graphQLDataFetchers.tradeTeam())
				.dataFetcher("togglePlayer", graphQLDataFetchers.togglePlayer())
				.dataFetcher("getLeagues", graphQLDataFetchers.getLeagues())
				.dataFetcher("getLeagues", graphQLDataFetchers.getLeague())
				.dataFetcher("accountAuthenticate", graphQLDataFetchers.accountAuthenticate())
				.dataFetcher("register", graphQLDataFetchers.register()))
				.build();

	}

	@Bean
	public GraphQL graphQL() {
		return graphQL;
	}

}