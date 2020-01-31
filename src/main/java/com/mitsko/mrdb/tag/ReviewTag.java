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
            for (Map.Entry<String, Review> entry : reviewSet) {
                int rating = usersRatingHashMap.get(entry.getKey());
                pageContext.getOut().print(entry.getKey());
                outStars(rating);
                pageContext.getOut().print("<br>" + entry.getValue().getReview() + "<br>");
            }
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
