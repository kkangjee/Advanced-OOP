import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindow extends FrameWindow implements ActionListener {
    private static final String MAIN_TITLE = "Main Window";
    private static final String TEXTFIELD_WINDOW_TITLE = "TextField Window";
    private static final String LABEL_WINDOW_TITLE = "Label Window";
    private static final String TEXTFIELD_OBSERVER_BUTTON_TITLE = "TextField Window Observer";
    private static final String LABEL_OBSERVER_BUTTON_TITLE = "Label Window Observer";
    private static final String STOP_THREAD_BUTTON_TITLE = "Stop Generating Prime Number";
    private static final int X = 250;
    private static final int Y = 100;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 200;
    private static final int GAP = 50;

    private JButton stopButton;
    private JButton textFieldObserverButton;
    private JButton labelObserverButton;
    private PrimeObservableThread primeThread;
    private TextFieldWindow textFieldWindow;
    private LabelWindow labelWindow;
    private boolean labelWindowAdded = true;
    private boolean textFieldWindowAdded = true;

    private Creator creator;

    public MainWindow(String title) {
        super(title, X, Y, WIDTH, HEIGHT);

        creator = ConcreateCreator.getInstance();
        textFieldWindow = (TextFieldWindow) creator.factoryMethod(TEXTFIELD_WINDOW_TITLE, X, Y + HEIGHT + GAP, WIDTH, HEIGHT);
        labelWindow = (LabelWindow) creator.factoryMethod(LABEL_WINDOW_TITLE, X, Y + (HEIGHT + GAP) * 2, WIDTH, HEIGHT);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                primeThread.stopRunning();
                textFieldWindow.closeWindow();
                labelWindow.closeWindow();
                System.exit(0);
            }
        });

        primeThread = new PrimeObservableThread();
        primeThread.addObserver(textFieldWindow);
        primeThread.addObserver(labelWindow);
        primeThread.run();
    }

    public JPanel createPanel(int width, int height) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setPreferredSize(new Dimension(width, height));
        textFieldObserverButton = createButton("Remove " + TEXTFIELD_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(textFieldObserverButton);
        labelObserverButton = createButton("Remove " + LABEL_OBSERVER_BUTTON_TITLE, this, width, height);
        panel.add(labelObserverButton);
        stopButton = createButton(STOP_THREAD_BUTTON_TITLE, this, width, height);
        panel.add(stopButton);
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == textFieldObserverButton) {
            if (textFieldWindowAdded) {
                primeThread.removeObserver(textFieldWindow);
                textFieldWindowAdded = false;
                textFieldObserverButton.setText("Add " + TEXTFIELD_OBSERVER_BUTTON_TITLE);
            }
            else {
                primeThread.addObserver(textFieldWindow);
                textFieldWindowAdded = true;
                textFieldObserverButton.setText("Remove " + TEXTFIELD_OBSERVER_BUTTON_TITLE);
            }
        }
        else if (e.getSource() == labelObserverButton) {
            if (labelWindowAdded) {
                primeThread.removeObserver(labelWindow);
                labelWindowAdded = false;
                labelObserverButton.setText("Add " + LABEL_OBSERVER_BUTTON_TITLE);
            }
            else {
                primeThread.addObserver(labelWindow);
                labelWindowAdded = true;
                labelObserverButton.setText("Remove " + LABEL_OBSERVER_BUTTON_TITLE);
            }
        }
        else if (e.getSource() == stopButton) {
            primeThread.stopRunning();
        }
    }

    private JButton createButton(String text, ActionListener listener, int width, int height) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        Dimension buttonDimension = new Dimension(width, height / 3);
        button.setMaximumSize(buttonDimension);
        button.setMinimumSize(buttonDimension);
        button.setPreferredSize(buttonDimension);
        return button;
    }

    public static void main(String[] args) {
        MainWindow mainWindow = new MainWindow(MainWindow.MAIN_TITLE);
    }
}
