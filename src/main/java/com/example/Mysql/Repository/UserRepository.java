package com.example.Mysql.Repository;

import com.example.Mysql.Entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
