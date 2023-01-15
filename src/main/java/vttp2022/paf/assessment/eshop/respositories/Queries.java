package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    public static final String SQL_FIND_CUSTOMER_BY_NAME =
    """
            select * from customers where name = ?
            """;
    
}
