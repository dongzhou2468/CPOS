/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-05
 */
package chen.pos.welcome.ui;

import javax.swing.UIManager;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.plaf.FontUIResource;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import chen.pos.welcome.dao.DaoProxy;

import static javax.swing.SwingUtilities.invokeLater;

public class Home {
    private static final String LAYOUT_NORTH = "North";
    private static final String LAYOUT_SOUTH = "South";
    private static final String LAYOUT_EAST = "East";
    private static final String LAYOUT_CENTER = "Center";

    static final JFrame frame = new JFrame();
    static boolean newOrder = false;

    private static GoodsPanel goodsPanel = null;
    private static InOutPanel inOutPanel = null;

    private static void createAndShowGUI() {

//        Color bgColor = new Color(77, 88, 99);

        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("宋体", Font.PLAIN, 15)));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.PLAIN, 20)));

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        frame.getContentPane().add(new TimePanel().getTime(), LAYOUT_NORTH);
        frame.getContentPane().add(new WelcomePanel().getWelcome(), LAYOUT_SOUTH);
        frame.getContentPane().add(inOutPanel.getInOutPanel(), LAYOUT_EAST);
        frame.getContentPane().add(goodsPanel.getGoodsPane(), LAYOUT_CENTER);

//        frame.pack();
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setVisible(true);

        final JTextField input = inOutPanel.getInput();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                input.requestFocus();
            }
        });
        input.addKeyListener(new ModifyNumber());
    }

    private static void startService() {

        DaoProxy.init();
        GoodsPanel.init();
        InOutPanel.init();
        goodsPanel = GoodsPanel.getInstance();
        inOutPanel = InOutPanel.getInstance();

        // 显示应用 GUI
        invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

//        for (int i = 0; i < 25; ++i) {
//            Goods goods = new Goods();
//            goods.setId("07308017");
//            goods.setBarCode("6900000002");
//            goods.setName("怡宝1.5L");
//            goods.setPrice(3.5f);
//            goods.setStock(24);
//            goods.setCategory("水");
//            goodsPanel.addToList(goods);
//        }
    }

    public static boolean isNewOrder() {
        return newOrder;
    }

    public static void setNewOrder(boolean newOrder) {
        Home.newOrder = newOrder;
    }

    public static void main(String[] args) {
        startService();

//        new Home().mybatisTest();
    }
}
