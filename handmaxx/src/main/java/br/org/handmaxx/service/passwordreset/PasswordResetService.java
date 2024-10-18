package br.org.handmaxx.service.passwordreset;

import br.org.handmaxx.dto.resetpassword.PasswordResetDTO;
import br.org.handmaxx.dto.resetpassword.PasswordResetRequestDTO;

public interface PasswordResetService {
    public void requestPasswordReset(PasswordResetRequestDTO request);
    boolean validateResetToken(String token);
    public void resetPassword(PasswordResetDTO resetDTO);

}
