package com.twu.refactoring;

import java.util.List;

public class Order {
    String name;
    String address;
    List<LineItem> productList;

    public Order(String name, String address, List<LineItem> productList) {
        this.name = name;
        this.address = address;
        this.productList = productList;
    }

    public String getCustomerName() {
        return name;
    }

    public String getCustomerAddress() {
        return address;
    }

    public List<LineItem> getLineItems() {
        return productList;
    }
}
