package com.twu.refactoring;

import java.util.ArrayList;
import java.util.Iterator;

public class Customer {

	private String name;
	private ArrayList<Rental> rentalList = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental arg) {
		rentalList.add(arg);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		double totalAmount = 0;
		int frequentRenterPoints = 0;
		Iterator<Rental> rentals = rentalList.iterator();
		StringBuilder result = new StringBuilder();
		result.append("Rental Record for ").append(getName()).append("\n");
		while (rentals.hasNext()) {
			double thisAmount = 0;
			Rental each = rentals.next();
			switch (each.getMovie().getPriceCode()) {
			case Movie.REGULAR: {
				thisAmount += 2;
				if (each.getDaysRented() > 2)
					thisAmount += (each.getDaysRented() - 2) * 1.5;
				break;
			}
			case Movie.NEW_RELEASE: {
				thisAmount += each.getDaysRented() * 3;
				if (each.getDaysRented() > 1) {
					frequentRenterPoints++;
				}
				break;
			}
			case Movie.CHILDRENS: {
				thisAmount += 1.5;
				if (each.getDaysRented() > 3)
					thisAmount += (each.getDaysRented() - 3) * 1.5;
				break;
			}

			}
			frequentRenterPoints++;
			result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
			totalAmount += thisAmount;
		}
		result.append("Amount owed is ").append(totalAmount).append("\n").append("You earned ")
				.append(frequentRenterPoints).append(" frequent renter points");
		return result.toString();
	}

}
