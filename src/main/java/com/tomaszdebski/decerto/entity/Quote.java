package com.tomaszdebski.decerto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Quote {

    public Quote() {
    }

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "author_first_name")
    private String authorFirstName;

    @Column(name = "author_last_name")
    private String authorLastName;

    @Column(name = "content")
    private String content;

    public Quote(String authorFirstName, String authorLastName, String content) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quote quote = (Quote) o;

        if (id != quote.id) return false;
        if (authorFirstName != null ? !authorFirstName.equals(quote.authorFirstName) : quote.authorFirstName != null)
            return false;
        if (authorLastName != null ? !authorLastName.equals(quote.authorLastName) : quote.authorLastName != null)
            return false;
        return content != null ? content.equals(quote.content) : quote.content == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (authorFirstName != null ? authorFirstName.hashCode() : 0);
        result = 31 * result + (authorLastName != null ? authorLastName.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        return result;
    }

    public static QuoteBuilder builder() {
        return new QuoteBuilder();
    }

    public static class QuoteBuilder {

        private long id;
        private String authorFirstName;
        private String authorLastName;
        private String content;

        public QuoteBuilder withId(Long id) {
            this.id = id;
            return this;
        }

        public QuoteBuilder withAuthorFirstName(String authorFirstName) {
            this.authorFirstName = authorFirstName;
            return this;
        }

        public QuoteBuilder withAuthorLastName(String authorLastName) {
            this.authorLastName = authorLastName;
            return this;
        }

        public QuoteBuilder withContent(String content) {
            this.content = content;
            return this;
        }

        public Quote build() {
            return new Quote(authorFirstName, authorLastName, content);
        }
    }
}
