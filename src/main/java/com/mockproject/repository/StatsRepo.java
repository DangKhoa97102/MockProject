package com.mockproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mockproject.entity.Orders;

@Repository
public interface StatsRepo extends JpaRepository<Orders, Long>{
	
	@Query(value = "{CALL sp_getTotalPricePerMonth(:month1, :year)}", nativeQuery = true)
	String getTotalPricePerMonth(@Param("month1") String month, @Param("year") String year);
}


// Viết repository không dùng bất cứ Entity nào thì ta dùng:
// jdbc template