package com.khetanshu.datamining.vo;

/**
 * @author khetanshu
 * This is the Value-Object which would help in storing the intermediate values of the sequences which backtracking
 */
public class Sequence {
	public StringBuilder text;
	public StringBuilder out;
	
	public Sequence() {
		text = new StringBuilder();
		out = new StringBuilder();
	}
}
