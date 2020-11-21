package com.lordy.chapter10;


/**
 * 动态的锁顺序死锁
 *
 * transferMoney(a, b, 10)和transferMoney(b, a, 20)同时调用会产生死锁
 */
public class Account {

    private double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void transferMoney(Account from, Account to, double amount){
        synchronized (from){
            synchronized (to){
                if(from.getBalance() < amount){
                    //
                }else {
                    from.balance = from.getBalance() - amount;
                    to.balance = to.getBalance() + amount;
                }
            }
        }
    }
}
