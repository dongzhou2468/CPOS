/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-10
 */
package chen.pos.welcome.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ModifyNumber implements KeyListener {

    private static final int F11 = 122;
    private static final int UP = 38;
    private static final int DOWN = 40;
    private static final int ENTER = 10;
    private static final int DEAL = 19;
    private static final int DELETE = 127;
    private static final int DELETE_ALL = 155;
    private boolean isF11Pressed = false;

    private static final String DELETE_LINE_PROMPT = "删除该商品？\n按Enter键确认，按Esc键取消";
    private static final String DELETE_ALL_PROMPT = "删除全部内容？\n按Enter键确认，按Esc键取消";
    private static final String CHARGE_PROMPT = "应收取：%s元\n再按一次继续，按Esc键取消";

    public void keyTyped(KeyEvent e) {
        System.out.println("主页输入: keyCode: " + e.getKeyCode());
        System.out.println("主页输入: keyChar: " + e.getKeyChar());
        System.out.println("主页输入: keyText: " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");
    }

    public void keyPressed(KeyEvent e) {
        System.out.println("主页按下: keyCode: " + e.getKeyCode());
        System.out.println("主页按下: keyChar: " + e.getKeyChar());
        System.out.println("主页按下: keyText: " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");

        int keyCode = e.getKeyCode();
        if (keyCode == F11) {
            InOutPanel.getInstance().changePromt2Number();
            isF11Pressed = true;
        } else if (keyCode == ENTER) {
            if (!isF11Pressed) {
                // add goods
                GoodsPanel.getInstance().updateGoodsList();
                InOutPanel.getInstance().getInput().setText("");
            } else {
                // modify number
                GoodsPanel.getInstance().updateGoodsNumber();
                InOutPanel.getInstance().changePromt2Barcode();
                InOutPanel.getInstance().getInput().setText("");
                isF11Pressed = false;
            }
        } else if (keyCode == UP) {
            GoodsPanel.getInstance().setSelectedRow(-1);
        } else if (keyCode == DOWN) {
            GoodsPanel.getInstance().setSelectedRow(1);
        } else if (keyCode == DEAL) {
            if (GoodsPanel.getInstance().isGoodsListEmpty()) {
                return;
            }
            final JButton button = new JButton("确定");
            button.addKeyListener(new KeyListener() {
                public void keyTyped(KeyEvent e) {
                    System.out.println("收钱提示#输入：" + e.getKeyCode());
                }
                public void keyPressed(KeyEvent e) {
                    System.out.println("收钱提示#按下：" + e.getKeyCode());
                    if (e.getKeyCode() == DEAL || e.getKeyCode() == ENTER) {
                        System.out.println("找钱");
                        JDialog chargeDialog = (JDialog) button.getRootPane().getParent();
                        System.out.println(chargeDialog);
                        chargeDialog.dispose();
                        // 开钱柜
                        Robot robot = null;
                        try {
                            robot = new Robot();
                            robot.keyPress(KeyEvent.VK_F7);
                        } catch (AWTException ex) {
                            ex.printStackTrace();
                        }
                        GoodsPanel.getInstance().clear();
                        Home.setNewOrder(true);
                    }
                }
                public void keyReleased(KeyEvent e) {
                    System.out.println("收钱提示#松开：" + e.getKeyCode());
                }
            });
            Object[] option = { button };
            int charge = JOptionPane.showOptionDialog(null,
                    String.format(CHARGE_PROMPT, InOutPanel.getInstance().getTotalValue()),
                    "", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
            System.out.println("charge: " + charge);
            if (charge != 0) {
                return;
            }

        } else if (keyCode == DELETE) {
            if (GoodsPanel.getInstance().isGoodsListEmpty()) {
                return;
            }
            int deleteAll = JOptionPane.showConfirmDialog(
                    null, DELETE_LINE_PROMPT, "", JOptionPane.YES_NO_OPTION);
            System.out.println("delete line: " + deleteAll);
            if (deleteAll == JOptionPane.OK_OPTION) {
                GoodsPanel.getInstance().removeGoods();
            }
        } else if (keyCode == DELETE_ALL) {
            if (GoodsPanel.getInstance().isGoodsListEmpty()) {
                return;
            }
            int deleteAll = JOptionPane.showConfirmDialog(
                    null, DELETE_ALL_PROMPT, "", JOptionPane.YES_NO_OPTION);
            System.out.println("delete all: " + deleteAll);
            if (deleteAll == JOptionPane.OK_OPTION) {
                GoodsPanel.getInstance().clear();
                InOutPanel.getInstance().reset();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        System.out.println("主页按下: keyCode: " + e.getKeyCode());
        System.out.println("主页按下: keyChar: " + e.getKeyChar());
        System.out.println("主页按下: keyText: " + KeyEvent.getKeyText(e.getKeyCode()) + "\n");
    }
}
