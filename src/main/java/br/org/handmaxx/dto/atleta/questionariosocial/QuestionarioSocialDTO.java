package br.org.handmaxx.dto.atleta.questionariosocial;

import br.org.handmaxx.model.QuestionarioSocial;

public record QuestionarioSocialDTO(
    Double rendaFamiliar,
    Integer pessoasEmCasa,
    String condicoesMoradia,
    Boolean cadastroNIS
) {

    public QuestionarioSocial toModel() {
        QuestionarioSocial dados = new QuestionarioSocial();
        dados.setRendaFamiliar(this.rendaFamiliar);
        dados.setPessoasEmCasa(this.pessoasEmCasa);
        dados.setCondicoesMoradia(this.condicoesMoradia);
        dados.setCadastroNIS(this.cadastroNIS);
        return dados;
    }

    public static QuestionarioSocialDTO valueOf(QuestionarioSocial questionario){
        return new QuestionarioSocialDTO(
            questionario.getRendaFamiliar(),
            questionario.getPessoasEmCasa(),
            questionario.getCondicoesMoradia(),
            questionario.getCadastroNIS()
        );
    }
}
