package win.doyto.tpchchallenge.domain.part;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * PartController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("part")
public class PartController extends AbstractEIQController<PartEntity, Integer, PartQuery> {
}
