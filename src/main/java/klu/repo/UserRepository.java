package klu.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import klu.model.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	@Query("select count(u) from User u where u.username=:uname")
	public int validateUser(@Param("uname") String uname);
	
	@Query("select count(u) from User u where u.username=:uname and u.password=:pass")
	public int validateLogin(@Param("uname") String uname,@Param("pass") String pass);
	
    Optional<User> findByUsername(String username);
    
    @Query("SELECT u.balance FROM User u WHERE u.username = :username")
    double findBalanceByUsername(String username);
}
