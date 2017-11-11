package cc.openhome.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.TagSupport;

public class WhenTag extends TagSupport {
    private boolean test;
    
    public void setTest(boolean test) {
        this.test = test;
    }

    @Override
    public int doStartTag() throws JspException {
        JspTag parent = getParent();
        if (!(parent instanceof ChooseTag)) {
            throw new JspException("必須置於 choose 標籤中");
        }

        ChooseTag choose = (ChooseTag) parent;
        if (choose.isMatched() || !test) {
            return SKIP_BODY;
        }

        choose.setMatched(true);
        return EVAL_BODY_INCLUDE;
    }
}
