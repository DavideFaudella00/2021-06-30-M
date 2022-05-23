package it.polito.tdp.genes.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;
import it.polito.tdp.genes.db.GenesDao;

public class Model {
	Graph<Integer, DefaultWeightedEdge> grafo;
	GenesDao dao = new GenesDao();
	private double pesoAlto;
	private double pesoBasso;

	public void creaGrafo() {
		grafo = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		Graphs.addAllVertices(grafo, dao.getVertex());

		for (Adiacenza a : dao.getArchi()) {
			Graphs.addEdgeWithVertices(grafo, a.getC1(), a.getC2(), a.getPeso());
			if (a.getPeso() > pesoAlto) {
				pesoAlto = a.getPeso();
			}
			if (a.getPeso() < pesoBasso) {
				pesoBasso = a.getPeso();
			}
		}

		System.out.println(grafo.vertexSet().size());
		System.out.println(grafo.edgeSet().size());
		System.out.println("Peso piu alto : " + pesoAlto);
		System.out.println("Peso piu basso : " + pesoBasso);
	}

	private int numP = 0;
	private int numM = 0;

	public void check(Double num) {
		numM = 0;
		numP = 0;
		for (DefaultWeightedEdge e : grafo.edgeSet()) {
			if (grafo.getEdgeWeight(e) > num) {
				numP++;
			}
			if (grafo.getEdgeWeight(e) < num) {
				numM++;
			}
		}
		System.out.println("Minori: " + numM);
		System.out.println("Maggiori: " + numP);
	}

	public int getNumP() {
		return numP;
	}

	public int getNumM() {
		return numM;
	}

	public double getPesoAlto() {
		return pesoAlto;
	}

	public double getPesoBasso() {
		return pesoBasso;
	}

}