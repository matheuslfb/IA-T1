# IA-T1

A aplicação simula um jogo, onde um agente deve percorrer um labirinto e encontrar a porta de saída e abri-la. 

Ao longo do mapa existem buracos, paredes, moedas e baús.

Para abrir a porta de saída é necessário distribuir harmonicamente, nos baús, as moedas recolhidas pelo agente. A porta só abre se os baús tiverem a mesma quantidade de moedas. Ao longo do percurso, o agente deve pular ou desviar buracos, contornar paredes, recolher sacos de moedas, descobrir o local da porta e dos baús, e depositar sacos de moedas nesses baús.

## Anotações sobre implementação

- Ambiente/matriz instancia um ambiente/matriz;
- Agente instancia um agente (DECISAO/AUTONOMIA);
- Matriz manda info pro agente;
- Agente reage analisa o que tem e manda a matriz reposicionar ele.

## TO-DO
- [X] Criar github
- [X] Criar maquina de automatos
- [ ] Criar codego: (em andamento)	
	
		Ambiente --> cria ambiente 10x10 (Genetico)
			Paredao(saida[em qqr parte],extremos)
			Gerar baus na coluna vizinha do paredao
			Parede = 5 blocos em linha reta (pode intercalar nao criar lugares fechados) 
			Buracos aleatorios nao intercalar
			SpawnPoint pode ser em qqr lugar menos preso
		
		Agente --> acoes (Estrela)
			pode se mover _WASD_ uma celular por vez
			No idclip 
			LoS = 2 celulas N/S/E/kanye West
			Lie Detector (LoS)
			WS no SACO_DE_GOLD ==> grab SACO_DE_GOLD
			Depositar SACO_DE_GOLD ==> DO LADO DO CHEST
				pode ficar em cima do CHEST
			Memorize positions do CHEST e EXIT
			Agente burrao n sabe nada no comeco
			desviar de HOLES
			se cair no HOLE = Shindeiru  Botar o toastie do mk
			JUMP = DESVIAR = andar 2 celulas por vez (CONFIRMAR)
			MOVIMENTACAO = ESTRELA (em direcao aos baus e porta e saco se ver pela parede)			
		
		Main --> (Ambiente,Agente)	
	

***IMPORTANTE***
usar bestmementos.jpg no video
***IMPORTANTE***
