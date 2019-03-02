package com.ramostear.persistence.repo;

import com.ramostear.persistence.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by ramostear on 2019/3/3 0003.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    List<User> findAllByUsername(String username);
}
