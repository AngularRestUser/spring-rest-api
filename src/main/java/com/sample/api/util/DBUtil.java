package com.sample.api.util;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class DBUtil {
	public void setStringQueryIParam( CallableStatement cstmt, int paramIndex, String value ) throws SQLException {
		if ( ( value == null ) || ( value.trim().length() == 0 )) {
			cstmt.setNull( paramIndex, java.sql.Types.CHAR );
		}
		else {
			cstmt.setString( paramIndex, value.trim() );
		}
	}

	public void setIntQueryIParam( CallableStatement cstmt, int paramIndex, Integer value) throws SQLException {
        if ( value == null ) {
            cstmt.setNull( paramIndex, java.sql.Types.NUMERIC );
        }
        else {
            cstmt.setInt( paramIndex, value.intValue() );
        }
    }
	public void setDoubleQueryIParam( CallableStatement cstmt, int paramIndex, Double value) throws SQLException { 
        if ( value == null ) {
            cstmt.setNull( paramIndex, java.sql.Types.DOUBLE );
        }
        else {
            cstmt.setBigDecimal( paramIndex, BigDecimal.valueOf(value));
        }
    }
	
	
}
