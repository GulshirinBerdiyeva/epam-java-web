package com.epam.task.fifth.entity;

public class Lexeme implements Component{
    private String lexeme;
    private LexemeType type;

    public Lexeme(String lexeme, LexemeType type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public LexemeType getType() {
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

        Lexeme lexeme1 = (Lexeme) o;

        if (lexeme != null ? !lexeme.equals(lexeme1.lexeme) : lexeme1.lexeme != null) {
            return false;
        }

        return type == lexeme1.type;
    }

    @Override
    public int hashCode() {
        int result = lexeme != null ? lexeme.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "lexeme='" + lexeme +
                "', type='" + type + "'";
    }

}