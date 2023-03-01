package win.doyto.tpchchallenge.domain.partsupp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import win.doyto.query.annotation.ForeignKey;
import win.doyto.query.core.CompositeId;
import win.doyto.query.entity.Persistable;
import win.doyto.tpchchallenge.domain.part.PartEntity;
import win.doyto.tpchchallenge.domain.supplier.SupplierEntity;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Id;

/**
 * PartSuppKey
 *
 * @author f0rb on 2023/2/24
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartSuppKey implements CompositeId, Persistable<PartSuppKey> {
    @Id
    @ForeignKey(entity = PartEntity.class, field = "p_partkey")
    private Integer ps_partkey;

    @Id
    @ForeignKey(entity = SupplierEntity.class, field = "s_suppkey")
    private Integer ps_suppkey;

    @Override
    public List<Serializable> toKeys() {
        return Arrays.asList(ps_partkey, ps_suppkey);
    }

    @JsonIgnore
    @Override
    public PartSuppKey getId() {
        return new PartSuppKey(ps_partkey, ps_suppkey);
    }

    @Override
    public void setId(PartSuppKey partsuppKey) {
        this.ps_partkey = partsuppKey.ps_partkey;
        this.ps_suppkey = partsuppKey.ps_suppkey;
    }
}
