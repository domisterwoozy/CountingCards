package com.jacobschneider.countingcards.framework;

/**
 * Convienence class for returning to objects of any type.
 * 
 * @author Jacob
 *
 * @param <F> - type of first object
 * @param <S> - type of second object
 */
public class Pair<F, S> {
	public final F first;
	public final S second;
	
	private Pair(F first, S second) {
		this.first = first;
		this.second = second;		
	}
	
	/**
	 * Public factory method for creating Pair objects
	 * 
	 * @param a - first object
	 * @param b - second object
	 * @return - a newly created Pair object
	 */
	public static<A,B> Pair<A,B> create(A a, B b) {
		return new Pair<A, B>(a,b);
	}
	
	

}
