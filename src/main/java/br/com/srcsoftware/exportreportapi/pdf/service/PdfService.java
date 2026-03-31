package br.com.srcsoftware.exportreportapi.pdf.service;
import java.io.ByteArrayOutputStream;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jknack.handlebars.Handlebars;
import com.github.jknack.handlebars.Template;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import br.com.srcsoftware.exportreportapi.controller.model.dto.PayloadDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final ObjectMapper objectMapper;
    private final Handlebars handlebars = new Handlebars();

    public byte[] gerarPdf(PayloadDTO payload) throws Exception {
        // 1. Converter a String JSON em Map para o Handlebars
        Map<String, Object> data = objectMapper.readValue(
            payload.getJsonData(), 
            new TypeReference<Map<String, Object>>() {}
        );

        // 2. Compilar o template HTML enviado no payload
        Template template = handlebars.compileInline(payload.getHtmlReportTemplate());
        String htmlProcessado = template.apply(data);

        // 3. Renderizar para PDF usando OpenHTMLtoPDF
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode(); // Otimização de performance
            builder.withHtmlContent(htmlProcessado, "");
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        }
    }
}