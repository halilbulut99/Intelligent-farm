package com.farmSystem.farmSystem.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "details")
public class AnimalDetail {
	
	@Id
	@Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
	@Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "a", nullable = false)
    private int a;
    
    @Column(name = "d3", nullable = false)
    private int d3;
    
    @Column(name = "e", nullable = false)
    private int e;
    
    @Column(name = "c", nullable = false)
    private int c;
    
    @Column(name = "ca", nullable = false)
    private int ca;
    
    @Column(name = "b1", nullable = false)
    private int b1;
    
    @Column(name = "temperature", nullable = false)
    private int temperature;
    
       @Column(name = "date", nullable = true)
 	   private LocalDate date;
    
       
       public AnimalDetail() {
	    }
	    
       
    @Autowired
    public AnimalDetail(String name,int a,int d3,int e,int c,int ca,int b1,int temperature,LocalDate date) {
    	this.name = name;
    	this.a = a;
        this.d3 = d3;
        this.e = e;
        this.c = c;
        this.ca = ca;
        this.b1 = b1;
        this.temperature = temperature;
        this.date = date;
	}

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
    
	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getD3() {
		return d3;
	}

	public void setD3(int d3) {
		this.d3 = d3;
	}

	public int getE() {
		return e;
	}

	public void setE(int e) {
		this.e = e;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}

	public int getCa() {
		return ca;
	}

	public void setCa(int ca) {
		this.ca = ca;
	}

	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	
	  public LocalDate getdate() {
	        return date;
	    }
	    public void setdate(LocalDate date) {
	       this.date = date;
	    }
	@Override
    public String toString() {
        return "Details { " +
                " Name = '" + name + '\'' +
                " A = '" + a + '\'' +
              ", D3 = " + d3 +
              ", E = " + e +
              ", C = " + c +
              ", Ca = " + ca +
              ", B1 = " + b1 +
              ", Temperature = " + temperature +
             ", Date = " + date +
                '}';
    }
    
    

}
