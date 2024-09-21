package br.org.handmaxx.dto.usuario;

import br.org.handmaxx.model.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UsuarioDTO (

    @NotBlank(message = "O CNPJ não pode ser nulo.")
    @Pattern(regexp = "^\\d{14}$", message = "Por favor, digitar apenas 14 caracteres numéricos [sem / e traço].")
    String cnpj,

    @NotBlank(message = "O campo login não pode ser nulo.")
    String login,
    @NotBlank(message = "O campo senha não pode ser nulo.")
    @Pattern(regexp = "(?=^.{6,15}$)(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+}{:;'?/<.>,])(?!.*\\s).*$", message = "Senha forte (pelo menos 1 letra maiúscula, 1 letra minúscula, 1 dígito, 1 caractere especial). Entre 6 a 10 caracteres.")
    String senha
) {
    public static UsuarioDTO valueOf(Usuario usuario){
        return new UsuarioDTO(
            usuario.getCnpj(),
            usuario.getLogin(),
            usuario.getSenha()
        );
    }
}