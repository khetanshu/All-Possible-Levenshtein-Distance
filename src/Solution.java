import java.util.List;
import java.util.Scanner;

import com.khetanshu.datamining.algo.EditDistance;
import com.khetanshu.datamining.vo.Sequence;

/**
 * Program : Implementation to find all possible Levenshtein (or edit) sequences  
 * @author khetanshu
 * @date : April-8 -2018
 * 
 * @input : Two strings
 * @output: All possible Levenshtein (or edit) sequences
 * 
 * 
 * @test_cases
 * paris > alice 														(Expected O/P - Count 2, Operations 4)    		(Passed)
 * ab to "" 																(Expected O/P - Count 1, Operations 2)    		(Passed)	
 * abab to ab 															(Expected O/P - Count 3, Operations 2)    		(Passed)
 * gumbo > gambol 														(Expected O/P - Count 1, Operations 2)    		(Passed)
 * paris > aiytk 														(Expected O/P - Count 8, Operations 5)    		(Passed)
 * Zei > trial 															(Expected O/P - Count 1, Operations 4)    		(Passed)
 * trial > Zei  															(Expected O/P - Count 1, Operations 4)    		(Passed)
 * AGGCTATCACCTGACCTCCAGGCCGATGCCC > TAGCTATCACGACCGCGGTCGATTTGCCCGAC  	(Expected O/P - Count 216, Operations 13)    		(Passed)
 * aaaacccccggggXa > Xcccgggaaccaacc 									(Expected O/P - Count 1038, Operations 13)    	(Passed)
 * industry > interest 													(Expected O/P - Count 7, Operations 6)    		(Passed)
 * @exception_test_cases 
 * 	abc																	(Expected O/P - Exception found : Invalid Inputs)    		(Passed)
	1_ paris
	alice - 5 \17
 *  
 */

public class Solution {
	/**
	 * Entry point to the project P1
	 */
	public static void main(String[] args) {
		EditDistance obj = new EditDistance();
		String from=null;
		String to=null;
		
		Scanner scanner = new Scanner(System.in);
		try {
			while(scanner.hasNextLine()) {
				String input = scanner.nextLine();
				if(from==null||to==null) {
					if(input.matches("[A-Za-z]*")) {
						if(from==null) {
							from=input;
						}else if(to==null) {
							to=input;
						}
					}else {
						System.out.println("Exception found : Invalid Inputs");
						System.exit(1);
					}
				}else {
					System.out.println("Exception found : Invalid Inputs");
					System.exit(1);
				}

			}
		}catch(Exception e) {
			System.out.println("Invalid Input : Terminating the program due to an exception : " + e.toString());
		}finally {
			if(scanner!=null)
				scanner.close();
		}
		if(from ==null || to==null) {
			System.out.println("Exception found : Invalid Inputs");
			System.exit(1);
		}
		
		List<Sequence> sequences = obj.getEditDistances(from, to);
		/*System.out.println("\nOutputs:");*/
		/*int cnt=0;*/
		for (Sequence sequence : sequences) {
			/*System.out.print(" (~# "+ sequence.out+ " = "+ (sequence.out.toString().equals(to)?true:false) +") Result: ");*/
			System.out.println(sequence.text.toString());
			/*cnt++;*/
		}
		/*System.out.println("\nCount= "+cnt);*/
	}
	
	
}
