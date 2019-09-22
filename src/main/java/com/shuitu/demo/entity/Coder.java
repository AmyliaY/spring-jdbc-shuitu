package com.shuitu.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
* @author 全恒
*/

@Entity
@Table(name = "shuitu_coder")
public class Coder implements Serializable{

	@Id
	private String id;
	
	private String name;
	
	private Integer age;
	
	private String love;
	
	private Double hight;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getLove() {
		return love;
	}

	public void setLove(String love) {
		this.love = love;
	}

	public Double getHight() {
		return hight;
	}

	public void setHight(Double hight) {
		this.hight = hight;
	}

	
}
