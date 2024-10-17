package br.org.handmaxx.repository;

import br.org.handmaxx.model.CodigoRecuperacao;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

public class TokenRecuperacaoRepository implements PanacheRepository<CodigoRecuperacao> {

    public CodigoRecuperacao findByToken(String token){
        return find("token", token).firstResult();
    }
}
