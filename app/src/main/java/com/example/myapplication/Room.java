package com.example.myapplication;

public class Room {
    private String id;
    private String name;
    private double price;
    private boolean occupied;
    private String tenantName;
    private String phone;

    public Room(String id, String name, double price, boolean occupied, String tenantName, String phone) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.occupied = occupied;
        this.tenantName = tenantName;
        this.phone = phone;
    }

    // Getter và Setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public boolean isOccupied() { return occupied; }
    public void setOccupied(boolean occupied) { this.occupied = occupied; }

    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
