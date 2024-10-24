package br.org.handmaxx.service.publicacao;

import java.util.List;

import br.org.handmaxx.dto.publicacao.PublicacaoDTO;
import br.org.handmaxx.form.PublicacaoImageForm;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface PublicacaoService {
    
    PublicacaoDTO create(PublicacaoDTO dto);

    PublicacaoDTO update(Long id, PublicacaoDTO dto);

    PublicacaoDTO findById(Long id);

    void delete(Long id);

    void updateNomeImagem(Long id, String nomeImagem) ;

    public Response salvarImagem(PublicacaoImageForm form,@PathParam("id") Long id);

    List<PublicacaoDTO> getAll(int page, int pageSize);

    List<PublicacaoDTO> findAll();
}
