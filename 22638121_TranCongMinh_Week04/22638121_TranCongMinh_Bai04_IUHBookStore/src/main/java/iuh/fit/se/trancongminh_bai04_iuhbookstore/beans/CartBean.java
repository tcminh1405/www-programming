package iuh.fit.se.trancongminh_bai04_iuhbookstore.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartBean implements Serializable {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    // Thêm sách vào giỏ
    public void addBook(Book book) {
        for (CartItemBean item : items) {
            if (item.getBook().getId() == book.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }
        items.add(new CartItemBean(book, 1));
    }

    // Xóa sách theo id
    public void removeBook(int bookId) {
        Iterator<CartItemBean> iterator = items.iterator();
        while (iterator.hasNext()) {
            CartItemBean item = iterator.next();
            if (item.getBook().getId() == bookId) {
                iterator.remove();
                return;
            }
        }
    }

    // Cập nhật số lượng
    public void updateQuantity(int bookId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getBook().getId() == bookId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    removeBook(bookId);
                }
                return;
            }
        }
    }

    // Tính tổng tiền
    public double getTotal() {
        double total = 0;
        for (CartItemBean item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    // Xóa toàn bộ giỏ hàng
    public void clear() {
        items.clear();
    }
}
