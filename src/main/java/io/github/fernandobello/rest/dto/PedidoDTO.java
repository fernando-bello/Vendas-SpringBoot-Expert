package io.github.fernandobello.rest.dto;

import io.github.fernandobello.domain.entity.ItemPedido;
import io.github.fernandobello.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "Informe o código do cliente.")
    private Integer cliente;
    @NotNull(message = "Campo Total do pedido é obrigatório.")
    private BigDecimal total;
    @NotEmptyList(message = "O pedido não pode ser realizado sem itens.")
    private List<ItemPedidoDTO> itens;

}
