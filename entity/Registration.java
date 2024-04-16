package com.farmSystem.farmSystem.entity;
import java.time.LocalDate;
import java.util.Set;

//import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "registration")
public class Registration {
	
	    @Id
		@Column(name = "id", nullable = false)
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    @Column(name = "name", nullable = false)
	    private String name;
	    
	    @Column(name = "bulstat", nullable = false)
	    private String bulstat;
	    
	    @Column(name = "pass", nullable = false)
	    private String pass;
	    
	    @Column(name = "rpass", nullable = false)
	    private String rpass;
	    
	    @Column(name = "email", nullable = false)
	    private String email;
	    
	    @Column(name = "role", nullable = false)
	    private String role;
	    
	    @Column(name = "phone", nullable = false)
	    private int phone;
	    
	 
	    public Registration() {
	    }
	    
	    @Column(name = "birthDate", nullable = true)
		   private LocalDate birthDate;
		   
	    @Autowired
	    public Registration(String name, String pass,String rpass, String email,String bulstat,String role,int phone,LocalDate birthDate) {
	        this.name = name;
	        this.pass = pass;
	        this.rpass = rpass;
	        this.email = email;
	        this.bulstat = bulstat;
	        this.role = role;
	        this.phone = phone;
	        this.birthDate = birthDate;
	        
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
	    
	    public String getPass() {
	        return pass;
	    }
	    
	    public void setPass(String pass) {
	        this.pass = pass;
	    }
	    
	    public String getRpass() {
	        return pass;
	    }
	    
	    public void setRpass(String rpass) {
	        this.rpass = rpass;
	    }
	    
	    public String getEmail() {
	        return email;
	    }
	    
	    public void setEmail(String email) {
	        this.email = email;
	    }
	    
	    public String getBulstat() {
	        return bulstat;
	    }
	    
	    public void setBulstat(String bulstat) {
	        this.bulstat = bulstat;
	    }
	    
	    public String getRole() {
	        return role;
	    }
	    
	    public void setRole(String role) {
	        this.role = role;
	    }
	    
	    public int getPhone() {
	        return phone;
	    }
	    
	    public void setPhone(int phone) {
	        this.phone = phone;
	    }
	
		  public LocalDate getbirthDate() {
		        return birthDate;
		    }
		    public void setbirthDate(LocalDate birthDate) {
		       this.birthDate = birthDate;
		    }
	    @Override
	    public String toString() {
	        return "Name { " +
	                " Name = '" + name + '\'' +
	                ", Email = " + email +
	                 ", Pass = " + pass +
	                  ", Rpass = " + rpass +
	                   ", Bulstat = " + bulstat +
	                    ", Role = " + role +
	                     ", Phone = " + phone +
	                     ", Date = " + birthDate +
	                '}';
	    }
	

}
