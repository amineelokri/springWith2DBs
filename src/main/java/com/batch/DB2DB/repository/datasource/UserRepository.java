package com.batch.DB2DB.repository.datasource;

import com.batch.DB2DB.entity.datasource.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
