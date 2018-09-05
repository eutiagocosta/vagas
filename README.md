# Vagas.com

## Endpoint para cadastrar vagas de emprego

Link para documentação da API:
	[Documentacao da API](localhost:9000/swagger-ui.html)

Request:

	POST http://localhost:9000/v1/vagas
	
Body:

```json 
{  
   "empresa":"Teste",
   "titulo":"Vaga teste",
   "descricao":"Criar os mais diferentes tipos de teste",
   "localizacao":"A",
   "nivel":3
}
```
Response: 201 (Created) Com id do recurso criado no atributo Location do header da resposta:

	ex: Location → http://localhost:8080/v1/vagas/574C7899-EF9D-4A15-B104-3F7285EDF610


## Endpoint para cadastrar pessoas

Request:

	POST http://localhost:9000/v1/pessoas
	
Body:
	
```json
{  
   "nome":"John Doe",
   "profissao":"Engenheiro de Software",
   "localizacao":"C",
   "nivel":2
}
```
	
Response: 201 (Created) Com id do recurso criado no atributo Location do header da resposta:

	ex: Location → http://localhost:8080/v1/pessoas/574C7899-EF9D-4A15-B104-3F7285EDF610

## Endpoint para registrar candidatura de uma pessoa em uma vaga

Request:

	POST http://localhost:9000/v1/candidaturas
	
Body:
	
```json
{  
   "id_vaga":"234245-sdfghgf-4567-sdfg",
   "id_pessoa":"edfrghgf-345676-34567er"
}
```
	
Response: 200 (Ok)

Endpoint responsavel pela listagem de candidaturas para uma vaga

Request:

	GET: http://localhost:9000/v1/vagas/1/candidaturas/ranking
	
Response: 200 (Ok)

```json
[  
   {  
      "nome":"Mary Jane",
      "profissao":"Engenheira de Software",
      "localizacao":"A",
      "nivel":4,
      "score":100
   },
   {  
      "nome":"John Doe",
      "profissao":"Engenheiro de Software",
      "localizacao":"C",
      "nivel":2,
      "score":85
   }
]
```

