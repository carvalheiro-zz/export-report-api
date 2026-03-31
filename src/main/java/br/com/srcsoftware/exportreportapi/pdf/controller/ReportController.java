package br.com.srcsoftware.exportreportapi.pdf.controller;

import java.time.LocalDateTime;

import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.srcsoftware.exportreportapi.controller.model.dto.PayloadDTO;
import br.com.srcsoftware.exportreportapi.pdf.service.PdfService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1/relatorios")
@RequiredArgsConstructor
public class ReportController {

    private final PdfService pdfService;

    @Operation(summary = "Gera PDF a partir de template Handlebars e JSON")
    @PostMapping(value = "/exportar-pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> exportarPdf(@RequestBody PayloadDTO payload) {
        try {
        	log.info("Iniciando o processo de criação de relatorio com base em Template e JSonData");
        	
            byte[] pdfBytes = pdfService.gerarPdf(payload);
            
            String fileName = String.format("report-%d.pdf", System.currentTimeMillis());

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .contentLength(pdfBytes.length) // Boa prática: informa o tamanho para a barra de progresso do browser
                    .body(pdfBytes);

        } catch (Exception e) {
            log.error("Falha crítica na geração de PDF para o payload: {}", payload, e);
            return ResponseEntity.internalServerError().build();
        }
    }
}