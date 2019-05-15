package exam2011;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class LeagueTableRow implements Comparable<LeagueTableRow> {

	private int points, homeGoals, awayGoals;
	private String team;
	private List<MatchResult> results = new ArrayList<>();

	public LeagueTableRow(String team, List<MatchResult> results){
		this.team = team;
		Stream resultStream = results.stream()
									.filter((result) -> result.isParticipant(team));
		this.homeGoals = resultStream.filter((result) -> result.isHomeTeam(team))
								.mapToInt((result) -> result.getGoals(team))
								.sum().orElse(0);
		this.awayGoals = resultStream.filter((result) -> result.isAwayTeam(team))
								.mapToInt((result) -> result.getGoals(team))
								.sum().orElse(0);
		this.points = resultStream.mapToInt((result) -> LeagueTableRow.getParticipantPoints(result, team))
								.sum().orElse(0);
	}

	public int getPoints(){
		return points;
	}

	public int getGoals(){

	}

	public int compareTo(LeagueTableRow o){
		if (getPoints() == o.getPoints()) {
			return 0;
		}
		return getPoints() - o.getPoints();
	}
}
