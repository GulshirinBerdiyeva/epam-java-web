package com.epam.task.web.project.tag;

import com.epam.task.web.project.localizer.Localizer;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class PaginationTag extends TagSupport {

    private static final String PAGE = "page";
    private static final String ID_NAME = "id=\"current\"";
    private static final String NEXT = "local.pagination.next";
    private static final String PREV = "local.pagination.prev";

    private String action;
    private  int totalPageCount;
    private  int viewPageCount;
    private  int startIndex;
    private  int endIndex;

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
            throw new JspException("Minimum page count should be grater than zero!");
        }

        if (totalPageCount < viewPageCount) {
            viewPageCount = totalPageCount;
        }

        startIndex = 1;

        return SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        String pageValue = pageContext.getRequest().getParameter(PAGE);

        int page = (pageValue != null) ? Integer.parseInt(pageValue) : 1;

        endIndex = Math.min(page + viewPageCount - 1, totalPageCount);

        if (endIndex == totalPageCount) {
            startIndex = endIndex - viewPageCount + 1;
        } else {
            startIndex = page;
        }

        Localizer localizer = new Localizer(pageContext.getSession());
        JspWriter out = pageContext.getOut();
        try {
            if (startIndex > 1) {
                String prev = localizer.localize(PREV);
                out.write(getLink(action, page - 1, false, prev));
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
                String next = localizer.localize(NEXT);

                out.write(getLink(action, nextPage, false, next));
            }

            out.flush();

        } catch (IOException e) {
            throw new JspException(e.getMessage(), e);
        }

        return EVAL_PAGE;
    }

    private String getLink(final String action, final int page, final boolean isCurrentPage, final String desc) {
        String id = isCurrentPage ? ID_NAME : "";

        String link = "<a " + id + " href=\"" + action + "&page=" + page + "\">" + desc + "&nbsp;</a>";

        return link;
    }

}
