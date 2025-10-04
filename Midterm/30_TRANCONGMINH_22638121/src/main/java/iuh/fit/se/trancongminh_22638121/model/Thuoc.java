package iuh.fit.se.trancongminh_22638121.model;

public class Thuoc {
    private int maThuoc;
    private String tenThuoc;
    private double gia;
    private int namSX;
    private int maLoai;

    public Thuoc() {
    }

    public Thuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public Thuoc(int maThuoc, String tenThuoc, double gia, int namSX, int maLoai) {
        this.maThuoc = maThuoc;
        this.tenThuoc = tenThuoc;
        this.gia = gia;
        this.namSX = namSX;
        this.maLoai = maLoai;
    }

    public Thuoc(String tenThuoc, double gia, int namSX, int maLoai) {
        this.tenThuoc = tenThuoc;
        this.gia = gia;
        this.namSX = namSX;
        this.maLoai = maLoai;
    }

    // getter & setter
    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public String getTenThuoc() {
        return tenThuoc;
    }

    public void setTenThuoc(String tenThuoc) {
        this.tenThuoc = tenThuoc;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getNamSX() {
        return namSX;
    }

    public void setNamSX(int namSX) {
        this.namSX = namSX;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }
}
