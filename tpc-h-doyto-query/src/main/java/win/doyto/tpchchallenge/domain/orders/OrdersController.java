package win.doyto.tpchchallenge.domain.orders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.core.PageQuery;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * OrdersController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("orders")
public class OrdersController extends AbstractEIQController<OrdersEntity, Integer, PageQuery> {
}