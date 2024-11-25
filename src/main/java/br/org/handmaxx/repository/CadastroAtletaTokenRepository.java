package br.org.handmaxx.repository;

import br.org.handmaxx.model.CadastroAtletaToken;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CadastroAtletaTokenRepository implements PanacheRepository<CadastroAtletaToken> {
    public CadastroAtletaToken findByToken(String token){
        return find("token", token).singleResult();
    }

    public CadastroAtletaToken findByAtleta(Long atletaId) {
        return find("atleta.id", atletaId).firstResultOptional().orElse(null);
    }
}
