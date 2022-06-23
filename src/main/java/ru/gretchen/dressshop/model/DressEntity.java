package ru.gretchen.dressshop.model;

import lombok.*;
import ru.gretchen.dressshop.model.Enumeration.Colour;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class DressEntity {
    private Long id;
    private Colour colour;
    private Long price;
    private Long inStock;
}
