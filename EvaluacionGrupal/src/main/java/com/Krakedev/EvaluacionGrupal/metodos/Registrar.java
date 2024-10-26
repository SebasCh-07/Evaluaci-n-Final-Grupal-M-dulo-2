package com.Krakedev.EvaluacionGrupal.metodos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.Krakedev.EvaluacionGrupal.entidades.Articulo;
import com.Krakedev.EvaluacionGrupal.entidades.Grupo;
import com.Krakedev.EvaluacionGrupal.excepcion.KrakedevException;



public class Registrar {
	Grupo gr1 = new Grupo("G001", "Aseo Personal");
	Grupo gr2 = new Grupo("G002", "Tecnologia");
	Grupo gr3 = new Grupo("G003", "Ropa");

	
	public void resitrar(Articulo art) throws KrakedevException {
		Connection con = null; 
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(
					"insert into articulos(id,nombre,precio_venta,precio_compra,id_grupo)" + "values(?,?,?,?,?)");
			ps.setString(1, art.getId());
			ps.setString(2, art.getNombre());
			ps.setBigDecimal(3, art.getPrecio_venta());
			ps.setBigDecimal(4, art.getPrecio_compra());

			if (gr1.getId() == art.getId_grupo().getId()) {
				ps.setString(5, gr1.getId());
			} else if (gr2.getId() == art.getId_grupo().getId()) {
				ps.setString(5, gr2.getId());
			} else {
				ps.setString(5, gr3.getId());
			}

			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new KrakedevException("Error al insertar el cliente. Detalle: "+e.getMessage());
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	
}
