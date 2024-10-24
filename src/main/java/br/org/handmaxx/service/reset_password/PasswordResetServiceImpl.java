package br.org.handmaxx.service.reset_password;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import br.org.handmaxx.app.error.custom.CustomException;
import br.org.handmaxx.app.error.global.ErrorResponse;
import br.org.handmaxx.dto.email.EmailDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetRequestDTO;
import br.org.handmaxx.model.PasswordResetToken;
import br.org.handmaxx.model.Usuario;
import br.org.handmaxx.repository.PasswordResetTokenRepository;
import br.org.handmaxx.repository.UsuarioRepository;
import br.org.handmaxx.service.email.EmailService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PasswordResetServiceImpl implements PasswordResetService {
    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Inject
    EmailService emailService;

    @Override
    @Transactional
    public void requestPasswordReset(PasswordResetRequestDTO request) {
        Optional<Usuario> userOpt = usuarioRepository.findByEmailOptional(request.email());
        if(userOpt.isEmpty())
            throw new CustomException(new ErrorResponse("Usuário não encontrado pelo email informado.", "PasswordResetServiceImpl(requestPasswordToken)", 404));
        if (userOpt.isPresent()) {
            Usuario user = userOpt.get();
            
            if(passwordResetTokenRepository.findByUserId(user.getId()) != null){
                PasswordResetToken oldToken = passwordResetTokenRepository.findByUserId(user.getId());
                passwordResetTokenRepository.delete(oldToken);
            }

            String token = UUID.randomUUID().toString();
            Date expirationDate = new Date(System.currentTimeMillis() + 3600000); // 1 hora
            
            PasswordResetToken resetToken = new PasswordResetToken(token, user, expirationDate);
            passwordResetTokenRepository.persist(resetToken); // Persistir o token

            String resetLink = "https://localhost:4200/reset-password?token=" + token;
            String corpoEmail = gerarCorpoEmailHtml(resetLink);

            // Usando EmailService para enviar o e-mail
            EmailDTO emailDTO = new EmailDTO(user.getEmail(), "Recuperação de Senha - " + user.getLogin(), corpoEmail);
            emailService.enviarEmail(emailDTO);
        }
    }

    private String gerarCorpoEmailHtml(String linkRecuperacao) {
        return """
            <html>
              <body>
                <div style="font-family:Arial,sans-serif;max-width:600px;margin:auto;padding:20px;">
                  <h2 style="color:#333;">Recuperação de Senha</h2>
                  <p>Olá,</p>
                  <p>Recebemos uma solicitação para recuperar sua senha. Clique no link abaixo para criar uma nova senha:</p>
                  <a href="%s" style="color:#1a73e8;">Recuperar senha</a>
                  <p>Se você não solicitou essa mudança, por favor, ignore este email.</p>
                  <p>Atenciosamente,</p>
                  <p>Equipe HandMaxx</p>
                </div>
              </body>
            </html>
        """.formatted(linkRecuperacao);
    }
    
    @Override
    public boolean validateResetToken(String token) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        return resetToken != null && resetToken.getExpirationDate().after(new Date());
    }

    @Override
    public void resetPassword(PasswordResetDTO resetDTO) {
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(resetDTO.token());
        if (resetToken != null && resetToken.getExpirationDate().after(new Date())) {
            Usuario user = resetToken.getUsuario();
            if(resetDTO.newPassword() != resetDTO.confirmPassword())
                throw new CustomException(new ErrorResponse("Senha digitada diferente da confirmação", "PasswordResetServiceImpl(resetPassword)", 400));    
            else
                user.setSenha(resetDTO.newPassword()); // Considere usar hashing
            usuarioRepository.persist(user); // Persistir o usuário com a nova senha
        } else {
            throw new RuntimeException("Token inválido ou expirado.");
        }
    }

}
