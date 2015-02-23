//does mixed strategy, but changes which one. used for offensive attacks.
public class Mixed1 implements RoShamBot {
	int moves = 0;
	
    public Action getNextMove(Action lastOpponentMove) {
        moves ++;
        
    	double coinFlip = Math.random();
        
    	if(moves%3==0){
	        if (coinFlip <= 0.5)
	            return Action.ROCK;
	        else
	            return Action.PAPER;
    	}
    	else if (moves%3==1){
    	     if (coinFlip <= 0.5)
 	            return Action.ROCK;
 	        else
 	            return Action.SCISSORS;
    	}
    	else{
      	     if (coinFlip <= 0.5)
  	            return Action.PAPER;
  	        else
  	            return Action.SCISSORS;
    	}
    }
	
}
