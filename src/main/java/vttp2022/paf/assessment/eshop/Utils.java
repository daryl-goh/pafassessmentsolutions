package vttp2022.paf.assessment.eshop;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.JsonObject;
import vttp2022.paf.assessment.eshop.models.Customer;
import vttp2022.paf.assessment.eshop.models.LineItem;

public class Utils {

    public static Customer createCustFromRs(SqlRowSet rs) {
        Customer c = new Customer();
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setEmail(rs.getString("email"));
        return c;
    }

    // public static LineItem fromJson(JsonObject jo) {
    //     LineItem l = new LineItem();
    //     l.setItem(jo.getString("item"));
    //     l.setQuantity(jo.getInt("quantity"));
    //     return l;
    // }
    
}
