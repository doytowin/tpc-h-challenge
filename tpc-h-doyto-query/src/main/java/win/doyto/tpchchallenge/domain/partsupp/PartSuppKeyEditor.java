package win.doyto.tpchchallenge.domain.partsupp;

import java.beans.PropertyEditorSupport;

/**
 * PartSuppKeyEditor
 *
 * @author f0rb on 2023/2/24
 */
public class PartSuppKeyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            int index = text.indexOf("-");
            Integer partKey = Integer.valueOf(text.substring(0, index));
            Integer suppKey = Integer.valueOf(text.substring(index + 1));
            super.setValue(new PartSuppKey(partKey, suppKey));
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("The id format should be: %d-%d", nfe);
        }
    }
}
