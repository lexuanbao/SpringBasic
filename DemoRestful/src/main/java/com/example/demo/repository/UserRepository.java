package com.example.demo.repository;


import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long>{

//	@Query(value = "SELECT * FROM users WHERE name = ?1 and name IN ?2 order by length(phone)", nativeQuery = true) //Thao tác dựa trên tên table trong db (Native)
//	public List<UserModel> test(String name, String[] names);
	
	@Query("SELECT u FROM UserModel u WHERE u.name = :name and u.name IN :names") //Thao tác dựa trên tên class (JPQL)
	public List<UserModel> getUserByName(@Param("name") String name, @Param("names") Collection<String> names, Sort sort);
	
	@Modifying
	@Transactional //helps with exception: Executing an update/delete query
	@Query("UPDATE UserModel u SET u.phone = :phone, u.email = :email, u.age = :age WHERE u.name = :name")
	public int updateUserByName(@Param("name") String name, @Param("phone") String phone, @Param("email") String email, @Param("age") int age);
	
	@Modifying(clearAutomatically = true)
	@Transactional //helps with exception: Executing an update/delete query
	@Query(value = "UPDATE users SET phone = 0902123123 WHERE name = 'b'", nativeQuery = true)
	public int updateTest();
}
