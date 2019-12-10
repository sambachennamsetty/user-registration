package com.app.service;

import com.app.model.ConfirmationOtp;

public interface IConfirmationOtpService {

	ConfirmationOtp findConfirmationOtpByOtp(Integer otp);
	
	ConfirmationOtp findConfirmationOtpByUser(Long userId);
	
	void saveConfirmationOtp(ConfirmationOtp confirmationOtp);
	
	void clearOtp(Long otpId);
}
