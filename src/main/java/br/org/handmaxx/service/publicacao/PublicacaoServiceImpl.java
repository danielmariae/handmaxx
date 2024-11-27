package br.org.handmaxx.service.publicacao;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.org.handmaxx.dto.publicacao.PublicacaoDTO;
import br.org.handmaxx.dto.publicacao.PublicacaoResponseDTO;
import br.org.handmaxx.form.PublicacaoImageForm;
import br.org.handmaxx.model.Publicacao;
import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.util.Error;
import br.org.handmaxx.repository.PublicacaoRepository;
import br.org.handmaxx.repository.UsuarioRepository;
import br.org.handmaxx.service.file.ImageService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
public class PublicacaoServiceImpl implements PublicacaoService {
    @Inject
    PublicacaoRepository publicacaoRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    ImageService publicacaoFileService;

    @Override
    @Transactional
    public PublicacaoResponseDTO create(PublicacaoDTO dto) {
        Publicacao publicacao = new Publicacao();
        publicacao.setTitulo(dto.titulo());
        publicacao.setConteudo(dto.conteudo());
        publicacao.setNomeImagem(dto.nomeImagem());
        publicacao.setDataPublicacao(new java.util.Date());

        Usuario autor = usuarioRepository.findByLogin("usuarioLogado"); // Recuperar o usuário logado adequadamente
        publicacao.setAutor(autor);

        publicacaoRepository.persist(publicacao);
        return PublicacaoResponseDTO.valueOf(publicacao);
    }

    @Override
    @Transactional
    public PublicacaoResponseDTO update(Long id, PublicacaoDTO dto) {
        Publicacao publicacao = publicacaoRepository.findById(id);

        publicacao.setId(id);
        publicacao.setTitulo(dto.titulo());
        publicacao.setConteudo(dto.conteudo());

        publicacaoRepository.persist(publicacao);
        return PublicacaoResponseDTO.valueOf(publicacao);
    }

    @Override
    public PublicacaoResponseDTO findById(Long id) {
        Publicacao publicacao = publicacaoRepository.findById(id);
        return PublicacaoResponseDTO.valueOf(publicacao);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!publicacaoRepository.deleteById(id))
            throw new NotFoundException();
    }

    @Override
    public void updateNomeImagem(Long id, String nomeImagem) {
        Publicacao publicacao = publicacaoRepository.findById(id);

        if (publicacao == null)
            throw new NullPointerException("Nenhuma publicacao encontrada");
        publicacao.setNomeImagem(nomeImagem);

    }

    @PATCH
    @Path("/upload/imagem/{id}")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Override
    public Response salvarImagem(@MultipartForm PublicacaoImageForm form, @PathParam("id") Long id) {
        try {
            publicacaoFileService.salvar(form.getNomeImagem(), form.getImagem());

        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        ((PublicacaoService) publicacaoFileService).updateNomeImagem(id, form.getNomeImagem());

        return Response.ok(Status.NO_CONTENT).build();
    }

    @Override
    public List<PublicacaoResponseDTO> getAll(int page, int pageSize) {
        List<Publicacao> list = publicacaoRepository
                .findAll()
                .page(page, pageSize)
                .list();

        return list.stream().map(e -> PublicacaoResponseDTO.valueOf(e)).collect(Collectors.toList());
    }

    @Override
    public List<PublicacaoResponseDTO> findAll() {
        return publicacaoRepository.listAll().stream().map(e -> PublicacaoResponseDTO.valueOf(e)).toList();
    }

}
