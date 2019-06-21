/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-06
 */
package chen.pos.welcome.ui;

import chen.pos.welcome.bean.Goods;
import chen.pos.welcome.bean.Product;
import chen.pos.welcome.dao.DaoProxy;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.Arrays;
import java.util.Vector;

public class GoodsPanel {

    private static final GoodsPanel GOODS_PANEL = new GoodsPanel();

    private static final Vector<String> HEADER =  new Vector<String>(
            Arrays.asList("序号", "商品代码", "商品名称", "单位", "单价", "数量", "金额"));
    private enum header {sn, code, name, unit, retailPrice, quantity, price}

    private static Vector<Vector<String>> goodsList = null;
    private static JTable goodsTable = null;
    private int goodsCount = 0;
    private int maxGoodsSn = 0;

    private static JScrollPane goodsPane = null;

    private GoodsPanel() {}

    static void init() {
        goodsList = new Vector<Vector<String>>();
        goodsTable = new JTable(goodsList, HEADER);
        goodsTable.getTableHeader().setFont(new Font("宋体", Font.PLAIN, 15));
        goodsTable.setRowHeight(40);
        goodsTable.setFont(new Font("宋体", Font.PLAIN, 25));
        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        goodsTable.setDefaultRenderer(Object.class, tcr);
        goodsTable.getColumnModel().getColumn(header.sn.ordinal()).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(header.code.ordinal()).setPreferredWidth(100);
        goodsTable.getColumnModel().getColumn(header.name.ordinal()).setPreferredWidth(300);
        goodsTable.getColumnModel().getColumn(header.unit.ordinal()).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(header.retailPrice.ordinal()).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(header.quantity.ordinal()).setPreferredWidth(5);
        goodsTable.getColumnModel().getColumn(header.price.ordinal()).setPreferredWidth(5);
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
                String.valueOf(++maxGoodsSn),
                goods.getId(),
                goods.getName(),
                String.valueOf(goods.getPrice()),
                "1",
                String.valueOf(goods.getPrice())));
        goodsCount++;
        goodsList.add(newGoods);
        InOutPanel.getInstance().updateTotal(goods.getPrice());
        InOutPanel.getInstance().updateNumber(1);
        selectLastRow();
    }

    void updateGoodsList() {
        String goodsCode = InOutPanel.getInstance().getInput().getText();
        System.out.println("input code: " + goodsCode);
        if (goodsCode.equals("")) {
            return;
        }
        Product product = DaoProxy.getInstance().query(goodsCode);
        addToList(product);
    }

    private void addToList(Product product) {
        if (Home.isNewOrder()) {
            InOutPanel.getInstance().reset();
            Home.setNewOrder(false);
        }
        Vector<String> newGoods = new Vector<String>(Arrays.asList(
                String.valueOf(++maxGoodsSn),
                product.getProductCode(),
                product.getProductName(),
                product.getSpec() + "/" + product.getUnit(),
                String.valueOf(product.getRetailPrice()),
                "1",
                String.valueOf(product.getRetailPrice())));
        goodsCount++;
        goodsList.add(newGoods);
        InOutPanel.getInstance().updateTotal(product.getRetailPrice());
        InOutPanel.getInstance().updateNumber(1);
        selectLastRow();
    }

    private void selectLastRow() {
        int lastRowIndex = goodsCount - 1;
        goodsTable.setRowSelectionInterval(lastRowIndex, lastRowIndex);
        goodsTable.scrollRectToVisible(goodsTable.getCellRect(lastRowIndex, 0, false));
        updateUI();
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
        int oldNumber = Integer.valueOf(goodsList.get(goodsSn).get(header.quantity.ordinal()));
        float oldPrice = Float.valueOf(goodsList.get(goodsSn).get(header.price.ordinal()));
        float newPrice = oldPrice / oldNumber * number;
        goodsList.get(goodsSn).set(header.quantity.ordinal(), String.valueOf(number));
        goodsList.get(goodsSn).set(header.price.ordinal(), String.valueOf(newPrice));
        updateUI();

        InOutPanel.getInstance().updateTotal(newPrice - oldPrice);
        InOutPanel.getInstance().updateNumber(number - oldNumber);
    }

    boolean isGoodsListEmpty() {
        return goodsList.isEmpty();
    }

    void removeGoods() {
        int selectedIndex = goodsTable.getSelectedRow();
        int number = Integer.valueOf(goodsList.get(selectedIndex).get(header.quantity.ordinal()));
        float price = Float.valueOf(goodsList.get(selectedIndex).get(header.price.ordinal()));
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
        maxGoodsSn = 0;
        updateUI();
    }

    private void updateUI() {
        goodsTable.updateUI();
    }


}
