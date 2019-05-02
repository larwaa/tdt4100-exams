package exam2011;

import java.util.HashMap;
import java.util.Map;

public class MatchResult {

	private final String homeTeam, awayTeam;
	private int homeGoals, awayGoals;

	public MatchResult(String homeTeam, String awayTeam){
		this.homeTeam = homeTeam;
		this.awayTeam = awayTeam;

	}

	public boolean isHomeTeam(String participant){
		if (!isParticipant(participant)){
			throw new IllegalArgumentException("Not a participant");
		}
		return participant.equals(getHomeTeam());
	}

	private boolean isAwayTeam(String participant){
		if (!isParticipant(participant)){
			throw new IllegalArgumentException("Not a participant");
		}
		return participant.equals(getAwayTeam());
	}

	public void setGoals(String participant, int goals){
		if (isHomeTeam(participant)){
			homeGoals = goals;
		} else if (isAwayTeam(participant)){
			awayGoals = goals;
		} else {
			throw new IllegalArgumentException(participant + " is not a participant.");
		}
	}

	public int getGoals(String participant){
		if (isHomeTeam(participant)){
			return homeGoals;
		} else if (isAwayTeam(participant)){
			return awayGoals;
		} else {
			throw new IllegalArgumentException(participant + " is not a participant.");
		}
	}

	public String getHomeTeam(){
		return homeTeam;
	}

	public String getAwayTeam(){
		return awayTeam;
	}

	public boolean isParticipant(String participant){
		return participant.equals(homeTeam) || participant.equals(awayTeam);
	}

	public boolean isDraw(){
		return getGoals(getAwayTeam()) == getGoals(getHomeTeam());
	}

	public boolean isWinner(String participant){
		boolean homeWin = getGoals(getHomeTeam()) > getGoals(getAwayTeam());
		return isHomeTeam(participant) ? homeWin : ! homeWin;
	}
}
