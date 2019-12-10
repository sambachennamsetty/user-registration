package com.app.model;

import java.util.Date;
import java.util.Random;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ConfirmationOtp {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long otpId;

	private Integer otpNumber;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	@OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
	@JoinColumn(nullable = false, name = "user_id")
	private User user;

	public ConfirmationOtp(User user) {
		otpNumber = 100000 + new Random().nextInt(900000);
		createdDate = new Date();
		this.user = user;
	}

}
