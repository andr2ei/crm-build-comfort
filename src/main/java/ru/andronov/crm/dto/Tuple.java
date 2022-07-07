package ru.andronov.crm.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Tuple<T> {
    private final T first;
    private final T second;
}
