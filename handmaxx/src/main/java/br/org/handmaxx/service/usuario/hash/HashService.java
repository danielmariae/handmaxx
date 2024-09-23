package br.org.handmaxx.service.usuario.hash;

public interface HashService {
    // Recebe a senha e gera um hash
    public String getHashSenha(String senha);
}
