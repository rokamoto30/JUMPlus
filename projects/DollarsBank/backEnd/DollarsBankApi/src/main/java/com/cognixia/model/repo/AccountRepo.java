package com.cognixia.model.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognixia.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Integer> {
	@Query(value="SELECT a.* FROM account a WHERE a.user_id = ?1 AND a.name =?2", nativeQuery = true)
	public Optional<Account> getAccount(Integer id, String name);
	
	@Query(value="SELECT a.* FROM account a WHERE a.user_id = ?1", nativeQuery = true)
	public List<Account> getAccounts(Integer id);
}
