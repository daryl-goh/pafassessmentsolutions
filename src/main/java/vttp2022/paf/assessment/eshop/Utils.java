package vttp2022.paf.assessment.eshop;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import vttp2022.paf.assessment.eshop.models.Customer;

public class Utils {

    public static Customer createCustFromRs(SqlRowSet rs) {
        Customer c = new Customer();
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setEmail(rs.getString("email"));
        return c;
    }
    
}
