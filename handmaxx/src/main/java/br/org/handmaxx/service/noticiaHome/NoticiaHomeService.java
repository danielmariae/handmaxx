package br.org.handmaxx.service.noticiaHome;

import br.org.handmaxx.dto.noticiaHome.NoticiaHomeDTO;

public interface NoticiaHomeService {
    
    NoticiaHomeDTO create(NoticiaHomeDTO dto);

    NoticiaHomeDTO findById(Long id);
}
