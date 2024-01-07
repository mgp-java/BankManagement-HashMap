package Bank;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
public class Main {
    HashMap<Integer, Accounts> accountsData = new HashMap<Integer, Accounts>();

    int generatePin() {
        Random random = new Random();
        int a = random.nextInt(9), b = random.nextInt(9);
        int pinNo = 1000 + (a * b);
        return pinNo;
    }

    void register() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your account number :");
        int acNo = scanner.nextInt();
        if (acNo > 100000 && acNo < 999999) {
            System.out.println("Enter your account name :");
            String acName = scanner.next();
            if (acName != null) {
                int orgPin = generatePin();
                System.out.println("Re enter the pin number :" + orgPin);
                int pinNo = scanner.nextInt();
                if (pinNo == orgPin) {
                    System.out.println("Enter Initial deposit amount :");
                    int depAmt = scanner.nextInt();
                    if (depAmt > 999) {
                        Accounts accounts = new Accounts(acName, pinNo, depAmt);
                        accountsData.put(acNo, accounts);
                    }
                     else {
                        System.out.println("Enter an amount greater than 999");
                    }
                } else {
                    System.out.println("Enter a valid pin");
                }
            } else {
                System.out.println("Enter a valid account name");
            }
        } else {
            System.out.println("Enter a valid account number");
        }
    }
    void display(){
        for(Map.Entry<Integer,Accounts> entry:accountsData.entrySet()){
            Accounts accounts = entry.getValue();
            System.out.println("********Account Info*************");
            System.out.println("Account no:"+entry.getKey());
            System.out.println("Account Name :"+accounts.accountName);
            System.out.println("Available amount :"+accounts.depositAmount);

        }
    }
    public void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter account number");
        int acNo = sc.nextInt();
        System.out.println("Enter pin number");
        int pinNo = sc.nextInt();
        Boolean isAccExists = false;
        for(Map.Entry<Integer,Accounts> entry :accountsData.entrySet())
        {
            Accounts accounts = entry.getValue();
            if(acNo== entry.getKey() && pinNo == accounts.pinNumber){
                System.out.println("**********Account Info**********");
                System.out.println("Account no :"+entry.getKey());
                System.out.println("Account Name :"+accounts.accountName);
                System.out.println("Available amount :"+accounts.depositAmount);
                isAccExists = true;
            }
        }
        if(!isAccExists){
            System.out.println("Account doesn't exist.Please register it");
            register();
        }

    }
        public static void main(String[] args){
            Main main1 = new Main();
            String choice;
            Scanner sc = new Scanner(System.in);
            do {
                System.out.println("***********Menu*********\n");
                System.out.println("Enter any of the below choices \n 1. Register  \n 2. Login \n 3.Display \n 4.Enter any other character for exiting \n");
                choice = sc.next();
                switch (choice) {
                    case "1":
                        main1.register();
                        break;

                    case "2":
                        main1.login();
                        break;

                    case "3":
                        main1.display();
                        break;

                }
            }while(choice.matches("[1-3]"));
        }
    }