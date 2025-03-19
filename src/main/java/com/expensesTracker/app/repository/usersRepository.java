package com.expensesTracker.app.repository;

import com.expensesTracker.app.entities.Roles;
import com.expensesTracker.app.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface usersRepository extends JpaRepository<Users, Integer> {
    public List<Users> findByUsernameContainingIgnoreCase(String username);
    public List<Users> findByEmailContainingIgnoreCase(String email);
    @Query("SELECT r FROM Roles r JOIN r.users u WHERE u.userId = :userId")
    public List<Roles> findRolesByUserId(@Param("userId") int userId);
}
