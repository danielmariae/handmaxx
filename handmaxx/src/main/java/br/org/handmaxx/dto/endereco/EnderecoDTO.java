package br.org.handmaxx.dto.endereco;

import br.org.handmaxx.model.Endereco;

public record EnderecoDTO(
    String CEP,
    String logradouro,
    String numeroLote,
    String complemento,
    String localidade,
    String UF
) {
    public static EnderecoDTO valueOf(Endereco endereco){
        return new EnderecoDTO(endereco.getCEP(), 
                               endereco.getLogradouro(), 
                               endereco.getNumeroLote(), 
                               endereco.getComplemento(), 
                               endereco.getLocalidade(), 
                               endereco.getUF());
    }
}
