package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;

public class MainForm extends JFrame {
    private JPanel wrap;
    private JPanel headerPanel;
    private JPanel leftPanel;
    private JPanel rihgtPanel;
    private JButton showButton;
    private JButton chooseButton;
    private JLabel headerInfoLabel;
    private JLabel leftLabel;
    private JLabel rightLabel;
    private Deque<String> deque;

    MainForm() {
        ShowAction showAction = new ShowAction();
        ChooseAction chooseAction = new ChooseAction();
        String headerInformationString = "<html>Шарганов В.С.<br>" +
                "Задание: Ввести строки из файла, записать их в стек. Создать форму, строки на форму таблицу из двух колонок.<br>" +
                "Одна колонка содержит строки в том порядке в котором они были в файле, а другая в обратном порядке<br>" +
                "Реализовать на Фреймворке AWT, SWING и JavaFX</html>";
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(wrap);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 350, dimension.height / 2 - 350, 700, 700);
        headerInfoLabel.setText(headerInformationString);
        showButton.addActionListener(showAction);
        chooseButton.addActionListener(chooseAction);
    }

    private static void usual(Deque<String> deque, JLabel leftLabel) {
        Deque<String> copy = new LinkedList<>(deque);
        StringBuilder text = new StringBuilder("<html>");
        while (!copy.isEmpty()) {
            text.append(copy.pollFirst()).append("<br>");
        }
        text.append("</html>");
        leftLabel.setText(String.valueOf(text));
    }

    private static void reverse(Deque<String> deque, JLabel rightLabel) {
        Deque<String> copy = new LinkedList<>(deque);
        StringBuilder text = new StringBuilder("<html>");
        while (!copy.isEmpty()) {
            text.append(copy.pollLast()).append("<br>");
        }
        text.append("</html>");
        rightLabel.setText(String.valueOf(text));
    }

    class ShowAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            usual(deque, leftLabel);
            reverse(deque, rightLabel);
        }
    }
    class ChooseAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Открыть файл");
            if (ret == JFileChooser.APPROVE_OPTION) {
                try {
                    deque = new LinkedList<>();
                    FileInputStream fstream = new FileInputStream(fileopen.getSelectedFile().toString());
                    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
                    String strLine;
                    while ((strLine = br.readLine()) != null) {
                        deque.add(strLine);
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
