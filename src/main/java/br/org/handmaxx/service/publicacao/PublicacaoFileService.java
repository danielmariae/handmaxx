package br.org.handmaxx.service.publicacao;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import br.org.handmaxx.service.file.FileService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class PublicacaoFileService implements FileService {

    // Users/mnave/OneDrive/Documentos

    private final String PATH_USER = System.getProperty("user.home") +
            File.separator + "quarkus" +
            File.separator + "images" +
            File.separator + "usuario" +
            File.separator + "handmaxx" +
            File.separator;

    private static final List<String> SUPPORTED_MIME_TYPES = Arrays.asList(
            "image/jpg",
            "image/jpeg",
            "image/png",
            "image/gif");

    private static final int MAX_FILE_SIZE = 1024 * 1024 * 10; // 10mb

    @Override
    @Transactional
    public String salvar(String nomeArquivo, byte[] arquivo) throws IOException {
        verificarTamanhoImagem(arquivo);

        verificarTipoImagem(nomeArquivo);

        // criar diretorio caso nao exista
        Path diretorio = Paths.get(PATH_USER);
        Files.createDirectories(diretorio);

        // criando o nome do arquivo randomico
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        String extensao = mimeType.substring(mimeType.lastIndexOf('/') + 1);
        String novoNomeArquivo = UUID.randomUUID() + "." + extensao;

        // defindo o caminho completo do arquivo
        Path filePath = diretorio.resolve(novoNomeArquivo);

        if (filePath.toFile().exists())
            throw new IOException("Nome de arquivo ja existe.");

        // salvar arquivo
        try (FileOutputStream fos = new FileOutputStream(filePath.toFile())) {
            fos.write(arquivo);
        }

        return filePath.toFile().getName();
    }

    @Override
    public File obter(String nomeArquivo) {
        File file = new File(PATH_USER + nomeArquivo);
        return file;
    }

    private void verificarTamanhoImagem(byte[] arquivo) throws IOException {
        if (arquivo.length > MAX_FILE_SIZE)
            throw new IOException("Arquivo maior que 10mb.");
    }

    private void verificarTipoImagem(String nomeArquivo) throws IOException {
        String mimeType = Files.probeContentType(Paths.get(nomeArquivo));
        if (!SUPPORTED_MIME_TYPES.contains(mimeType))
            throw new IOException("Tipo de imagem não suportada.");

    }

}
