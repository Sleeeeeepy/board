package com.jungle.board.security.xss;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document.OutputSettings;
import org.jsoup.safety.Safelist;
import org.springframework.stereotype.Component;

@Component
public class PostParser {
    private final OutputSettings outputSettings;

    public PostParser() {
        this.outputSettings = new OutputSettings();
        outputSettings.prettyPrint(false);
    }

    public String filterContent(String html) {
        return Jsoup.clean(html, "", Safelist.relaxed(), outputSettings);
    }

    public String fetchThumbnail(String html) {
        var elem = Jsoup.parse(html).select("img").first();
        if (elem != null) {
            return elem.attr("src");
        }

        return "";
    }
}
