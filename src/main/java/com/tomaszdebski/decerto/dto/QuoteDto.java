package com.tomaszdebski.decerto.dto;

public class QuoteDto {

    private long id;
    private String authorFirstName;
    private String authorLastName;
    private String content;

    public QuoteDto(){}

    public QuoteDto(long id, String authorFirstName, String authorLastName, String content) {
        this.id = id;
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

        public QuoteDto build() {
            return new QuoteDto(id, authorFirstName, authorLastName, content);
        }
    }
}
