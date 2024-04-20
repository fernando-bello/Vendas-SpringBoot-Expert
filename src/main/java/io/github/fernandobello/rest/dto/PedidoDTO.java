package io.github.fernandobello.rest.dto;

import io.github.fernandobello.domain.entity.ItemPedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/*
{
    "cliente" : 1,
    "total" : 100,
    "itens" : [
        {
            "produto" : 1,
            "quantidade" : 10
        }
    ]
}
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {//Data Transfer Object
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;

}
