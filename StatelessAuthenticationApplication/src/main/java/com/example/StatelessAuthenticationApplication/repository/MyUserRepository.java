package com.example.StatelessAuthenticationApplication.repository;

import com.example.StatelessAuthenticationApplication.model.MyUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 5/12/2021.
 */
@Repository
public interface MyUserRepository extends CrudRepository<MyUser, Long> {

    MyUser findByUsername(String username);

}
