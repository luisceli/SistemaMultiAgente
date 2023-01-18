/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Services;

import java.io.Serializable;

/**
 *
 * @author LENOVO
 */
public class Post implements Serializable{
    private String id;
    private String title;
    private String link;
    private String snippet;

    public String getId() {
        return id;
    }

    public Post(String id, String title, String link, String snippet) {
        this.id = id;
        this.title = title;
        this.link = link;
        this.snippet = snippet;
    }
        
 
    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    @Override
    public String toString() {
        return "Post{" + "title=" + title + ", link=" + link + ", snippet=" + snippet + '}';
    }
}
