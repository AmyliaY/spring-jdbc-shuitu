package com.shuitu.demo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_member")
public class Member implements Serializable {//实体类必须实现序列化，方便在网络上进行传输
	
	@Id
	private Long id;
	
	private String name;
	
	private String addr;
	
	private Long createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
