package vttp2022.paf.assessment.eshop.respositories;

public class Queries {
    public static final String SQL_FIND_CUSTOMER_BY_NAME =
    """
            select * from customers where name = ?
            """;

    public static final String SQL_INSERT_INTO_ORDERS =
    """
            insert into orders (order_id, name, order_date)
            values (?,?,?)
            """;

    public static final String SQL_INSERT_INTO_LINE_ITEMS =
    """
            insert into line_items  (order_id, item, quantity)
            values (?,?,?)
            """;
    
}
