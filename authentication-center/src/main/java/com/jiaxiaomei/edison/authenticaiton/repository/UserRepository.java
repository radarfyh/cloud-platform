package com.jiaxiaomei.edison.authenticaiton.repository;

import com.jiaxiaomei.edison.authenticaiton.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}
