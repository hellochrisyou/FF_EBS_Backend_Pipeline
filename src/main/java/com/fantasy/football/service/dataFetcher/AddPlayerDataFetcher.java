package com.fantasy.football.service.dataFetcher;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fantasy.football.domain.Roster;
import com.fantasy.football.domain.entity.League;
import com.fantasy.football.domain.entity.Player;
import com.fantasy.football.domain.model.Dto;
import com.fantasy.football.repository.LeagueRepository;
import com.fantasy.football.service.AuthorizeService;

import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;

// Add Draft Player
@Component
public class AddPlayerDataFetcher implements DataFetcher<League> {

	@Autowired
	private LeagueRepository leagueRepository;
	
	@Autowired
	private AuthorizeService authorizeService; 
	
	@Override
	@Transactional
	public League get(DataFetchingEnvironment dataFetchingEnvironment) {
		
		this.authorizeService.authorizeBoth();
		
		Dto dto= dataFetchingEnvironment.getArgument("dto");

		League repoLeague = new League();
		Player newPlayer = new Player(dto.getPlayer1());
		
		repoLeague = this.leagueRepository.findByLeagueName(dto.getMyLeagueName());
		Roster roster = new Roster(repoLeague.getTeam(dto.getMyTeamName()).getPlayers());
		
		if (roster.checkPosition(newPlayer.getPosition())) {
			newPlayer.setActive(true);
		} else {
			newPlayer.setActive(false);
		}		
		 
		repoLeague.getTeam(dto.getMyTeamName()).addPlayer(newPlayer);	
		newPlayer.addTeam(repoLeague.getTeam(dto.getMyTeamName()));		
		
		if (repoLeague.getDraftOrder() == 10 && repoLeague.getTeam(dto.getMyTeamName()).getPlayers().size() == 16) {
			repoLeague.setStatus("Ongoing");
		} else { 
			repoLeague.setDraftOrder(repoLeague.getDraftOrder()+1);
		}		
		
		return this.leagueRepository.save(repoLeague);
	 }
}
