package com.app.service;

import java.sql.SQLException;
import java.util.List;

import com.app.model.Mobile;

public interface IMobileService {

	public void mobileBatchUpdate(List<Mobile> mobiles) throws SQLException;
}
