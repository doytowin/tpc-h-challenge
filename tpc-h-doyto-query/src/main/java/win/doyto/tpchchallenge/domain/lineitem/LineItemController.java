package win.doyto.tpchchallenge.domain.lineitem;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import win.doyto.query.web.controller.AbstractEIQController;

/**
 * LineItemController
 *
 * @author f0rb on 2023/2/24
 */
@RestController
@RequestMapping("lineitem")
public class LineItemController extends AbstractEIQController<LineItemEntity, LineItemKey, LineitemQuery> {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LineItemKey.class, new LineItemKeyEditor());
    }
}