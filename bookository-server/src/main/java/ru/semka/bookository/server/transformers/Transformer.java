package ru.semka.bookository.server.transformers;

public interface Transformer<I, O> {
    O transform(I input);
}
