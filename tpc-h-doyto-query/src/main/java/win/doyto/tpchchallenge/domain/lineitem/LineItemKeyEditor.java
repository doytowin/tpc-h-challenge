package win.doyto.tpchchallenge.domain.lineitem;

import java.beans.PropertyEditorSupport;

/**
 * LineItemKeyEditor
 *
 * @author f0rb on 2023/2/25
 */
public class LineItemKeyEditor extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            int index = text.indexOf("-");
            Integer orderKey = Integer.valueOf(text.substring(0, index));
            Integer lineNumber = Integer.valueOf(text.substring(index + 1));
            super.setValue(new LineItemKey(orderKey, lineNumber));
        } catch (NumberFormatException nfe) {
            throw new IllegalArgumentException("The id format should be: %d-%d", nfe);
        }
    }
}
