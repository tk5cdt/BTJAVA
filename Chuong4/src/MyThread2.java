public class MyThread2 extends Thread{
    private int start;
    private int end;
    private boolean isEven;

    public MyThread2(int start, int end, boolean isEven) {
        this.start = start;
        this.end = end;
        this.isEven = isEven;
    }

    @Override
    public void run() {
        for (int i = start; i <= end; i++) {
            if (isEven && i % 2 == 0) {
                System.out.print(i + " ");
            } else if (!isEven && i % 2 != 0) {
                System.out.print(i + " ");
            }
        }
    }

    public static void main(String[] args) {
        MyThread2 thread1 = new MyThread2(1, 10, true); // In số chẵn
        MyThread2 thread2 = new MyThread2(1, 10, false); // In số lẻ

        // Chạy thread1 trước
        thread1.start();

        // Chờ thread1 kết thúc
        try {
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Chạy thread2
        thread2.start();
    }
}
