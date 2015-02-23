import java.util.*;

/*This player keeps a history of 5 deep, and then tries to anticipate the opponents 
 * next move by countering the most common move player over the past five moves.
 * 
 */

public class Player1 implements RoShamBot {

	List<Action> history = new ArrayList<Action>();
	Action[] lastFive = new Action[5];
	boolean isRandom = false; // this is set to true if our player starts losing
								// bad.
	Action lastMove = null;
	public int wins = 0, losses = 0, ties = 0, total = 0;

	public Action getNextMove(Action lastOpponentMove) {
		history.add(lastOpponentMove);
		Action move = null; //this is returned.
		
		
		
		if(!isRandom){
			lastFive = updateLastFive();
			lastFive[0] = lastOpponentMove;

			Action mostCommon = findMostCommon();
			move = findWinningMove(mostCommon);
		}
		//if we are doing poorly, switch to NashBot
		else{
	        double coinFlip = Math.random();
	        
	        if (coinFlip <= 1.0/3.0)
	            move = Action.ROCK;
	        else if (coinFlip <= 2.0/3.0)
	            move = Action.PAPER;
	        else
	            move = Action.SCISSORS;
		}
		//update our records:
		total++;
		if(isWin(lastMove, lastOpponentMove)) wins++;
		else if (isTie(lastMove, lastOpponentMove)) ties++;
		else losses++;
		//check if we need to change to random (bc we're losing too much).
		if(total>250){
			if(((double)losses/(double)total) > (1.0/3.0)){
				isRandom=true;
				//System.out.println("Now using Nash Bot!");
				
			}
		}
		lastMove = move;
		return move;

	}

	private Action[] updateLastFive() {
		Action[] temp = new Action[5];
		for (int i = 0; i < lastFive.length - 1; i++) {
			temp[i + 1] = lastFive[i];
		}
		return temp;
	}

	private Action findMostCommon() {
		int rocks = 0, papers = 0, scissors = 0;
		for (int i = 0; i < lastFive.length; i++) {
			if (lastFive[i] == Action.ROCK)
				rocks++;
			if (lastFive[i] == Action.PAPER)
				papers++;
			if (lastFive[i] == Action.SCISSORS)
				scissors++;
		}
		if (rocks > papers && rocks > scissors)
			return Action.ROCK;
		else if (papers > rocks && papers > scissors)
			return Action.PAPER;
		else
			return Action.SCISSORS;
	}

	private Action findWinningMove(Action move) {
		if (move == Action.PAPER)
			return Action.SCISSORS;
		else if (move == Action.ROCK)
			return Action.PAPER;
		else
			return Action.ROCK;
	}
	
	//returns if a beats b.
	private boolean isWin(Action a, Action b){
		if(a==Action.ROCK) {
			return (b==Action.SCISSORS);
		}
		else if (a==Action.PAPER){
			return (b==Action.ROCK);
		}
		else{
			return b==Action.PAPER;
		}
	}
	
	private boolean isTie(Action a, Action b){
		return (a==b);
	}
	
	

}
