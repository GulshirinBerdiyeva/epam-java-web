package com.epam.task.web.project.tag;

import com.epam.task.web.project.locale.TagLocalizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Optional;

public class PaginationTag extends TagSupport {

    private static final String PAGE = "page";
    private static final String CURRENT_ID = "id=\"current\"";
    private static final String LOCALE = "locale";
    private static final String NEXT = "locale.pagination.next";
    private static final String PREV = "locale.pagination.prev";

    private String action;
    private  int totalPageCount;
    private  int viewPageCount;
    private  int startIndex;
    private  int endIndex;

    private final TagLocalizer tagLocalizer;

    public PaginationTag() {
        super();

        tagLocalizer = new TagLocalizer();
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public void setViewPageCount(int viewPageCount) {
        this.viewPageCount = viewPageCount;
    }

    @Override
    public int doStartTag() throws JspException {
        if (totalPageCount == 0 || viewPageCount < 1) {
            throw new JspException("Minimum page count should be grater than zero");
        }

        if (totalPageCount < viewPageCount) {
            viewPageCount = totalPageCount;
        }

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        Integer page = (Integer) pageContext.getRequest().getAttribute(PAGE);

        if (page == null) {
            page = 1;
        }

        endIndex = Math.min(page + viewPageCount - 1, totalPageCount);

        startIndex = (endIndex == totalPageCount) ? (endIndex - viewPageCount + 1) : page;

        String locale = (String) pageContext.getSession().getAttribute(LOCALE);

        JspWriter out = pageContext.getOut();

        try {
            if (startIndex > 1) {
                Optional<String> prev = tagLocalizer.localize(PREV, locale);
                out.write(getLink(action, page - 1, false, prev.get()));
            }

            for (int i = startIndex; i <= endIndex; i++) {

                if ( (page == 0 && i == 1) || (page == i) ) {
                    out.write(getLink(action, i, true, String.valueOf(i)));
                } else {
                    out.write(getLink(action, i, false, String.valueOf(i)));
                }
            }

            if (endIndex < totalPageCount) {
                int nextPage = (page != 0) ? page + 1 : 2;
                Optional<String> next = tagLocalizer.localize(NEXT, locale);

                out.write(getLink(action, nextPage, false, next.get()));
            }

            out.flush();
        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    private String getLink(final String action, final int page, final boolean isCurrentPage, final String desc) {
        String id = isCurrentPage ? CURRENT_ID : "";

        String link = "<a " + id + " href=\"" + action + "&page=" + page + "\">" + desc + "&nbsp;</a>";

        return link;
    }

}
