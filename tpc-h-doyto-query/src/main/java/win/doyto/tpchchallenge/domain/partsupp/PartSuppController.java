package win.doyto.tpchchallenge.domain.partsupp;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.web.controller.AbstractEIQController;
import win.doyto.query.web.response.JsonBody;

/**
 * PartSuppController
 *
 * @author f0rb on 2023/2/24
 */
@JsonBody
@RestController
@RequestMapping("partsupp")
public class PartSuppController extends AbstractEIQController<PartSuppEntity, PartSuppKey, PartSuppQuery> {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(PartSuppKey.class, new PartSuppKeyEditor());
    }
}
