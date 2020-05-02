package com.pagantis.admintool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.pagantis.admintool.dto.WalletDTO;
import com.pagantis.admintool.service.WalletService;
import com.pagantis.admintool.service.WalletServiceImpl;



@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = {"http://localhost:8081" })
public class WalletController {
	
	@Autowired 
	WalletService walletService;
   
	/**
    * Get all user wallets by user id
    * @return list of wallets
    */
	@GetMapping("/wallets/user/{id}")
	public ResponseEntity <List<WalletDTO>> findAllWalletsByUserId(@PathVariable (value = "id") Long userId) {
		 List<WalletDTO> wallets = walletService.findAllWalletsByUserId(userId);			
		return new ResponseEntity < >(wallets, HttpStatus.OK);
	}
	
	/**
	    * Get one wallet by id
	    * @return list of wallets
	    */
	@GetMapping("/wallet/{id}")
	public ResponseEntity <WalletDTO> findWalletById( @PathVariable (value = "id") Long id) {	
		final WalletDTO wallet = walletService.findById(id);
		return new ResponseEntity <>(wallet, HttpStatus.OK);
	}
	
		/**
	    * create new wallet
	    * @return list of wallets  
	    */ 
	@PostMapping(value = "/wallet/create/{userId}/walletName/{name}")
	public ResponseEntity <WalletDTO> createWallet(@PathVariable (value = "userId") Long id, @PathVariable (value = "name") String name) {
		final WalletDTO wallet = walletService.createWallet(id, name);
		return new ResponseEntity < >(wallet, HttpStatus.CREATED);
	}
		
		/**
	    * transfer coins between wallets
	    * @return list of wallets
	    */
	
	@PostMapping(value = "/wallet/transfer/from/{from}/to/{to}/amount/{amount}")
	public ResponseEntity <WalletDTO> transferCoins(@PathVariable (value = "from") String from, @PathVariable(value="to") String to, @PathVariable(value="amount") double amount) {
	
		final WalletDTO wallet = walletService.sendCoinsTo(from, to, amount);
		return new ResponseEntity < >(wallet, HttpStatus.OK);
	}	
	
	/**
     * Deleted the wallet by id 
     * @param id
     * @return
     */	
	
	@DeleteMapping("/wallet/{id}")
	public ResponseEntity <String > deleteWallet( @PathVariable (value = "id") Long id) {	
		walletService.deleteWallet(id);
		return new ResponseEntity <>( HttpStatus.OK);
	}

}
