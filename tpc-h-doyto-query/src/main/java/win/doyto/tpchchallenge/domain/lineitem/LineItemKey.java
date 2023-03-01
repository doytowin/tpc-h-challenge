package win.doyto.tpchchallenge.domain.lineitem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.doyto.query.annotation.ForeignKey;
import win.doyto.query.core.CompositeId;
import win.doyto.query.entity.Persistable;
import win.doyto.tpchchallenge.domain.orders.OrdersEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;

/**
 * LineItemKey
 *
 * @author f0rb on 2023/2/25
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LineItemKey implements CompositeId, Persistable<LineItemKey> {

    @Id
    @ForeignKey(entity = OrdersEntity.class, field = "o_orderkey")
    private Integer l_orderkey;

    @Id
    private Integer l_linenumber;

    @JsonIgnore
    @Override
    public LineItemKey getId() {
        return new LineItemKey(this.l_orderkey, this.l_linenumber);
    }

    @Override
    public void setId(LineItemKey lineItemKey) {
        this.l_orderkey = lineItemKey.l_orderkey;
        this.l_linenumber = lineItemKey.l_linenumber;
    }

    @Override
    public List<Serializable> toKeys() {
        return Arrays.asList(l_orderkey, l_linenumber);
    }
}
