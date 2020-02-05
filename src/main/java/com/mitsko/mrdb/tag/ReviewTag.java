package com.mitsko.mrdb.tag;

import com.mitsko.mrdb.entity.Review;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ReviewTag extends TagSupport {
    private HashMap<String, Review> reviewHashMap;
    private HashMap<String, Integer> usersRatingHashMap;
    private String star;

    public HashMap<String, Review> getReviewHashMap() {
        return reviewHashMap;
    }

    public void setReviewHashMap(HashMap<String, Review> reviewHashMap) {
        this.reviewHashMap = reviewHashMap;
    }

    public HashMap<String, Integer> getUsersRatingHashMap() {
        return usersRatingHashMap;
    }

    public void setUsersRatingHashMap(HashMap<String, Integer> usersRatingHashMap) {
        this.usersRatingHashMap = usersRatingHashMap;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    @Override
    public int doStartTag() throws JspException {
        Set<Map.Entry<String, Review>> reviewSet = reviewHashMap.entrySet();
        try {
            pageContext.getOut().print("<table class=\"table table-striped\">");


            for (Map.Entry<String, Review> entry : reviewSet) {
                String login = entry.getKey();
                login = login.replaceAll("\\s+","");
                pageContext.getOut().print("<tr><td>");
                pageContext.getOut().print(login);
                int rating = usersRatingHashMap.get(entry.getKey());
                outStars(rating);
                pageContext.getOut().print("</td></tr>");
                pageContext.getOut().print("<tr><td>");
                pageContext.getOut().print(entry.getValue().getReview());
                pageContext.getOut().print("</td></tr>");
            }

            pageContext.getOut().print("</table>");
        } catch (IOException ex) {

        }
        return SKIP_BODY;
    }

    private void outStars(int rating) {
        try {
            for (int i = 0; i < rating; i++) {
                pageContext.getOut().print(star);
            }
        } catch (IOException ex) {

        }
    }
}
