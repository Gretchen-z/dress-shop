package ru.gretchen.dressshop.model;

import lombok.*;
import ru.gretchen.dressshop.model.Enumeration.Color;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DressEntity {
    private Long id;
    private Color color;
    private Long price;
    private Long inStock;
}
