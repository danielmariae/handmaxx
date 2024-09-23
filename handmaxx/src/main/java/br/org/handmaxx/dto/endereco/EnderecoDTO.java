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

    // Método para converter de DTO para a entidade Endereco
    public Endereco toModel() {
        Endereco endereco = new Endereco();
        endereco.setCEP(this.CEP);
        endereco.setLogradouro(this.logradouro);
        endereco.setNumeroLote(this.numeroLote);
        endereco.setComplemento(this.complemento);
        endereco.setLocalidade(this.localidade);
        endereco.setUF(this.UF);
        return endereco;
    }

    public static EnderecoDTO valueOf(Endereco endereco){
        return new EnderecoDTO(endereco.getCEP(), 
                               endereco.getLogradouro(), 
                               endereco.getNumeroLote(), 
                               endereco.getComplemento(), 
                               endereco.getLocalidade(), 
                               endereco.getUF());
    }
}
