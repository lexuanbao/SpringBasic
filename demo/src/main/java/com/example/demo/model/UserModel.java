package com.example.demo.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//Dùng @Transient khi dùng 1 trường ko cần tạo ở database (ví dụ: tổng lương dựa trên tính toán mà ra nên sẽ ko có trong db)
@Entity //Hibernate
@Table(name="users")  //Mapping table trong database, các trường phải có cùng tên với database, TH dùng "_" thì viết dưới dạng camel (createdAt = create_at)
@EntityListeners(AuditingEntityListener.class) //Cấu hình để auditing 2 trường "createdAt", "updatedAt"
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"},  // Bỏ qua value được cung cấp từ rest api
allowGetters = true)
public class UserModel implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//
	private Long id;
	private String name;
	private String phone;
	private String email;
	private int age;
	
	@Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP) //Convert kiểu ngày tháng của cơ sở dữ liệu
    @CreatedDate //Tự động get giá trị khi đối tượng được add new
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate //Tự động get giá trị khi đối tượng được update
    private java.util.Date updatedAt;
	
	public UserModel() {
		super();
	}
	
	public UserModel(Long id, String name, String phone, String email, int age) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.age = age;
	}

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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	@Override
	public String toString() {
		return "UserModel [id=" + id + ", name=" + name + ", phone=" + phone + ", email=" + email + ", age=" + age
				+ "]";
	}
}
