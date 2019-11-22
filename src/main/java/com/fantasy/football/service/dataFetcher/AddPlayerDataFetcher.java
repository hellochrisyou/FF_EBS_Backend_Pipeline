//package com.fantasy.football.service.dataFetcher;
//
//import javax.transaction.Transactional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.fantasy.football.domain.entity.League;
//import com.fantasy.football.domain.model.Dto;
//import com.fantasy.football.service.AuthorizeService;
//import com.fantasy.football.service.TeamService;
//
//import graphql.schema.DataFetcher;
//import graphql.schema.DataFetchingEnvironment;
//
//// Add Draft Player
//@Component
//public class AddPlayerDataFetcher implements DataFetcher<League> {
//	
//	@Autowired
//	private AuthorizeService authorizeService;
//	
//	@Autowired 
//	private TeamService teamService;
//
//	@Override
//	@Transactional
//	public League get(DataFetchingEnvironment dataFetchingEnvironment) {
//		this.authorizeService.authorizeBoth();		
//		Dto dto = dataFetchingEnvironment.getArgument("dto");		
//		return this.teamService.addPlayer(dto);
//	}
//}
