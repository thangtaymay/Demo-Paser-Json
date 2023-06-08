package com.example.demopaserjson.objectdemo;

import java.util.List;

public class User {

    private int currentPage;
    private boolean hasPagination;
    private List<Item> items;
    private String offsetDescription;
    private int totalItems;
    private int totalPages;

    public User(int currentPage, boolean hasPagination, List<Item> items, String offsetDescription, int totalItems, int totalPages) {
        this.currentPage = currentPage;
        this.hasPagination = hasPagination;
        this.items = items;
        this.offsetDescription = offsetDescription;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public boolean isHasPagination() {
        return hasPagination;
    }

    public void setHasPagination(boolean hasPagination) {
        this.hasPagination = hasPagination;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public String getOffsetDescription() {
        return offsetDescription;
    }

    public void setOffsetDescription(String offsetDescription) {
        this.offsetDescription = offsetDescription;
    }

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "User{" +
                "currentPage=" + currentPage +
                ", hasPagination=" + hasPagination +
                ", items=" + items +
                ", offsetDescription='" + offsetDescription + '\'' +
                ", totalItems=" + totalItems +
                ", totalPages=" + totalPages +
                '}';
    }


}
