package win.doyto.tpchchallenge.domain.supplier;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * SupplierController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("supplier")
public class SupplierController extends AbstractEIQController<SupplierEntity, Integer, SupplierQuery> {
}