package midtermProject;
import java.util.*;

public class codeMaker {
	/* This method generates a code that the users has to guess the right nummber and the right position of that
	 * number. A random number isgenerating between 0 and 9 and added to a list that is then returned  
	 */
	public List<String> generateCode () {
		List<String> code = new ArrayList<String>();
		Random randNum = new Random();
		for(int numOfPins = 0; numOfPins < 4; numOfPins++) {
			//Generates a random number between 0 and 10, not including 10.
			int pin = randNum.nextInt(10);
			
			if (code.contains(Integer.toString(pin))) {
				//This while loop will remember true until the random number generated is not in the code list
				while (true) {
					int newPin = randNum.nextInt(10);
					if (!code.contains(Integer.toString(newPin))) {
						code.add(Integer.toString(newPin));
						break;
					}
				}
			}
			else {
				code.add(Integer.toString(pin));
			}	
		}
		//prints the code that was generated
		 System.out.println(code);
		return code;	

	}
	
	/* This method checks to see if the users guess is in the in code maker list. If the user's guess is in 
	 *   the code list but not in the same index, the user is awarded a W, if the user guess a number that is 
	 *   in the code and the right index the user is awarded a B
	 */
	public  List<String> checker(List<String> userPrediction, List<String> code) {
		List<String> answerList = new ArrayList<String>();
		for (String pin : userPrediction) {
			if (code.contains(pin)) {
				if (code.indexOf(pin) == userPrediction.indexOf(pin)) {
					//there is a bug here when code is for ex is [1 2 3 4], a user code of [1 2 1 2] will cause a bug.
					//this is caused by the indexOf methods looking at the FIRST instance of where pin ocurres 
					//so when when the pointer is at the 2nd one in the list, the indexOf("1") will look at the first one at index 0
					answerList.add("B");
				}else {
					answerList.add("W");
				}
			}
			
		}
		return answerList;
	}
	
	/*This method checks to see if the user guess is the same as the random generated code. This is done by checking
	 * the answer list to see if there are four B's, meaning the user guess list matchs the code Maker list( number and 
	 * index wise) the user is predicted the code right and has won the game
	 */
	public String ifWon(List<String> ansList) {
		int rightIndex = 0;
		for (String ans:ansList) {
			if (ans == "B") {
				rightIndex++;
			}
		}
		if(rightIndex == 4) {
			return "true";
			}
		else {
			return "false";
		}
		//return "true";
		
	}
	
}


