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

        public static final String SQL_INSERT_INTO_ORDER_STATUS = 
        """
                insert into order_status (order_id, delivery_id, status, status_update)
                values (?,?,?,?)
                """;

        public static final String SQL_SELECT_ORDER_STATUS_COUNTS =
        """
                select status, count(*) as count
                from order_status s
                join orders o
                on s.order_id = o.order_id
                where name = ?
                group by status
                
                """;
                   
}
