import java.util.ArrayList;

public class BankingApp {


    private String name;
    private double balance;
    private ArrayList<String> transactions;

    BankingApp(){
        this.balance = 0.0;
        this.transactions = new ArrayList<>();
    }

    public void userRegister(String name,  ArrayList<UserList> userList){
          this.name = name;
//        this.balance = 0.0;
//        this.transactions = new ArrayList<>();
        userList.add(new UserList(name)); // Adding a UserList instance
    }

    public double viewBalance(){
        return this.balance;
    }

    public double deposit(double balance){
        this.balance += balance;
        this.transactions.add("deposited: " + balance );
        return this.balance;
    }

    public double withDraw(double amount){
        if(amount > this.balance){
            System.out.println("Low balance");
        }else {
            this.balance -= amount;
            this.transactions.add("Withdrawn: " + amount);
        }
            return this.balance;
    }

    public ArrayList<String> viewTransaction(){
        return this.transactions;
    }
}
