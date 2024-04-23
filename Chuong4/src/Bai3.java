import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Random;

public class Bai3 {

    private static final int NUMBER_OF_ACCOUNTS = 10;
    private static final int NUMBER_OF_THREADS = 10;
    private static final int NUMBER_OF_TRANSFERS = 1000;

    private static Account[] accounts;
    private static ExecutorService executorService;

    public static void main(String[] args) {
        // Khởi tạo các tài khoản
        accounts = new Account[NUMBER_OF_ACCOUNTS];
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            accounts[i] = new Account(i + 1, 1000.00); // Thay đổi số dư ban đầu
        }

        // Hiển thị số dư của các tài khoản trước khi chuyển khoản
        System.out.println("Số dư các tài khoản trước khi chuyển khoản:");
        printAccountBalances();

        // Khởi tạo ExecutorService
        executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

        // Thực hiện các giao dịch chuyển khoản
        for (int i = 0; i < NUMBER_OF_TRANSFERS; i++) {
            final int finalI = i; // Declare final variable for loop counter
            executorService.execute(() -> {
                // Chọn hai tài khoản ngẫu nhiên
                int account1Index = new Random().nextInt(NUMBER_OF_ACCOUNTS);
                int account2Index = new Random().nextInt(NUMBER_OF_ACCOUNTS);

                // Đảm bảo rằng hai tài khoản không giống nhau
                while (account1Index == account2Index) {
                    account2Index = new Random().nextInt(NUMBER_OF_ACCOUNTS);
                }

                Account account1 = accounts[account1Index];
                Account account2 = accounts[account2Index];

                synchronized (account1) {
                    synchronized (account2) {
                        if (account1.getBalance() >= 1.00) {
                            // Thực hiện chuyển khoản
                            double amount = new Random().nextDouble() * 100.00; // Thay đổi số tiền chuyển
                            account1.withdraw(amount);
                            account2.deposit(amount);

                            // Hiển thị thông tin giao dịch
                            System.out.println("Giao dịch #" + (finalI + 1) + ":"); // Use finalI for transaction number
                            System.out.println("Tên luồng: " + Thread.currentThread().getName());
                            System.out.println("Số tiền chuyển: " + amount);
                            System.out.println("Chuyển từ: TK" + account1.getId());
                            System.out.println("Chuyển đến: TK" + account2.getId());
                            System.out.println("Số dư sau khi chuyển:");
                            printAccountBalances();
                            System.out.println();
                        }
                    }
                }
            });
        }

        // Chờ cho tất cả các giao dịch hoàn thành
        executorService.shutdown();
        try {
            executorService.awaitTermination(Long.MAX_VALUE, java.util.concurrent.TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Hiển thị số dư của các tài khoản sau khi chuyển khoản
        System.out.println("Số dư các tài khoản sau khi chuyển khoản:");
        printAccountBalances();
    }

    private static void printAccountBalances() {
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            System.out.println("TK" + accounts[i].getId() + ": " + accounts[i].getBalance());
        }
    }
}

class Account {
    private int id;
    private double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public double getBalance() {
        return balance;
    }

    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        }
    }

    public synchronized void deposit(double amount) {
        balance += amount;
    }
}
