package main.java.problems;

public class Transaction {

    int id;
    int amount;
    String merchant;
    String time;
    String account;

    public Transaction(int id, int amount, String merchant, String time, String account) {
        this.id = id;
        this.amount = amount;
        this.merchant = merchant;
        this.time = time;
        this.account = account;
    }
}