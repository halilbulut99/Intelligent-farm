package com.farmSystem.farmSystem.entity;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;



//import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import jakarta.persistence.*;

@Entity
@Table(name = "animals")
public class Animal {
	
	    @Id
		@Column(name = "id", nullable = false)
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    
	    @Column(name = "name", nullable = false)
	    private String name;
	    
	    @Column(name = "type", nullable = false)
	    private String type;
	    
	    @Column(name = "breed", nullable = false)
	    private String breed;
	    
	    @Column(name = "father", nullable = false)
	    private String father;
	    
	    @Column(name = "mother", nullable = false)
	    private String mother;
	    
	    @Column(name = "age", nullable = false)
	    private int age;
	    
	    @Column(name = "weight", nullable = false)
	    private int weight;
	    
	   @Column(name = "birthDate", nullable = true)
	   private LocalDate birthDate;
	   
	    public Animal() {
	    }
	    
	    @Autowired
	    public Animal(String name,String type,String breed,String father,String mother,int age,int weight,LocalDate birthDate) {
	        this.name = name;
	        this.type = type;
	        this.breed = breed;
	        this.father = father;
	        this.mother = mother;
	        this.age = age;
	        this.weight = weight;
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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getBreed(String breed) {
			return breed;
		}

		public void setBreed(String breed) {
			this.breed = breed;
		}

		public String getFather() {
			return father;
		}

		public void setFather(String father) {
			this.father = father;
		}

		public String getMother() {
			return mother;
		}

		public void setMother(String mother) {
			this.mother = mother;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		public LocalDate getBirthDate() {
			return birthDate;
		}

		public void setBirthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
		}

		@Override
	    public String toString() {
	        return "Animal { " +
	                " Name = '" + name + '\'' +
	              ", Type = " + type +
	              ", Breed = " + breed +
	              ", Father = " + father +
	              ", Mother = " + mother +
	              ", Age = " + age +
	              ", Weight = " + weight +
	             ", Date = " + birthDate +
	                '}';
	    }
}
