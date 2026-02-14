package ru.it_arch.kddd.fts

public interface Entity : Fts, Validatable {
    public val id: ValueObject
    public val content: ValueObject.Data
}
