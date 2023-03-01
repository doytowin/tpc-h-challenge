
package win.doyto.tpchchallenge.domain.nation;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.core.PageQuery;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * NationController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("nation")
public class NationController extends AbstractEIQController<NationEntity, Integer, PageQuery> {
}
