// package br.org.handmaxx.service.reports;

// import java.io.ByteArrayOutputStream;
// import java.io.InputStream;
// import java.util.HashMap;
// import java.util.List;
// import java.util.Map;

// import jakarta.enterprise.context.ApplicationScoped;
// import net.sf.jasperreports.engine.JasperCompileManager;
// import net.sf.jasperreports.engine.JasperFillManager;
// import net.sf.jasperreports.engine.JasperPrint;
// import net.sf.jasperreports.engine.JasperReport;
// import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
// import net.sf.jasperreports.export.SimpleExporterInput;
// import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

// @ApplicationScoped
// public class ReportsServiceImpl implements ReportsService {
//         @Override
//     public byte[] generateReport() {
//         try {
//             // Carregar o arquivo .jrxml do relatório
//             InputStream reportStream = getClass().getResourceAsStream("/relatorio/Relatorio_Quantidade_Pessoas_Casa.jrxml");
//             JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

//             // Aqui você pode obter os dados do seu banco de dados ou de outra fonte
//             List<> dataList = fetchData(); // Implementar seu método para buscar dados

//             JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);
//             Map<String, Object> parameters = new HashMap<>();
//             // Adicione parâmetros, se necessário

//             // Preencher o relatório
//             JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

//             // Exportar o relatório para PDF
//             JRPdfExporter exporter = new JRPdfExporter();
//             ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//             exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//             exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
//             exporter.exportReport();

//             return outputStream.toByteArray(); // Retorna o PDF gerado
//         } catch (Exception e) {
//             throw new RuntimeException("Erro ao gerar relatório", e);
//         }
//     }

// }
