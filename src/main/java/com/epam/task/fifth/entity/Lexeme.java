package com.epam.task.fifth.entity;

public class Lexeme implements Component{
    private String lexeme;
    private String type;

    public Lexeme(String content, String type) {
        this.lexeme = content;
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public String getType() {
        return type;
    }

    @Override
    public int countElements() {
        return 1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Lexeme)) {
            return false;
        }

        Lexeme lexemes = (Lexeme) o;

        if (lexeme != null ? !lexeme.equals(lexemes.lexeme) : lexemes.lexeme != null) {
            return false;
        }
        return type != null ? type.equals(lexemes.type) : lexemes.type == null;
    }

    @Override
    public int hashCode() {
        int result = lexeme != null ? lexeme.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return lexeme;
    }
}