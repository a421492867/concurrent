package com.lordy.chapter10;

public class IdenHashCode {

    private static final Object tieLock = new Object();

    public void transferMoney(final Account from, final Account to, final double amount){
        class Helper{
            public void transfer(){
                if(from.getBalance() < amount){
                    return;
                }else {
                    from.balance = from.getBalance() - amount;
                    to.balance = to.getBalance() + amount;
                }
            }
        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);
        if(fromHash < toHash){
            synchronized (from){
                synchronized (to){
                    new Helper().transfer();
                }
            }
        }else if(fromHash > toHash){
            synchronized (to){
                synchronized (from){
                    new Helper().transfer();
                }
            }
        }else {
            synchronized (tieLock){
                synchronized (from){
                    synchronized (to){
                        new Helper().transfer();
                    }
                }
            }
        }

    }





    class Account{

        private double balance;

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }
    }
}
