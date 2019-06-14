/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-06
 */
package chen.pos.welcome.ui;

import chen.pos.welcome.bean.Goods;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class GoodsPanel {

    private static final GoodsPanel GOODS_PANEL = new GoodsPanel();

    private static final Vector<String> header =  new Vector<String>(
            Arrays.asList("序号", "商品代码", "商品名称", "单价", "数量", "金额"));
    private static Vector<Vector<String>> goodsList = null;
    private static JTable goodsTable = null;
    private int goodsCount = 0;

    private static JScrollPane goodsPane = null;

    private GoodsPanel() {}

    static void init() {
        goodsList = new Vector<Vector<String>>();
        goodsTable = new JTable(goodsList, header);
        goodsTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 15));
        goodsTable.setRowHeight(40);
        goodsTable.setFont(new Font("宋体", Font.PLAIN, 25));
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        goodsTable.setDefaultRenderer(Object.class, tcr);
        goodsTable.getColumnModel().getColumn(0).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(1).setPreferredWidth(100);
        goodsTable.getColumnModel().getColumn(2).setPreferredWidth(300);
        goodsTable.getColumnModel().getColumn(3).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(4).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(5).setPreferredWidth(5);
        goodsPane = new JScrollPane(goodsTable);
        goodsPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    }

    static GoodsPanel getInstance() {
        return GOODS_PANEL;
    }

    JScrollPane getGoodsPane() {
        return goodsPane;
    }

    void addToList(Goods goods) {
        Vector<String> newGoods = new Vector<String>(Arrays.asList(
                String.valueOf(++goodsCount),
                goods.getId(),
                goods.getName(),
                String.valueOf(goods.getPrice()),
                "1",
                String.valueOf(goods.getPrice())));
        goodsList.add(newGoods);
        InOutPanel.getInstance().updateTotal(goods.getPrice());
        InOutPanel.getInstance().updateNumber(1);
        selectLastRow();
    }

    private void selectLastRow() {
        int lastRowIndex = goodsCount - 1;
        goodsTable.setRowSelectionInterval(lastRowIndex, lastRowIndex);
        goodsTable.scrollRectToVisible(goodsTable.getCellRect(lastRowIndex, 0, false));
    }

    void setSelectedRow(int shift) {
        int lastRowIndex = goodsCount - 1;
        int newSelectedIndex = goodsTable.getSelectedRow() + shift;
        newSelectedIndex = (newSelectedIndex < 0) ? 0 : newSelectedIndex;
        newSelectedIndex = (newSelectedIndex > lastRowIndex) ? lastRowIndex : newSelectedIndex;
        goodsTable.setRowSelectionInterval(newSelectedIndex, newSelectedIndex);
        goodsTable.scrollRectToVisible(goodsTable.getCellRect(newSelectedIndex, 0, false));
    }

    void updateGoodsNumber() {
        String goodsNumber = InOutPanel.getInstance().getInput().getText();
        System.out.println("input number: " + goodsNumber);
        if (goodsNumber.equals("")) {
            return;
        }
        int goodsSn = goodsTable.getSelectedRow();
        System.out.println("goodsNumber: " + goodsNumber + ", goodsSn: " + goodsSn);
        modifyGoodsNumber(goodsSn, Integer.valueOf(goodsNumber));
    }

    private void modifyGoodsNumber(int goodsSn, int number) {
        float price = Float.valueOf(goodsList.get(goodsSn).get(5));
        float newPrice = number * price;
        goodsList.get(goodsSn).set(4, String.valueOf(number));
        goodsList.get(goodsSn).set(5, String.valueOf(newPrice));
        updateUI();

        InOutPanel.getInstance().updateTotal(newPrice - price);
        InOutPanel.getInstance().updateNumber(number - 1);
    }

    boolean isGoodsListEmpty() {
        return goodsList.isEmpty();
    }

    void removeGoods() {
        int selectedIndex = goodsTable.getSelectedRow();
        int number = Integer.valueOf(goodsList.get(selectedIndex).get(4));
        float price = Float.valueOf(goodsList.get(selectedIndex).get(5));
        goodsList.remove(selectedIndex);
        goodsCount -= number;
        updateUI();
        InOutPanel.getInstance().updateTotal(0 - price);
        InOutPanel.getInstance().updateNumber(0 - number);
        selectLastRow();
    }

    void clear() {
        goodsList.clear();
        goodsCount = 0;
        updateUI();
        InOutPanel.getInstance().reset();
    }

    private void updateUI() {
        goodsTable.updateUI();
    }
}
