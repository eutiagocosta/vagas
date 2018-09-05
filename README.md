#Vagas.com

##Endpoint para cadastrar vagas de emprego
Request:
	POST http://localhost:9000/v1/vagas
Body:
	`{"empresa": "Teste","titulo": "Vaga teste","descricao": "Criar os mais diferentes tipos de teste","localizacao": "A","nivel": 3}`
Response: 201 (Created)


##Endpoint para cadastrar pessoas
Request:
	POST http://localhost:9000/v1/pessoas
Body:
	`{"nome": "John Doe","profissao": "Engenheiro de Software","localizacao": "C","nivel": 2}`
Response: 201 (Created)

##Endpoint para registrar candidatura de uma pessoa em uma vaga
Request:
	POST http://localhost:9000/v1/candidaturas
	Body:
	`{"id_vaga": "234245-sdfghgf-4567-sdfg","id_pessoa": "edfrghgf-345676-34567er"}`
Response: 200 (Ok)

Endpoint responsavel pela listagem de candidaturas para uma vaga
Request:
	GET: http://localhost:9000/v1/vagas/1/candidaturas/ranking
Response: 200 (Ok)
`[ {"nome":"Mary Jane","profissao":"Engenheira de Software","localizacao":"A","nivel":4,"score":100}]`

