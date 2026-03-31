# Export Report API 📄🚀

Esta API foi desenvolvida em **Java 21** e **Spring Boot** para gerenciar a exportação de relatórios dinâmicos em PDF utilizando **Handlebars** para templating e **OpenHTMLtoPDF** para renderização de alta performance.

-----

## 🛠️ Acesso e Ferramentas

### Banco de Dados (H2 Console)

Para inspeção de dados em ambiente de desenvolvimento:

  * **URL:** `http://localhost:8080/h2-console`
  * **Configuração Crítica:** No campo **JDBC URL**, utilize exatamente:
    > `jdbc:h2:file:./data/export-report_db`

### Documentação da API (Swagger)

Para testes de endpoints e visualização dos schemas (PayloadDTO):

  * **URL:** [http://localhost:8080/swagger-ui/index.html](https://www.google.com/search?q=http://localhost:8080/swagger-ui/index.html)

-----

## 🎨 Padrão de Desenvolvimento HTML/PDF

A renderização utiliza o motor **OpenHTMLtoPDF**, que implementa o padrão **CSS Paged Media**. Este padrão permite o controle preciso de margens, cabeçalhos repetíveis e numeração de páginas diretamente via CSS.

### 📜 Regras de Ouro (Checklist)

Para garantir que o relatório seja gerado sem erros de parse e com fidelidade visual, siga estas diretrizes:

  * **XHTML é lei:** O motor exige XML válido. Todas as tags **devem** ser fechadas obrigatoriamente.
      * *Exemplo:* `<br />`, `<img />`, `<hr />`.
  * **CSS Inline ou Interno:** Evite dependências externas.
      * Arquivos `.css` externos aumentam o tempo de IO e latência de rede. Injetar o CSS dentro da tag `<style>` no `<head>` é a estratégia mais performática.
  * **Unidades de Medida Estáticas:** Para PDFs, a precisão física é essencial.
      * Use **pt** ou **cm** para fontes e margens.
      * Use **%** para larguras de colunas (grids).
      * *Atenção:* Evite `px`, pois varia conforme a DPI do SO (Linux/Windows), causando inconsistências no layout.
  * **Gestão de Imagens:**
      * **Base64:** Prefira para logos e ícones pequenos (evita IO de disco/rede).
      * **URLs Absolutas:** Utilize apenas para recursos dinâmicos (ex: fotos de jogadores ou escudos que mudam frequentemente).
  * **Tipografia e Fontes:**
      * O Java possui suporte limitado a fontes nativas do SO.
      * **Fontes Seguras:** Arial, Helvetica, Times New Roman.
      * **Custom Fonts:** Se utilizar fontes personalizadas (Google Fonts, etc), elas devem ser registradas no `PdfRendererBuilder` via `.useFont()`.

-----

## 🚀 Como gerar um relatório

1.  Acesse o Swagger.
2.  Utilize o endpoint `POST /api/v1/reports/export-pdf`.
3.  Envie o `htmlReportTemplate` (Stringificada e compatível com XHTML) e o `jsonData` com as chaves correspondentes aos placeholders `{{ }}` do Handlebars.

-----

*Desenvolvido com foco em Clean Code, Performance e Escalabilidade.*