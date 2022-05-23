package it.polito.tdp.genes.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Genes;
import it.polito.tdp.genes.model.Interactions;

public class GenesDao {

	public List<Genes> getAllGenes() {
		String sql = "SELECT DISTINCT GeneID, Essential, Chromosome FROM Genes";
		List<Genes> result = new ArrayList<Genes>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Genes genes = new Genes(res.getString("GeneID"), res.getString("Essential"), res.getInt("Chromosome"));
				result.add(genes);
			}
			res.close();
			st.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException("Database error", e);
		}
	}

	public List<Integer> getVertex() {
		String sql = "SELECT DISTINCT g.Chromosome as cr " + "FROM genes g " + "WHERE g.Chromosome <> 0";
		List<Integer> result = new ArrayList<Integer>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(res.getInt("cr"));
			}
			res.close();
			st.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException("Database error 1", e);
		}
	}

	public List<Adiacenza> getArchi() {
		String sql = "SELECT DISTINCT g1.Chromosome as g1, g2.Chromosome as g2, SUM(DISTINCT(i1.Expression_Corr)) AS somma "
				+ "FROM genes g1, interactions i1, genes g2 "
				+ "WHERE g1.Chromosome <> g2.Chromosome and g1.GeneID = i1.GeneID1 AND g2.GeneID = i1.GeneID2 and g1.Chromosome<> 0 and g2.Chromosome<>0 "
				+ "GROUP BY g1.Chromosome, g2.Chromosome";
		List<Adiacenza> result = new ArrayList<Adiacenza>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(new Adiacenza(res.getInt("g1"), res.getInt("g2"), res.getDouble("somma")));
			}
			res.close();
			st.close();
			conn.close();
			return result;

		} catch (SQLException e) {
			throw new RuntimeException("Database error 1", e);
		}
	}
}
