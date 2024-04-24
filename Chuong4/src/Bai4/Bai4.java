package Bai4;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.concurrent.*;

import java.util.concurrent.Executors;

public class Bai4 {
    private JButton btnStart;
    private JButton btnStop;
    private JTable table1;
    private JPanel panel;
    private JLabel lblct;
    private JPanel panel1;
    private JScrollPane scrollPane;

    DefaultTableModel tableModel = new DefaultTableModel();

    private static final int NUMBER_OF_ACCOUNTS = 10;
    private static final int NUMBER_OF_TRANSFERS = 1000;

    private static Account[] accounts;
    private ScheduledExecutorService scheduledExecutorService;
    double balance;
    private int transferCount = 0;
    public Bai4() {
        // Khởi tạo JTable và JScrollPane
        tableModel.addColumn("Thread Name");
        tableModel.addColumn("Số tiền chuyển");
        tableModel.addColumn("Chuyển từ tài khoản");
        tableModel.addColumn("Chuyển tới tài khoản");
        tableModel.addColumn("Tổng tiền trong 2 tài khoản");

        table1 = new JTable(tableModel);
        scrollPane.setViewportView(table1);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTransfer();
            }
        });
        btnStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTransfer();

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai4");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Bai4().panel1);
        frame.pack();
        frame.setVisible(true);
    }
    private void startTransfer() {
        btnStart.setEnabled(false);
        btnStop.setEnabled(true);

        accounts = new Account[NUMBER_OF_ACCOUNTS];
        for (int i = 0; i < NUMBER_OF_ACCOUNTS; i++) {
            accounts[i] = new Account(i + 1, 1000.00);
            balance += accounts[i].getBalance();

        }
        scheduledExecutorService = Executors.newScheduledThreadPool(10);
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (transferCount < NUMBER_OF_TRANSFERS) {
                String threadName = Thread.currentThread().getName();
                int account1Index = (int) (Math.random() * NUMBER_OF_ACCOUNTS);
                int account2Index = (int) (Math.random() * NUMBER_OF_ACCOUNTS);

                while (account1Index == account2Index) {
                    account2Index = (int) (Math.random() * NUMBER_OF_ACCOUNTS);
                }

                Account account1 = accounts[account1Index];
                Account account2 = accounts[account2Index];

                if (account1.getBalance() >= 1.00) {
                    double amount = new Random().nextDouble() * 100.00; // Số tiền chuyển ngẫu nhiên
                    account1.withdraw(amount);
                    account2.deposit(amount);


                    //String threadName = "Thread[Thread-" + (transferCount + 1) + "]";
                    String fromAccount = "TK" + account1.getId();
                    String toAccount = "TK" + account2.getId();
                    String totalBalance = String.valueOf(account1.getBalance() + account2.getBalance());

                    SwingUtilities.invokeLater(() -> {
                        tableModel.addRow(new Object[]{threadName, amount, fromAccount, toAccount, totalBalance});

                        // Làm cho table1 scroll tới hàng mới nhất
                        int rowCount = tableModel.getRowCount();
                        table1.scrollRectToVisible(table1.getCellRect(rowCount - 1, 0, true));
                    });

                    transferCount++;
                }
            } else {
                stopTransfer();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private void stopTransfer() {
        btnStart.setEnabled(true);
        btnStop.setEnabled(false);

        scheduledExecutorService.shutdown();


    }

}

class Account {
    private int id;
    private double balance;

    public Account(int id, double balance) {
        this.id = id;
        this.balance = balance;
    }
    public Account()
    {

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