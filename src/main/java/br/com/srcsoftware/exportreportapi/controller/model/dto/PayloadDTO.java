package br.com.srcsoftware.exportreportapi.controller.model.dto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PayloadDTO {
	private String htmlReportTemplate;
	
	@Schema(example = "Use JSON.stringify(meuObjetoDados) para -> {\"nome\": \"Gabriel\", \"valor\": 150.00}", description = "JSON stringificado com os dados do template")
	private String jsonData;
}