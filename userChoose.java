package midtermProject;
//import midtermProject.broad;
import java.util.Scanner;
import java.util.*;

	
public class userChoose {
	
	// This method gets the user inputs, and is assigned to the String variable pin, and that String is returned 
	public String userChooses() {
		Scanner PinChooses = new Scanner(System.in);
		System.out.println("Please enter four numbers, with a space inbetween each of the numbers" );
		String pins = PinChooses.nextLine().trim();
		
		return pins;
		}
	
	/*This method takes the user input and parsing it using white spaces as a delimiter.
	*when finished parsing the string it will turn into a list of the type String.
	*There is also a checker to see how many numbers are in the list. If the numbers 
	*entered are greater of less than 4 this method will get called again with the parameter returned 
	*from the userChoose
	*/
	public List<String> getPins(String pins) {
		List<String> userChooses = Arrays.asList(pins.split(" "));
		int listSize = userChooses.size();
		if (listSize> 4 || listSize < 4) {
			System.out.println("The number of pins is greater than or less than 4. Please try again");
			// This line will run the userChooses method agin asking for pins, and take that string and 
			//uses it as a parameter in getPins 
			getPins(userChooses());	
			}
		//System.out.println("this is the size of the list" + userChooses.size());
		return userChooses;
	}
	
	//This method checks to see is the user inputs are digits
	public static Boolean digitChecker(List<String> userChooses) {
		for (String chooses: userChooses) {
			//"-?\\d+(\\.\\d+)?" is the regex used to find a number 
			if (!chooses.matches("-?\\d+(\\.\\d+)?")) {
				System.out.println("You entered something that is not a number, Please try again");
				return false;
			}
		}
		return true;
	}
	
	//This method checks to see if the user has enter multiple ofthe same numbers in his guess
	public static Boolean repeatDigits (List<String> userChooses) {
		System.out.println(userChooses);
		for (String digits : userChooses) {
			//Collections.frequency() counts the numbers of time a specific number is in our user guess list.
			
			int occurrences = Collections.frequency(userChooses, digits);
			if(occurrences > 1){
				System.out.println("You have repeating numbers");
				//userChooses.add(digits);
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		codeMaker codeMaker = new codeMaker();
		userChoose currentUser = new userChoose();
		List<String> code = codeMaker.generateCode();
		List<String> codeChecker;
		List<String> chooses;
		//LinkedHashMap is used b/c the order the elements are added is kept unlike hashmap
		Map<List<String>,List<String>> combos = new LinkedHashMap<List<String>,List<String>>();
		String pins;
		int trysLeft = 10;
		boolean numbersAreDigits;
		boolean repeatingDigits;
		
		
		while (trysLeft > 0){
		/*This do while loop will continue keep asking the user for another guess until the user enters 
		 * a guess that 1) doesnt have repeating digits in it and 2) that the users guesses are actual 
		 * numbers and not letters,etc. 
		 */
			do {
			pins = currentUser.userChooses();
			chooses = currentUser.getPins(pins);
			numbersAreDigits = digitChecker(chooses);
			
			repeatingDigits = repeatDigits(chooses);
			} while ((repeatingDigits == true) || (numbersAreDigits == false )); //|| ());
			
			
			codeChecker = codeMaker.checker(chooses,code);
			String didYouWin = codeMaker.ifWon(codeChecker);
			//System.out.println(didYouWin);
			System.out.println(codeChecker);
			if (didYouWin == "true") {
				System.out.print("You guessed the right code, the code was " + code);
				//System.out.println(codeChecker);
				break;
			}
			else {
				trysLeft--;
				System.out.println("you have "+ (trysLeft)+" trys left") ;
				if (trysLeft == 0){
					System.out.println("You were unable to find the code, the code was "+ code);
				}
			}
			/*combos is a HashMap that is key:value data structure. I am using this to link the key (user guess) to 
			 * the value (the respond list explained in codeMaker class) so the user can look at previous guess and 
			 * answers. */
			combos.put(chooses, codeChecker);
			int row = 1;
			for (List<String> choose: combos.keySet()) {
					
				System.out.print(row + " ");
				System.out.print(choose + " ");
				System.out.println(combos.get(choose));
				row++;
			}
			//System.out.println(codeChecker);
			
		}
		
	}
}

