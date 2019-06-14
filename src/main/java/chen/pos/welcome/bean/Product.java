/**
 * Author: dongzhou <414214550@qq.com>
 * Created: 2019-06-14
 */
package chen.pos.welcome.bean;

import com.google.common.base.MoreObjects;

public class Product {

    private int gid;
    private String productCode;
    private String shortCode;
    private String productName;
    private String shortName;
    private String sortCode;
    private String sortName;
    private String barCode;
    private String spec;
    private String unit;
    private float retailPrice;
    private String remark;
    private String status;
    private int prcType;
    private int productId;
    private float memberPrice;
    private int isBigPack;
    private int isDisp;
    private String spProductId;
    private int relativeQty;
    private String py;
    private int updateState;
    private String brand;
    private String billTo;
    private int isLtd;

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getShortCode() {
        return shortCode;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getSortName() {
        return sortName;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public float getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(float retailPrice) {
        this.retailPrice = retailPrice;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrcType() {
        return prcType;
    }

    public void setPrcType(int prcType) {
        this.prcType = prcType;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public float getMemberPrice() {
        return memberPrice;
    }

    public void setMemberPrice(float memberPrice) {
        this.memberPrice = memberPrice;
    }

    public int getIsBigPack() {
        return isBigPack;
    }

    public void setIsBigPack(int isBigPack) {
        this.isBigPack = isBigPack;
    }

    public int getIsDisp() {
        return isDisp;
    }

    public void setIsDisp(int isDisp) {
        this.isDisp = isDisp;
    }

    public String getSpProductId() {
        return spProductId;
    }

    public void setSpProductId(String spProductId) {
        this.spProductId = spProductId;
    }

    public int getRelativeQty() {
        return relativeQty;
    }

    public void setRelativeQty(int relativeQty) {
        this.relativeQty = relativeQty;
    }

    public String getPy() {
        return py;
    }

    public void setPy(String py) {
        this.py = py;
    }

    public int getUpdateState() {
        return updateState;
    }

    public void setUpdateState(int updateState) {
        this.updateState = updateState;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBillTo() {
        return billTo;
    }

    public void setBillTo(String billTo) {
        this.billTo = billTo;
    }

    public int getIsLtd() {
        return isLtd;
    }

    public void setIsLtd(int isLtd) {
        this.isLtd = isLtd;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gid", gid)
                .add("productCode", productCode)
                .add("shortCode", shortCode)
                .add("productName", productName)
                .add("shortName", shortName)
                .add("sortCode", sortCode)
                .add("sortName", sortName)
                .add("barCode", barCode)
                .add("spec", spec)
                .add("unit", unit)
                .add("retailPrice", retailPrice)
                .add("remark", remark)
                .add("status", status)
                .add("prcType", prcType)
                .add("productId", productId)
                .add("memberPrice", memberPrice)
                .add("isBigPack", isBigPack)
                .add("isDisp", isDisp)
                .add("spProductId", spProductId)
                .add("relativeQty", relativeQty)
                .add("py", py)
                .add("updateState", updateState)
                .add("brand", brand)
                .add("billTo", billTo)
                .add("isLtd", isLtd)
                .toString();
    }
}
