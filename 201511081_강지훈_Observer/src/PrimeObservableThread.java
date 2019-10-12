import java.util.ArrayList;

public class PrimeObservableThread implements Runnable, Subject {
    private static final int SLEEPTIME = 500;

    private int primeNumber;
    private int numCount;
    private boolean first = true;
    private boolean stopRunning = false;

    private ArrayList observers;//옵저버 객체를 담을 리스트 생성

    public PrimeObservableThread() {
        observers = new ArrayList();//생성자에서 리스트 생성
    }

    public int getPrimeNumber() {
        return primeNumber;
    }

    public void stopRunning() {
        stopRunning = true;
    }

    public void startRunning() {
        stopRunning = false;
        run();
    }

    private void generatePrimeNumber() {
        while (stopRunning == false) {
            if (first) {
                first = false;
                primeNumber = 2;   // 첫 번째 소수는 2
                System.out.println(primeNumber);
                numCount = 1; // 다음 단계부터는 2를 더해서 홀수만 확인하므로 1로 바꿔서 다음 숫자를 3으로 만들어야 함
            } else {
                numCount += 2; // 2를 제외한 짝수는 소수가 될 수 없음. 따라서 홀수만 검사
                if (isPrimeNumber(numCount)) {
                    primeNumber = numCount;
                    System.out.println(primeNumber);
                    notifyObservers();//옵저버에 알림
                }
            }
            try {
                Thread.sleep(SLEEPTIME); // 1초 쉼
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isPrimeNumber(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void run() {
        generatePrimeNumber();
    }


    @Override
    public void registerObserver(Observer o) {//옵저버 생성
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {//옵저버 제거
        int i = observers.indexOf(o);
        if (i >= 0)
            observers.remove(i);
    }

    @Override
    public void notifyObservers() {//옵저버에 알림
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(primeNumber);
        }
    }
}
