package ctag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;

public class ReportTag implements Tag {
    private PageContext pageContext;
    private Tag parentTag;
    private String y;
    @Override
    public void setPageContext(PageContext pc) {
        pageContext = pc;
    }

    @Override
    public void setParent(Tag t) {
        parentTag = t;
    }
    
    @Override
    public Tag getParent() {
        return parentTag;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
    
    @Override
    public int doStartTag() throws JspException {
        try {
            JspWriter out = pageContext.getOut();
            out.print(getY() + " Report");
        } catch (Exception e) {
        }
        return Tag.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return Tag.EVAL_PAGE;
    }

    @Override
    public void release() {
    }
    
}
