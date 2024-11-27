package br.org.handmaxx.service.publicacao;

import br.org.handmaxx.service.file.ImageService;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PublicacaoImageService extends ImageService{

    public PublicacaoImageService() {
        super("publicacao");
    }
}
