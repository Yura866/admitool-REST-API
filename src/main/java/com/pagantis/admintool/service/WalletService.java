package com.pagantis.admintool.service;
import java.util.List;
import org.springframework.stereotype.Service;

import com.pagantis.admintool.dto.WalletDTO;

@Service
public interface WalletService {		
	 List findAllWalletsByUserId(Long userId);		
	 WalletDTO updateWallet(Long id, WalletDTO wallet);
	 WalletDTO findById(Long id);
	 void deleteWallet(Long id);	
	 WalletDTO sendCoinsTo(String hashFrom, String hashTo, double value);
	 WalletDTO findByAddress(String address);	 
	 WalletDTO createWallet(Long id, String name);
	
}
