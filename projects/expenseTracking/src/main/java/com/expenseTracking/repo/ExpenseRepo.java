package com.expenseTracking.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.expenseTracking.model.Expense;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Integer>{
	@Query(value="SELECT * FROM expense e WHERE e.user_id = ?1 ORDER BY date DESC LIMIT 5", nativeQuery=true)
	public List<Expense> getTop5(Integer id);
	
	@Query(value="SELECT * FROM expense e WHERE (e.user_id = ?1 AND MONTH(e.date) = ?2 AND YEAR(e.date) = ?3) OR e.recurring = TRUE ORDER BY date", nativeQuery=true)
	public List<Expense> getMonth(Integer id, String month, String year);
	
	@Query(value="SELECT * FROM expense e WHERE e.user_id = ?1 AND YEAR(e.date) = ?2 ORDER BY date", nativeQuery=true)
	public List<Expense> getYear(Integer id, String Year);
	
}
