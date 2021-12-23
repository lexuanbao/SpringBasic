package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserModel;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {//<Entity's type, primary key's type> (entity type phải có annotation @Entity)
	//Có thể dùng các phương thức save(), findOne(), findAll(), count(), delete()
	//Các phương thức này sẽ gắn vào trong thời gian runtime
}
