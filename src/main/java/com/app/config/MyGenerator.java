package com.app.config;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class MyGenerator implements IdentifierGenerator {

	@Override
	public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		String prefix = "ST-";
		String format = new SimpleDateFormat("yyyyMMdd").format(new Date());
		int random = Math.abs(new Random().nextInt());
		return prefix + format + "-" + random;
	}
}