package br.org.handmaxx.dto.endereco;

import br.org.handmaxx.model.Endereco;

public record EnderecoResponseDTO(
    String CEP,
    String logradouro,
    String numeroLote,
    String complemento,
    String localidade,
    String UF
) {
        public static EnderecoResponseDTO valueOf(Endereco endereco){
        return new EnderecoResponseDTO(endereco.getCEP(), 
                               endereco.getLogradouro(), 
                               endereco.getNumeroLote(), 
                               endereco.getComplemento(), 
                               endereco.getLocalidade(), 
                               endereco.getUF());
    }
}
