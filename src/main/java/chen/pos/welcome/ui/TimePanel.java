/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-06
 */
package chen.pos.welcome.ui;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

class TimePanel {

    private JButton time = null;

    private static final Map<Integer, String> DAY;
    static {
        DAY = new HashMap<Integer, String>();
        DAY.put(1, "一");
        DAY.put(2, "二");
        DAY.put(3, "三");
        DAY.put(4, "四");
        DAY.put(5, "五");
        DAY.put(6, "六");
        DAY.put(7, "七");
    }

    TimePanel() {
        time = new JButton();
        time.setFont(new Font("宋体", Font.PLAIN, 20));
        time.setBackground(new Color(77, 88, 99));
        time.setForeground(Color.white);
        time.setBorderPainted(false);
        startTimer(time);
    }

    JButton getTime() {
        return time;
    }

    private void startTimer(JButton time){

        final JButton varTime = time;
        final SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Date date = new Date(System.currentTimeMillis());
                varTime.setText(df.format(date) + " 星期" + DAY.get(date.getDay()));
            }
        }).start();
    }
}
