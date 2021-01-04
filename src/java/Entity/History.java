/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.util.Date;

/**
 *
 * @author artur
 */
public class History {
    private long id;
    private Person person;
    private Product product;
    private Date takeOnDate;
    private Date returnDate;

    public History() {
    }

    public History(long id, Person person, Product product, Date takeOnDate, Date returnDate) {
        this.id = id;
        this.person = person;
        this.product = product;
        this.takeOnDate = takeOnDate;
        this.returnDate = returnDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getTakeOnDate() {
        return takeOnDate;
    }

    public void setTakeOnDate(Date takeOnDate) {
        this.takeOnDate = takeOnDate;
    }

    @Override
    public String toString() {
        return "History{" + "id=" + id 
                + ", person=" + person 
                + ", product=" + product 
                + ", takeOnDate=" + takeOnDate
                + ", returnDate=" + returnDate 
                + '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    
    
    
    
}
