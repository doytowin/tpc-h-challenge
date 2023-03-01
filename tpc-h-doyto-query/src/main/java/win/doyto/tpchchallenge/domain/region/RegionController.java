package win.doyto.tpchchallenge.domain.region;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.core.PageQuery;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * RegionController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("region")
public class RegionController extends AbstractEIQController<RegionEntity, Integer, PageQuery> {
}