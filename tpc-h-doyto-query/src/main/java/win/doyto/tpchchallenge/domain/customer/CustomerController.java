package win.doyto.tpchchallenge.domain.customer;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.core.PageQuery;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * CustomerController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("customer")
public class CustomerController extends AbstractEIQController<CustomerEntity, Integer, PageQuery> {
}
