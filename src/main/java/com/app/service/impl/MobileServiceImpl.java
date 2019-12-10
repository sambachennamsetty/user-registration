package com.app.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.app.config.MySqlDbConfig;
import com.app.model.Mobile;
import com.app.service.IMobileService;
import java.sql.Statement;

@Service
public class MobileServiceImpl implements IMobileService {

	@Override
	public void mobileBatchUpdate(List<Mobile> mobiles) throws SQLException {

		try (Connection con = MySqlDbConfig.getConnection(); Statement stmt = con.createStatement()) {
			int i=1;
			for (Mobile mobile : mobiles) {
				String qry = "insert into mobile(id,name) values('"+ i++ +"','" + mobile.getName() + "')";
				stmt.addBatch(qry);
			}
			stmt.executeBatch();
		}
	}

}
