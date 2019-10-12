public interface Subject {
    public void registerObserver(Observer o);        // 옵저버 등록
    public void removeObserver(Observer o);          // 옵저버 제거
    public void notifyObservers();                   // 옵저버에 데이터 전달

}
