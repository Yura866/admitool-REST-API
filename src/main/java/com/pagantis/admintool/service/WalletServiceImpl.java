package com.pagantis.admintool.service;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.pagantis.admintool.dao.UserRepository;
import com.pagantis.admintool.dao.WalletRepository;
import com.pagantis.admintool.dto.WalletDTO;
import com.pagantis.admintool.exception.ResourceNotFoundException;
import com.pagantis.admintool.exception.TransferFailureException;
import com.pagantis.admintool.model.User;
import com.pagantis.admintool.model.Wallet;

import org.apache.commons.codec.digest.DigestUtils;

@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	WalletRepository walletRepo;	

	@Autowired
	UserRepository userRepo;

	@Override
	public List <WalletDTO> findAllWalletsByUserId(Long userId) {		
		List<Wallet>wallets = new ArrayList<Wallet>();	    

		walletRepo.findByUserId(userId).forEach(wallets::add);	   
		if(wallets.isEmpty())
			throw new ResourceNotFoundException("Wallets for user [" + userId + "] was not found");

		List<WalletDTO> walletList = new ArrayList<WalletDTO>();
		for (Wallet walletModel : wallets) {
			WalletDTO walletDTO = new WalletDTO();
			BeanUtils.copyProperties(walletModel, walletDTO);
			walletList.add(walletDTO);
		}
		return walletList;
	}
	
	@Override
	public WalletDTO createWallet(Long userId, String walletName) {
		WalletDTO walletDto = new WalletDTO();
		Wallet wallet = new Wallet();		
		try {
			Optional<User> user = userRepo.findById(userId);		

			wallet.setBalance(0.00);
			wallet.setName(walletName);
			wallet.setUser(user.get());		
			wallet.setAddress(DigestUtils.sha256Hex(walletName+(Math.random()*(3-1))));
			wallet.setPrivateKey(DigestUtils.sha256Hex(walletName+Long.toString(userId)));
			
			wallet = walletRepo.save(wallet);			
			BeanUtils.copyProperties(wallet, walletDto);
			
			walletDto.setMessage("Created successful");
			
			
		} catch (Exception e) {
			
			walletDto.setMessage("Error while create new wallet !!!\r\n" + 
					" Please try do it later .");
		}
		return walletDto;
	}

	@Override
	public WalletDTO findById(Long id) {

		Optional<Wallet> wallet = walletRepo.findById(id);		
		if(!wallet.isPresent())
			throw new ResourceNotFoundException("Wallet with ID [" + id + "] not found");		

		WalletDTO walletDTO = new WalletDTO();
		BeanUtils.copyProperties(wallet.get(), walletDTO);        
		return walletDTO;		

	}

	@Override
	public void deleteWallet(Long id) {
		if(!walletRepo.existsById(id)) {
			throw new ResourceNotFoundException("Wallet with ID [" + id + "] not found");
		}		
		walletRepo.deleteById(id);		
	}

	@Override
	public WalletDTO updateWallet(Long id, WalletDTO walletDTO) {		

		if(walletRepo.existsById(id)) {	

			Wallet existingWalet = walletRepo.findById(id).get();			
			existingWalet.setAddress(walletDTO.getAddress());
			existingWalet.setName(walletDTO.getName());
			existingWalet.setBalance(walletDTO.getBalance());

			Wallet updatedWallet = walletRepo.save(existingWalet);			
			BeanUtils.copyProperties(updatedWallet, walletDTO);    

			return walletDTO;		

		}else		
			throw new ResourceNotFoundException("Wallet with ID [" + id + "] not found");		


	}


	@Override
	@Transactional
	public WalletDTO sendCoinsTo(String from, String to, double amount) {
		Wallet sendTo = null;
		Wallet sendFrom = null;
		WalletDTO sendFromDTO= null;
		WalletDTO sendtoDTO =null;
		final String fromWalletAddress = from;
		final String toWalletAddress = to;
		final Double coinEmount = amount;
		
		try {
			 sendTo = new Wallet();
			 sendFrom = new Wallet();	
			
			//check addresses
			if(fromWalletAddress == null || toWalletAddress == null) {
				throw new TransferFailureException("The destination or sender address not present! ");
			}
			else {				
				sendFromDTO = findByAddress(fromWalletAddress);							 

				if (sendFromDTO.getBalance()<coinEmount)
					throw new TransferFailureException("Sorry but you don't have amount available for this transfer, check your wallet !!! ");

				sendtoDTO = findByAddress(toWalletAddress);	
				
				double balanceF = sendFromDTO.getBalance();					
				sendFromDTO.setBalance(balanceF-coinEmount);			
				double balanceTo = sendtoDTO.getBalance();
				sendtoDTO.setBalance(balanceTo+coinEmount);

				BeanUtils.copyProperties(sendFromDTO, sendFrom);
				BeanUtils.copyProperties(sendtoDTO, sendTo); 

				Wallet updatedWalletFrom = walletRepo.save(sendFrom);					
				Wallet updatedWalletTo = walletRepo.save(sendTo);

				BeanUtils.copyProperties(updatedWalletFrom, sendFromDTO); 
				sendFromDTO.setMessage("Transfer successful");

			}		
		} catch (Exception e) {	
			e.getStackTrace();
			throw new TransferFailureException( e.getMessage(), e );			
		}
		return sendFromDTO;
	}



	@Override
	public WalletDTO findByAddress(String address) {

		Optional<Wallet> walletModel = walletRepo.findByAddress(address);			

		if(!walletModel.isPresent())
			throw new ResourceNotFoundException("Wallet with address [" + address + "] not found");		

		WalletDTO walletDTO = new WalletDTO();
		BeanUtils.copyProperties(walletModel.get(), walletDTO);        
		return walletDTO;	

	}
}
