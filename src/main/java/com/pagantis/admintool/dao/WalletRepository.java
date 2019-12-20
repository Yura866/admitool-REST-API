package com.pagantis.admintool.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pagantis.admintool.model.Wallet;

public interface WalletRepository extends JpaRepository<Wallet,Long> {		
	List <Wallet> findByUserId(Long id);	
	Optional<Wallet> findByAddress(String address);		
}
