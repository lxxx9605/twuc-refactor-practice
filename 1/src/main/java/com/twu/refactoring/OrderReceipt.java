package com.twu.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 * 
 */
public class OrderReceipt {
    private Order order;

    public OrderReceipt(Order order) {
        this.order = order;
	}

	public String printReceipt() {
		StringBuilder output = new StringBuilder();
		output.append("======Printing Orders======\n");
        output.append(order.getCustomerName());
        output.append(order.getCustomerAddress());
		double totalPrice = 0d;
		for (LineItem lineItem : order.getLineItems()) {
			output.append(lineItem.getDescription());
			output.append('\t');
			output.append(lineItem.getPrice());
			output.append('\t');
			output.append(lineItem.getQuantity());
			output.append('\t');
			output.append(lineItem.totalAmount());
			output.append('\n');
			totalPrice += lineItem.totalAmount();
		}
		output.append("Sales Tax").append('\t').append(totalPrice * .10);
		output.append("Total Amount").append('\t').append(totalPrice * 1.10);
		return output.toString();
	}
}