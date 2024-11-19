package com.example.rssApp;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Controller
public class RssAppController {

   @GetMapping("/")
    public String showFeed(Model model) {
        String RSS_FEED_URL = "https://rss.nytimes.com/services/xml/rss/nyt/Technology.xml";
        List<RssItem> rssItems = fetchRssFeed(RSS_FEED_URL);

        model.addAttribute("rssItems", rssItems);
        return "rssView"; 
    }

    private List<RssItem> fetchRssFeed(String url) {
        List<RssItem> items = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new URL(url).openStream());
            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("item");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String title = element.getElementsByTagName("title").item(0).getTextContent();
                    String link = element.getElementsByTagName("link").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();

                    String image = "";
                    if (element.getElementsByTagName("media:content").getLength() > 0) {
                        image = element.getElementsByTagName("media:content").item(0).getAttributes().getNamedItem("url").getTextContent();
                    }
                    
                    items.add(new RssItem(title, link, description, image));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return items;
    }


    class RssItem {
        private String title;
        private String link;
        private String description;
        private String image;
        
    
        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public RssItem(String title, String link, String description, String image) {
            this.title = title;
            this.link = link;
            this.description = description;
            this.image = image;
        }
    
        // Getters
        public String getTitle() {
            return title;
        }
    
        public String getLink() {
            return link;
        }
    
        public String getDescription() {
            return description;
        }
    }
}
