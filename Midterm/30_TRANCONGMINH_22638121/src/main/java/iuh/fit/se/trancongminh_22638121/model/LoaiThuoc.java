package iuh.fit.se.trancongminh_22638121.model;

public class LoaiThuoc {
    private int maLoai;
    private String tenLoai;

    public LoaiThuoc() {
    }

    public LoaiThuoc(int maLoai) {
        this.maLoai = maLoai;
    }

    public LoaiThuoc(int maLoai, String tenLoai) {
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
}
