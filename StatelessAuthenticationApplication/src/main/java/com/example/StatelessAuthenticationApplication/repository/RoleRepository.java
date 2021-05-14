/**
 * 
 */
package com.example.StatelessAuthenticationApplication.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.StatelessAuthenticationApplication.model.Role;

/**
 * @author: Praveenkumar
 * Created date: May 13, 2021
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

}
