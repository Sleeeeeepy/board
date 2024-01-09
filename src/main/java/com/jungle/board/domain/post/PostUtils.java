package com.jungle.board.domain.post;

import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

public class PostUtils {
    
    private PostUtils() {

    }

    public static String getThumbnailUrlFromContent(String content) {
        var elem = Jsoup.parse(content).select("img").first();
        if (elem != null) {
            return elem.attr("src");
        }

        return "";
    }

    public static String filterContent(String html) {
        return Jsoup.clean(html, "", Safelist.relaxed());
    }
}