/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-06
 */
package chen.pos.welcome.ui;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

class WelcomePanel {

    private JButton welcome = null;

    WelcomePanel() {
        welcome = new JButton("欢 迎 光 临");
        welcome.setFont(new Font("宋体", Font.PLAIN, 15));
        welcome.setBackground(new Color(77, 88, 99));
        welcome.setForeground(Color.white);
        welcome.setBorderPainted(false);
    }

    JButton getWelcome() {
        return welcome;
    }
}
