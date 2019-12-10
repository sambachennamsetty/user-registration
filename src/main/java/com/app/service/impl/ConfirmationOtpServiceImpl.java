package com.app.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.ConfirmationOtp;
import com.app.repo.ConfirmationOtpRepository;
import com.app.service.IConfirmationOtpService;
@Service
public class ConfirmationOtpServiceImpl implements IConfirmationOtpService {

	@Autowired
	private ConfirmationOtpRepository otpRepo;
	
	@Override
	public ConfirmationOtp findConfirmationOtpByOtp(Integer otp) {
		return otpRepo.findByOtpNumber(otp);
	}

	@Override
	public void saveConfirmationOtp(ConfirmationOtp confirmationOtp) {
		otpRepo.save(confirmationOtp);
		
	}

	@Override
	public ConfirmationOtp findConfirmationOtpByUser(Long userId) {
		return otpRepo.findByUserUserid(userId);
		 
	}

	@Override
	public void clearOtp(Long otpId) {
		otpRepo.clearOtpAfterSuccess(otpId);
	}
	

}
