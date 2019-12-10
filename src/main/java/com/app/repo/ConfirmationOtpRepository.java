package com.app.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.app.model.ConfirmationOtp;

public interface ConfirmationOtpRepository extends JpaRepository<ConfirmationOtp, Long> {

	ConfirmationOtp findByOtpNumber(Integer otpNumber);

	ConfirmationOtp findByUserUserid(Long userId);

	@Modifying
	@Transactional
	@Query("DELETE FROM ConfirmationOtp where otpId=?1")
	void clearOtpAfterSuccess( Long otpId);

}
