package com.batch.DB2DB.repository.target;

import com.batch.DB2DB.entity.target.TargetUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetUserRepository extends JpaRepository<TargetUser, Long> {
}
