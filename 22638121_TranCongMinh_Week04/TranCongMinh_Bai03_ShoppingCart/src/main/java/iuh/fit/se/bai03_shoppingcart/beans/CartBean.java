package iuh.fit.se.bai03_shoppingcart.beans;

import java.util.ArrayList;
import java.util.List;

public class CartBean {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public List<CartItemBean> getItems() {
        return items;
    }


    //them sp
    public void addProduct(Product p) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItemBean(p, 1));
    }

    //xoa sp
    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);

    }

    //cap nhat so luong
    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    //neu nhap <0 thi xoa luon sp
                    removeProduct(productId);
                }
                return;
            }
        }
    }

    //tinh tong tien
    public double getTotal() {
        double total = 0;
        for (CartItemBean item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
    public void clear(){
        items.clear();
    }
}
