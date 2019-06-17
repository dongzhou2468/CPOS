/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-10
 */
package chen.pos.welcome.ui;

import javax.swing.*;
import java.awt.*;

public class InOutPanel {

    private static final InOutPanel IN_OUT_PANEL = new InOutPanel();
    private static JPanel inOutPanel = null;

    private static JTextArea total = null;
    private static final String PREFIX_TOTAL = "\n  总计: ￥";
    private float totalValue = 0;
    private static JTextArea number = null;
    private static final String PREFIX_NUMBER = "\n  件数: ";
    private int numberValue = 0;

    private static JPanel inputPanel = null;
    private static JTextArea prompt = null;
    private static final String INPUT_BARCODE = "\n 商品条码";
    private static final String INPUT_NUMBER = "\n 数量";
    private static JTextField input = null;

    private InOutPanel() {}

    static void init() {
        inOutPanel = new JPanel();
        inOutPanel.setLayout(new BorderLayout());

        total = new JTextArea(PREFIX_TOTAL);
        total.setFont(new Font("宋体", Font.BOLD, 30));
        inOutPanel.add(total, "North");

        number = new JTextArea(PREFIX_NUMBER);
        number.setFont(new Font("宋体", Font.BOLD, 30));
        inOutPanel.add(number, "Center");

        inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        prompt = new JTextArea(INPUT_BARCODE);
        prompt.setFont(new Font("宋体", Font.BOLD, 20));
        inputPanel.add(prompt, "North");

        input = new JTextField(25);
        input.setFont(new Font("宋体", Font.BOLD, 25));
        input.setCaretPosition(0);
        inputPanel.add(input, "Center");
        inputPanel.add(new JTextArea("\n\n"), "South");
        inputPanel.add(new JTextArea("    "), "West");
        inputPanel.add(new JTextArea("    "), "East");

        inOutPanel.add(inputPanel, "South");
    }

    static InOutPanel getInstance() {
        return IN_OUT_PANEL;
    }

    JPanel getInOutPanel() {
        return inOutPanel;
    }

    JTextField getInput() {
        return input;
    }

    void changePromt2Barcode() {
        prompt.setText(INPUT_BARCODE);
    }

    void changePromt2Number() {
        prompt.setText(INPUT_NUMBER);
    }

    void updateTotal(float addPrice) {
        totalValue += addPrice;
        total.setText(PREFIX_TOTAL + totalValue);
    }

    void updateNumber(int addNumber) {
        numberValue += addNumber;
        number.setText(PREFIX_NUMBER + numberValue);
    }

    float getTotalValue() {
        return totalValue;
    }

    void reset() {
        total.setText(PREFIX_TOTAL);
        number.setText(PREFIX_NUMBER);
    }
}
