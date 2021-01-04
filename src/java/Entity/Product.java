/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;


/**
 *
 * @author artur
 */
public class Product {
    private long id;
    private String name;
    private Integer count;
    private Integer quantity;

    public Product() {
    }

    public Product( String name, Integer count, Integer quantity) {
        this.name = name;
        this.count = count;
        this.quantity = quantity;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id 
                + ", name=" + name 
                + ", count=" + count
                + ", quantity=" + quantity 
                + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
