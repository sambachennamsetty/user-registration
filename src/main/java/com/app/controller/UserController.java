package com.app.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.config.EmailConfig;
import com.app.model.ConfirmationOtp;
import com.app.model.User;
import com.app.service.IConfirmationOtpService;
import com.app.service.IUserService;

@RestController
public class UserController {

	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	@Autowired
	private IUserService userService;

	@Autowired
	private IConfirmationOtpService otpService;

	@Autowired
	private EmailConfig sendEmail;

	// 1. Saving the User
	@PostMapping(value = "/register")
	public String saveUser(@RequestBody User user) {
		// checking the email id whether it exist or not
		User existingUser = userService.findUserByEmail(user.getEmailId());
		if (existingUser != null) {
			return "Email Already Exists";
		} else {
			// Saving the user
			user.setPassword(pwdEncoder.encode(user.getPassword()));
			userService.saveUser(user);
			// Assigning date and generating OTP
			ConfirmationOtp confirmationOtp = new ConfirmationOtp(user);
			// saving OTP details to OTP table
			otpService.saveConfirmationOtp(confirmationOtp);
			// sending mail
			sendEmail.mailSend(user.getEmailId(), confirmationOtp.getOtpNumber(), "Trackerwave Registration OTP ");
			return "Confirmation OTP has been sent to your mail:" + user.getEmailId();
		}
	}

	@PostMapping(value = "/confirm-account")
	public String confirmUserAccount(@RequestParam Integer otp, @RequestParam String email) {
		// checking user entered details or not
		if (otp != null && email != null) {
			// checking OTP from table and getting ConfirmationOtp object
			ConfirmationOtp confirmationOtp = otpService.findConfirmationOtpByOtp(otp);
			if (confirmationOtp != null) {
				// If confirmation object exists then it gets user object using emailId
				User user = userService.findUserByEmail(confirmationOtp.getUser().getEmailId());
				// checking object's email, OTP with user entered OTP, email
				if (email.equals(user.getEmailId()) && otp.equals(confirmationOtp.getOtpNumber())) {
					// setting is_enabled to '1' in Db
					user.setEnabled(true);
					// again saving user object after set to '1'
					userService.saveUser(user);
					otpService.clearOtp(confirmationOtp.getOtpId());
					return "Account verified Successfully";
				} else {
					return "You entered wrong Email Address";
				}
			} else {
				return "You entred wrong OTP";
			}
		} else {
			return "Please Enter OTP and Email Address";
		}

	}

	@PostMapping(value = "forgot-password")
	public String forgotPassword(@RequestParam String emailId) {
		// checking the email id whether it exists or not
		User existingUser = userService.findUserByEmail(emailId);
		if (existingUser != null) {
			if (existingUser.isEnabled()) {
				ConfirmationOtp otp = new ConfirmationOtp(existingUser);
				otp.setOtpNumber(100000 + new Random().nextInt(900000));
				otpService.saveConfirmationOtp(otp);
				// sending mail
				sendEmail.mailSend(otp.getUser().getEmailId(), otp.getOtpNumber(), "Trackerwave forgot OTP");
				return "OTP sent to your mail id:" + otp.getUser().getEmailId();
			} else {
				return "Your account was not verified. First verify the account..!";
			}
		} else {
			return "Email not exists..!";
		}
	}

	@PostMapping(value = "/reset-password")
	public String restPassword(@RequestParam Integer otp, @RequestParam String email, @RequestParam String newPassword,
			@RequestParam String reEnterNewPassword) {
		if (otp != null && email != null && newPassword != null && reEnterNewPassword != null
				&& newPassword.equals(reEnterNewPassword)) {
			ConfirmationOtp confirmationOtp = otpService.findConfirmationOtpByOtp(otp);
			if (confirmationOtp != null) {
				User user = userService.findUserByEmail(confirmationOtp.getUser().getEmailId());
				if (email.equals(user.getEmailId()) && otp.equals(confirmationOtp.getOtpNumber())) {
					user.setPassword(newPassword);
					userService.saveUser(user);
					otpService.clearOtp(confirmationOtp.getOtpId());
					return "Password Reset Successfully";
				} else {
					return "You entered wrong email address";
				}
			} else {
				return "You entered wrong OTP";
			}
		} else {
			return "Please fill all the details or You Entered Passwords are not same..!";
		}

	}
}