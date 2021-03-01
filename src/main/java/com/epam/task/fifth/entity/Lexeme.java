package com.epam.task.fifth.entity;

public class Lexeme implements Component{
    private enum LexemeType {
        WORD, EXPRESSION
    }
    private String lexeme;
    private LexemeType type;

    private Lexeme(String lexeme, LexemeType type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public static Lexeme word(String lexeme) {
        return new Lexeme(lexeme, LexemeType.WORD);
    }

    public static Lexeme expression(String lexeme) {
        return new Lexeme(lexeme, LexemeType.EXPRESSION);
    }

    public String getLexeme() {
        return lexeme;
    }

    public LexemeType getType() {
        return type;
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
        return lexeme;
    }

}