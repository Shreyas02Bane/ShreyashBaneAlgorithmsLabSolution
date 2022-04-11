/**
 * 
 */
package com.mentorSession.week6;

import java.util.Scanner;

/**
 * @author Shreyas
 *
 */
public class CurrencyDenominations {

	/**
	 * @param args
	 */

	Scanner sc = new Scanner(System.in);

	int currency[], notes[];
	int amount, noOfCurrencyDenominations;

	public void impCurrencyDenominations() {
		takeCurrencyDenominations();

		notes = new int[noOfCurrencyDenominations];

		mergeSort(0, currency.length - 1);
		printValues();

		System.out.println("Enter the amount : ");
		try {
			amount = sc.nextInt();
			if(amount < 0) {
				amount = 0;
				throw new InvalidCurrencyDenominationException("Amount should be always positive");
			}
		}catch(InvalidCurrencyDenominationException icde) {
			System.out.println(icde);
		}
		printNotesToBePaid();
	}

	public void takeCurrencyDenominations() throws InvalidCurrencyDenominationException {

		System.out.println("Enter the number of Currency Denominations : ");
		noOfCurrencyDenominations = sc.nextInt();

		currency = new int[noOfCurrencyDenominations];

		System.out.println("Enter the Currency Denominations : ");
		for (int i = 0; i < currency.length; i++) {

			int store = sc.nextInt();
			try {
				if (store <= 0) {
					currency[i] = 1;
					throw new InvalidCurrencyDenominationException("Currency Denomination cannot be zero or negative");
				} else {
					currency[i] = store;
				}
			} catch (InvalidCurrencyDenominationException icd) {
				System.out.println(icd);
			}

		}
	}

	public void mergeSort(int left, int right) {
		if (left < right) {
			int mid = (left + right) / 2;

			mergeSort(left, mid);
			mergeSort(mid + 1, right);

			merge(left, mid, right);

			System.out.print("After Merge Sort ");
		}
	}

	public void merge(int left, int mid, int right) {

		int len1 = mid - left + 1;
		int len2 = right - mid;

		int leftArr[] = new int[len1];
		int rightArr[] = new int[len2];

		for (int i = 0; i < len1; i++) {
			leftArr[i] = currency[left + i];
		}

		for (int j = 0; j < len2; j++) {
			rightArr[j] = currency[mid + 1 + j];
		}

		int i = 0, j = 0, k = left;

		while (i < len1 && j < len2) {
			if (leftArr[i] <= rightArr[j]) {
				currency[k] = leftArr[i];
				i++;
			} else {
				currency[k] = rightArr[j];
				j++;
			}
			k++;
		}

		while (i < len1) {
			currency[k] = leftArr[i];
			i++;
			k++;
		}

		while (j < len2) {
			currency[k] = rightArr[j];
			j++;
			k++;
		}

	}

	public void printValues() {
		System.out.println("The Currency is as follows : ");

		for (int i = 0; i < currency.length; i++) {
			System.out.println(currency[i]);
		}

	}

	public void printNotesToBePaid() {
		
		while (amount != 0) {
			
			System.out.println("The Notes needed to pay amount : ");
			
			for (int i = currency.length - 1; i >= 0; i--) {

				notes[i] = amount / currency[i];
				amount = amount - (notes[i] * currency[i]);

				System.out.println(currency[i] + " : " + notes[i]);
			}

			if (currency[0] != 1 && amount < currency[0]) {

				System.out.println("1 : " + amount);
				amount = amount - (amount * 1);
			}
		}

	}

	class InvalidCurrencyDenominationException extends ArithmeticException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		String message;

		InvalidCurrencyDenominationException(String str) {
			message = str;
		}

		public String toString() {
			return ("Invalid Currency Denominations Exception Occurred : " + message);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CurrencyDenominations cd = new CurrencyDenominations();
		cd.impCurrencyDenominations();
	}

}
