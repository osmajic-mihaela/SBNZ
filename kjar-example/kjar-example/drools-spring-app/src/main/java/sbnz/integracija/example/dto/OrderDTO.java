package sbnz.integracija.example.dto;

import demo.facts.OrderItem;
import demo.facts.Transaction;

import java.util.ArrayList;

public class OrderDTO {
    public String userEmail;
    public ArrayList<OrderItem> items;
    public Transaction transaction;
}
