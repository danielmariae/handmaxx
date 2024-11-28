package br.org.handmaxx.dto.publicacao;

import br.org.handmaxx.model.Publicacao;

public record PublicacaoResponseDTO(
        Long id,
        String titulo,
        String conteudo,
        String nomeImagem
) {
    public static PublicacaoResponseDTO valueOf(Publicacao p){
        return new PublicacaoResponseDTO(p.getId(), p.getTitulo(), limitaString(p.getConteudo(), 200), p.getNomeImagem());
    
    }
    public static String limitaString(String texto, int maximo){
        if (texto.length() <= maximo){
           return texto;
        }else{
           return texto.substring(0, maximo);
        }
     }
}
