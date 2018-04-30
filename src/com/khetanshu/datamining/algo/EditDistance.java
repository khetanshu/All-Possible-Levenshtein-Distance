package com.khetanshu.datamining.algo;

import java.util.LinkedList;
import java.util.List;

import com.khetanshu.datamining.vo.Sequence;

/**
 * @author khetanshu
 * This is the functional class which exposes the functionality to get all the optimal edit sequences of the strings
 */
public class EditDistance {
	/**
	 * This is the public data function which would provide the all the optimal sequences with which "from" string can be converted to "to" string
	 * @param from : value of the string that needs to be converted
	 * @param to : value of the string that the "from" string need to be converted to
	 * @return : list of all the optimal sequences with which "from" string can be converted to "to" string
	 */
	public List<Sequence> getEditDistances(String from, String to) {
		int [][] editDistanceMatrix=new int[to.length()+1][from.length()+1];
		/**Initializing the array*/
		for (int i = 0; i <= from.length(); i++) {
			editDistanceMatrix[0][i]=i;
		}
		for (int i = 0; i <= to.length(); i++) {
			editDistanceMatrix[i][0]=i;
		}
		int cost;
		for (int i = 1; i <= to.length(); i++) {
			for (int j = 1; j <= from.length(); j++) {
				/**checking if character is same*/
				if(to.charAt(i-1)==from.charAt(j-1)) {
					cost=0;
				}else {
					cost=1;
				}
				editDistanceMatrix[i][j]=Integer.min(editDistanceMatrix[i-1][j-1]+cost, Integer.min(editDistanceMatrix[i-1][j]+1,editDistanceMatrix[i][j-1]+1));
			}
		}
		/*printEditDistanceMatrix(from, to, editDistanceMatrix);*/
		List<Sequence> sequences = new LinkedList<>();
		findSequences(editDistanceMatrix, to.length(), from.length(), from, to,sequences);
		return sequences;
	}
	
	/**
	 * This the internal recursive procedure that would find all the optimal sequences with which "from" string can be converted to "to" string
	 * @param editDistanceMatrix : calculated edit distance matrix using dynamic approach
	 * @param i : length of the string "to"
	 * @param j : length of the string "from"
	 * @param from
	 * @param to
	 * @param sequences : object reference of the list of sequences <Output would be available in this data member>
	 */
	
	private void findSequences(int[][] editDistanceMatrix, int i, int j, String from, String to, List<Sequence> sequences){
		if(i==0 && j==0) {
			Sequence sequence = new Sequence();
			sequence.text.append(from);
			sequence.out.append(from);
			sequences.add(sequence);
		}else if(i==0 && j>0) {
			/**Move left : Delete Operation*/
			findSequences(editDistanceMatrix, i, j-1, from, to,sequences);
			for (Sequence sequence : sequences) {
				int factor = from.length()-sequence.out.length();
				sequence.out.deleteCharAt(j-factor-1);
				sequence.text.append(" delete "+ from.charAt(j-1) +" -> " +sequence.out);
			}
		}else if(i>0 && j==0){
			/**Moved Top	 : Insert Operation*/
			findSequences(editDistanceMatrix, i-1, j, from, to,sequences);
			for (Sequence sequence : sequences) {
				int factor = from.length()-sequence.out.length();
				sequence.out.insert(j-factor, to.charAt(i-1));
				sequence.text.append(" insert "+ to.charAt(i-1)+" -> " + sequence.out);
			}
		}else{
			/**if same character then no operation involved simply move up diagonally*/
			if(from.charAt(j-1)==to.charAt(i-1)) {
				List<Sequence> newSequences = new LinkedList<>();
				findSequences(editDistanceMatrix, i-1, j-1, from, to,newSequences);
				sequences.addAll(newSequences);
			}
			int expectedValue=editDistanceMatrix[i][j]-1;
			if(editDistanceMatrix[i-1][j]==expectedValue) {/**Moved Top : Insert Operation*/
				List<Sequence> newSequences = new LinkedList<>();
				findSequences(editDistanceMatrix, i-1, j, from, to,newSequences);
				sequences.addAll(newSequences);
				for (Sequence sequence : newSequences) {
					int factor = from.length()-sequence.out.length();
					sequence.out.insert(j-factor, to.charAt(i-1));
					sequence.text.append(" insert "+ to.charAt(i-1)+" -> " + sequence.out);
				}
			}
			if(editDistanceMatrix[i][j-1]==expectedValue) {/**Moved Left : Delete Operation*/
				List<Sequence> newSequences = new LinkedList<>();
				findSequences(editDistanceMatrix, i, j-1, from, to,newSequences);
				sequences.addAll(newSequences);
				for (Sequence sequence : newSequences) {
						int factor = from.length()-sequence.out.length()+1;
						sequence.out.deleteCharAt(j-factor);
						sequence.text.append(" delete "+ from.charAt(j-1) +" -> " +sequence.out);
				}
			}
			if(editDistanceMatrix[i-1][j-1]==expectedValue) {/**Moved Diagonally Up : Replace Operation*/
				List<Sequence> newSequences = new LinkedList<>();
				findSequences(editDistanceMatrix, i-1, j-1, from, to,newSequences);
				sequences.addAll(newSequences);
				for (Sequence sequence : newSequences) {
					int factor = from.length()-sequence.out.length();
					sequence.out.replace(j-factor-1, j-factor, String.valueOf(to.charAt(i-1)));
					sequence.text.append(" replace "+ from.charAt(j-1) + " by "+to.charAt(i-1) +" -> " +sequence.out);
				}
		}
		}
	}
	
	/**
	 * This procedure would print the distance matrix 
	 * @param from	: String 1
	 * @param to		: String 2
	 * @param editDistanceMatrix	:calculated edit distance matrix using dynamic approach
	 */
	/*private void printEditDistanceMatrix(String from, String to,int [][] editDistanceMatrix) {
		System.out.println("Distance matrix:");
		for (int i = 0; i <= to.length(); i++) {
			for (int j = 0; j <= from.length(); j++) {
				System.out.print(editDistanceMatrix[i][j]+(j==from.length()?"":","));
			}	
			System.out.println();
		}
	}*/
	
	
    
}
