package org.bignumberutils.utils;


public final class  Comparables<T extends Comparable<T>> {

	private final T a;

	private Comparables(T a) {
		this.a = a;
	}

	public boolean greaterThan(T b) {
		return a.compareTo(b) > 0;
	}

	public boolean greaterOrEqualThan(T b) {
		return a.compareTo(b) >= 0;
	}

	public boolean smallerThan(T b) {
		return a.compareTo(b) < 0;
	}

	public boolean smallerOrEqualThan(T b) {
		return a.compareTo(b) <= 0;
	}

	public static <T extends Comparable<T>> Comparables<T> is(T a) {
		return new Comparables<T>(a);
	}
}
