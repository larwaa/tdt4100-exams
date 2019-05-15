package exam2011;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class LeagueTable {

	private final List<String> teams;
	private Collection<MatchResult> matches = new ArrayList<>();

	public LeagueTable(String...teams){
		this.teams = Arrays.asList(teams);
	}

	public void addMatch(MatchResult matchResult){
		if (! isParticipants(matchResult.getHomeTeam(), matchResult.getAwayTeam())){
			throw new IllegalArgumentException("Not participants");
		}
		matches.add(matchResult);
	}

	private boolean isParticipants(String homeTeam, String awayTeam){
		return teams.contains(homeTeam) && teams.contains(awayTeam);
	}

	public static int getParticipantPoints(MatchResult matchResult, String participant){
		if (matchResult.isParticipant(participant)){
			if (matchResult.isWinner(participant)){
				return 3;
			}
			else if (matchResult.isDraw()){
				return 1;
			}
			else {
				return 0;
			}
		}
		else {
			return 0;
		}
	}
}
