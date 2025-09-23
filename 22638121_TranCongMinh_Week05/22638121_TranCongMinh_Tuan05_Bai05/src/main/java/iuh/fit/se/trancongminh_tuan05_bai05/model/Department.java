package iuh.fit.se.trancongminh_tuan05_bai05.model;

public class Department {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department() {
    }

    public Department(int id) {
        this.id = id;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
