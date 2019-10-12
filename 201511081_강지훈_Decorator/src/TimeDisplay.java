import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class TimeDisplay extends DisplayDecorator {
    Display displayComponent;//base 공간

    JPanel panel;// 레이블을 담을 패널
    LabelPanel labelPanel;// 문구를 보일 레이블

    TimeDisplay(Display display, int width, int height) {
        super(display, width, height);
        this.displayComponent = display;//base 설정

    }

    @Override
    public JPanel create() {
        // 레이블을 담을 패널 생성
        panel = new JPanel();
        //panel.setBackground(Color.RED);//패널 색 구분
        // 레이블 또는 다른 패널이 추가될 때 세로 방향으로 나열되도록 레이아웃 설정
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        // 크기 지정
        panel.setPreferredSize(new Dimension(getWidth(), getHeight()));
        // 문구를 보일 레이블 생성
        labelPanel = new LabelPanel();
        // 패널에 레이블을 붙임.
        panel.add(displayComponent.create());//base 패널 생성 및 달기
        panel.add(labelPanel.createPanel(getWidth(), getHeight()));

        return panel;
    }

    @Override
    public void show() {
        Date today = new Date();
        displayComponent.show();//base text update
        labelPanel.updateText("Date: "+today);
    }
}
