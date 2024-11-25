package br.org.handmaxx.service.publicacao;

import java.util.List;

import br.org.handmaxx.dto.publicacao.PublicacaoDTO;
import br.org.handmaxx.dto.publicacao.PublicacaoResponseDTO;
import br.org.handmaxx.form.PublicacaoImageForm;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface PublicacaoService {
    
    PublicacaoResponseDTO create(PublicacaoDTO dto);

    PublicacaoResponseDTO update(Long id, PublicacaoDTO dto);

    PublicacaoResponseDTO findById(Long id);

    void delete(Long id);

    void updateNomeImagem(Long id, String nomeImagem) ;

    public Response salvarImagem(PublicacaoImageForm form,@PathParam("id") Long id);

    List<PublicacaoResponseDTO> getAll(int page, int pageSize);

    List<PublicacaoResponseDTO> findAll();
}
