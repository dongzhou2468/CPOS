/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-05
 */
package chen.pos.welcome.ui;

import chen.pos.welcome.bean.Goods;
import chen.pos.welcome.bean.Product;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.swing.*;
import javax.swing.plaf.FontUIResource;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.InputStream;

import static javax.swing.SwingUtilities.invokeLater;

public class Home {
    private static final String LAYOUT_NORTH = "North";
    private static final String LAYOUT_SOUTH = "South";
    private static final String LAYOUT_EAST = "East";
    private static final String LAYOUT_CENTER = "Center";

    private static GoodsPanel goodsPanel = null;
    private static InOutPanel inOutPanel = null;

    private static void createAndShowGUI() {

        Color bgColor = new Color(77, 88, 99);

        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        BorderLayout layout = new BorderLayout();
        frame.setLayout(layout);
        frame.getContentPane().add(new TimePanel().getTime(), LAYOUT_NORTH);
        frame.getContentPane().add(new WelcomePanel().getWelcome(), LAYOUT_SOUTH);
        frame.getContentPane().add(inOutPanel.getInOutPanel(), LAYOUT_EAST);
        frame.getContentPane().add(goodsPanel.getGoodsPane(), LAYOUT_CENTER);

        // 显示窗口
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
        UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("宋体", Font.PLAIN, 15)));
        UIManager.put("OptionPane.messageFont", new FontUIResource(new Font("宋体", Font.PLAIN, 20)));
        UIManager.put("OptionPane.inputFont", new FontUIResource(new Font("宋体", Font.PLAIN, 20)));
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

        Goods goodsA = new Goods();
        goodsA.setId("07000300");
        goodsA.setBarCode("6900000000");
        goodsA.setName("怡宝555ml");
        goodsA.setPrice(2f);
        goodsA.setStock(48);
        goodsA.setCategory("水");
        goodsPanel.addToList(goodsA);

        Goods goodsB = new Goods();
        goodsB.setId("07003011");
        goodsB.setBarCode("6900000001");
        goodsB.setName("红牛300ml");
        goodsB.setPrice(6f);
        goodsB.setStock(24);
        goodsB.setCategory("饮料");
        goodsPanel.addToList(goodsB);

        Goods goodsC = new Goods();
        goodsC.setId("07308017");
        goodsC.setBarCode("6900000002");
        goodsC.setName("怡宝1.5L");
        goodsC.setPrice(3.5f);
        goodsC.setStock(24);
        goodsC.setCategory("水");
        goodsPanel.addToList(goodsC);

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

    public static void main(String[] args) {
//        startService();

        new Home().mybatisTest();
    }

    private void mybatisTest() {
        String resource = "mybatis-configuration.xml";
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(resource);
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
        SqlSession sqlSession = factory.openSession();

        String stmt = "chen.pos.welcome.mapper.ProductMapper.getProduct";
        Product product = sqlSession.selectOne(stmt,"07000300");
        System.out.println(product);
        sqlSession.close();
    }
}
