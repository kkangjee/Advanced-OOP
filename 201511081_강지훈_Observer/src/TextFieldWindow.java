import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextFieldWindow extends FrameWindow implements Observer {
    private JTextField textField;

    private Subject subject;//스레드 객체 받아올 공간 선언

    private String primeNumber;//받아온 소수를 문자열로 변환한 것을 담을 변수

    public TextFieldWindow(String title, int x, int y, int width, int height, Subject subject) {

        super(title, x, y, width, height);
        this.subject = subject;//스레드 객체
        subject.registerObserver(this);//첫 옵저버 등록

    }

    public void updateText(String msg) {
        textField.setText(msg);
        textField.validate();

    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        textField = new JTextField();
        panel.add(textField);
        panel.setPreferredSize(new Dimension(width, height));

        return panel;
    }


    @Override
    public void update(int primeNumber) {//소수 데이터를 업데이트
        this.primeNumber = Integer.toString(primeNumber);
        updateText(this.primeNumber);
    }
}
