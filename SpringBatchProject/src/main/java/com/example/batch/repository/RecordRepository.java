package com.example.batch.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.example.batch.model.Record;

import java.time.LocalDateTime;

@Repository
public class RecordRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Record findByPrimaryKey(String CUST_ID_NO, long ACCT_NO, String NPA, String NXX, String TLN,
                                   long BL_PROD_ID, String DELETE_IND, LocalDateTime ADMIN_CRT_TMSTAMP, long BL_GRP_NO) {
        String sql = "SELECT * FROM my_table WHERE CUST_ID_NO = ? AND ACCT_NO = ? AND NPA = ? AND NXX = ? " +
                "AND TLN = ? AND BL_PROD_ID = ? AND DELETE_IND = ? AND ADMIN_CRT_TMSTAMP = ? AND BL_GRP_NO = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{CUST_ID_NO, ACCT_NO, NPA, NXX, TLN,
                    BL_PROD_ID, DELETE_IND, ADMIN_CRT_TMSTAMP, BL_GRP_NO}, (rs, rowNum) ->
                    new Record(
                            rs.getString("CUST_ID_NO"),
                            rs.getLong("ACCT_NO"),
                            rs.getString("NPA"),
                            rs.getString("NXX"),
                            rs.getString("TLN"),
                            rs.getLong("BL_PROD_ID"),
                            rs.getString("DELETE_IND"),
                            rs.getTimestamp("ADMIN_CRT_TMSTAMP").toLocalDateTime(),
                            rs.getLong("BL_GRP_NO"),
                            rs.getDate("MTN_EFF_DT").toLocalDate()
                    ));
        } catch (Exception e) {
            return null;
        }
    }
}