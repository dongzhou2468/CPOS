/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-06
 */
package chen.pos.welcome.bean;

import com.google.common.base.MoreObjects;

public class Goods {

    private String id;
    private String barCode;
    private String name;
    private float price;
    private int stock;
    private String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("barCode", barCode)
                .add("name", name)
                .add("price", price)
                .add("stock", stock)
                .add("category", category)
                .toString();
    }
}
