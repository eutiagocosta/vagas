package br.com.vagas.domain;

import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

import br.com.vagas.domain.candidato.Localizacao;

public class CalculadoraDistancia {

	public static void calcularCaminho(Vertex origem) {

		origem.setMinDistance(0.);
		
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		
		vertexQueue.add(origem);

		while (!vertexQueue.isEmpty()) {
			
			Vertex head = vertexQueue.poll();

			for (Edge edge : head.adjacencies()) {
				
				final Vertex vertex = edge.destino();
				
				final Double distanceThroughU = head.minDistance() + edge.peso();
				
				if (distanceThroughU < vertex.minDistance()) {
					vertexQueue.remove(vertex);
					vertex.setMinDistance(distanceThroughU);
					vertexQueue.add(vertex);
				}
			}
		}
	}

	public List<Vertex> criarGrapho() {

		Vertex a = new Vertex("A");
		Vertex b = new Vertex("B");
		Vertex c = new Vertex("C");
		Vertex d = new Vertex("D");
		Vertex e = new Vertex("E");
		Vertex f = new Vertex("F");

		a.addAdjacencies(new Edge[] { new Edge(b, 5) });
		b.addAdjacencies(new Edge[] { new Edge(a, 5), new Edge(c, 7), new Edge(d, 3) });
		c.addAdjacencies(new Edge[] { new Edge(e, 4), new Edge(b, 7) });
		d.addAdjacencies(new Edge[] { new Edge(f, 8), new Edge(b, 3) });
		e.addAdjacencies(new Edge[] { new Edge(d, 10), new Edge(c, 4) });
		f.addAdjacencies(new Edge[] { new Edge(d, 8) });

		return Arrays.asList(a, b, c, d, e, f);

	}

	public Integer calcular(Localizacao localizacaoOrigem, Localizacao localizacaoDestino) {

		List<Vertex> vertices = criarGrapho();

		final Vertex origem = vertices
				.stream()
				.filter(v -> v.name().equals(localizacaoOrigem.valor()))
				.findFirst()
				.get();

		calcularCaminho(origem);

		final Vertex target = vertices
				.stream()
				.filter(v -> v.name().equals(localizacaoDestino.valor()))
				.findFirst()
				.get();

		final Integer distance = target.minDistance().intValue();

		if (estaEntre(distance, 0, 5))
			return 100;
		if (estaEntre(distance, 6, 10))
			return 75;
		if (estaEntre(distance, 11, 15))
			return 50;
		if (estaEntre(distance, 16, 20))
			return 25;

		return 0;

	}

	public static Boolean estaEntre(int valor, int menor, int maior) {
		return menor <= valor && valor <= maior;
	}

	private class Vertex implements Comparable<Vertex> {

		private String name;
		private Edge[] adjacencies;
		private Double minDistance = Double.POSITIVE_INFINITY;

		public Vertex(String argName) {
			name = argName;
		}

		@SuppressWarnings("unused")
		private Vertex() {}

		public int compareTo(Vertex other) {
			return Double.compare(minDistance, other.minDistance);
		}

		public String name() {
			return name;
		}

		public Edge[] adjacencies() {
			return adjacencies;
		}

		public Double minDistance() {
			return minDistance;
		}

		public void addAdjacencies(Edge[] edges) {
			adjacencies = edges;
		}

		public void setMinDistance(Double minDistance) {
			this.minDistance = minDistance;
		}

	}

	private class Edge {

		private Vertex destino;
		private Integer peso;

		public Edge(Vertex destino, Integer peso) {
			setDestino(destino);
			setPeso(peso);
		}

		@SuppressWarnings("unused")
		private Edge() {}

		public Vertex destino() {
			return destino;
		}

		public Integer peso() {
			return peso;
		}

		private void setDestino(Vertex destino) {
			this.destino = destino;
		}

		private void setPeso(Integer peso) {
			this.peso = peso;
		}

	}

}