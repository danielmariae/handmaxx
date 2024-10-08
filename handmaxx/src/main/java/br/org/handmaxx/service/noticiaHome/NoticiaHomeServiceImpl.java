package br.org.handmaxx.service.noticiaHome;

import br.org.handmaxx.dto.noticiaHome.NoticiaHomeDTO;
import br.org.handmaxx.model.NoticiaHome;
import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.repository.NoticiaHomeRepository;
import br.org.handmaxx.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class NoticiaHomeServiceImpl implements NoticiaHomeService{
    
    @Inject
    NoticiaHomeRepository noticiaRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public NoticiaHomeDTO create(NoticiaHomeDTO dto) {
        NoticiaHome noticia = new NoticiaHome();
        noticia.setTitulo(dto.titulo());
        noticia.setConteudo(dto.conteudo());
        noticia.setDataPublicacao(new java.util.Date());

        Usuario autor = usuarioRepository.findByLogin("usuarioLogado"); // Recuperar o usuário logado adequadamente
        noticia.setAutor(autor);

        noticiaRepository.persist(noticia);
        return NoticiaHomeDTO.valueOf(noticia);
    }

    @Override
    public NoticiaHomeDTO findById(Long id) {
        NoticiaHome noticia = noticiaRepository.findById(id);
        return NoticiaHomeDTO.valueOf(noticia);
    }
}
