/**
 * 
 */
package com.mentorSession.week6;

import java.util.Scanner;

/**
 * @author Shreyas
 *
 */
public class PayMoney {

	/**
	 * @param args
	 */

	int transactionsArr[];
	int numOfTransactions, target;

	Scanner sc = new Scanner(System.in);

	public void impPayMoney() {
		insertTransactions();

		targetStatus();
	}

	public void insertTransactions() {
		System.out.println("Enter the number of transactions : ");
		numOfTransactions = sc.nextInt();

		transactionsArr = new int[numOfTransactions];

		System.out.println("Enter the transactions : ");
		for (int i = 0; i < numOfTransactions; i++) {
			System.out.println("Transaction " + (i + 1) + " :");
			int store = sc.nextInt();
			try {
				if(store <= 0) {
					transactionsArr[i] = 1;
					throw new InvalidCurrencyAmount("Transaction cannot be Zero or Negative");
				}else {
					transactionsArr[i] = store;
				}
			}catch(InvalidCurrencyAmount ica) {
				System.out.println(ica);
			}
		}

	}

	public void validate(int store) throws InvalidCurrencyAmount {
		if (target <= 0) {
			throw new InvalidCurrencyAmount("Target cannot be Zero or Negative");
		}
	}

	public void targetCheck(){
		System.out.println("Enter the target to be achieved : ");
		target = sc.nextInt();
		try {
			if(target <= 0) {
				throw new InvalidCurrencyAmount("Target cannot be Zero or Negative");
			}else {
				int sum = 0;
				for(int i = 0; i < numOfTransactions; i++) {
			
					sum = sum + transactionsArr[i];
					if(sum >= target) {
						System.out.println("The target has been achieved after "+(i+1)+" transactions");
						break;
					}
				}
				if(sum < target) {
					System.out.println("The target "+target+" cannot be achieved!");
				}
			}
		}catch(InvalidCurrencyAmount ica) {
			System.out.println(ica);
		}
		
		System.out.println("=====================================================================================================================");
	}
	
	public void targetStatus() {
		
		System.out.println("How many targets are to be achieved ? :");
		int targets = sc.nextInt();
		
		for(int i = 1; i <= targets; i++) {
			targetCheck();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PayMoney paymoney = new PayMoney();
		paymoney.impPayMoney();

	}

}

class InvalidCurrencyAmount extends ArithmeticException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	String message;

	public InvalidCurrencyAmount(String str) {
		message = str;
	}

	public String toString() {
		return ("Invalid Currency Amount Exception Occurred : " + message);
	}

}