package ctag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;
import javax.servlet.jsp.tagext.BodyTag;
import javax.servlet.jsp.tagext.Tag;

public class SearchTag implements BodyTag {
    private PageContext pageContext;
    private Tag parentTag;
    private BodyContent bodyContent;
    private String keyword;
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

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    
    @Override
    public int doStartTag() throws JspException {
        return BodyTag.EVAL_BODY_BUFFERED;
    }

    @Override
    public void setBodyContent(BodyContent b) {
        bodyContent = b;
    }

    @Override
    public void doInitBody() throws JspException {
    }

    @Override
    public int doAfterBody() throws JspException {
        try {
            JspWriter out = bodyContent.getEnclosingWriter();
            String content = bodyContent.getString();
            content = content.replaceAll(keyword, "<font color=red>" + keyword + "</font>");
            out.print(content);
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
