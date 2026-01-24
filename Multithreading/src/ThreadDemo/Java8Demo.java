package ThreadDemo;

public class Java8Demo {
    public static void main(String[] args) {

        Runnable task = ()->{
            for (int i = 0; i < 6; i++) {
                System.out.println(Thread.currentThread().getName()+" executing "+i);
            }
        };
        new Thread(task).start();
        new Thread(task).start();
    }
}
