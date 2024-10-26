package com.Krakedev.EvaluacionGrupal.metodos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.Krakedev.EvaluacionGrupal.conexion.ConexionBDD;
import com.Krakedev.EvaluacionGrupal.entidades.RegistroMovimiento;
import com.Krakedev.EvaluacionGrupal.excepcion.KrakedevException;

public class Consultas {

public ArrayList<RegistroMovimiento> recuperarTodos(String idArticulos) throws KrakedevException{
		
		ArrayList<RegistroMovimiento> movimientos= new ArrayList<RegistroMovimiento>();
		
		Connection con= null;
		PreparedStatement ps= null;
		java.sql.ResultSet rs= null;
		RegistroMovimiento cl=null;
		try {
			con=ConexionBDD.obtenerConexion();
				ps=con.prepareStatement("select * from registro_movimientos where id_articulos=?");
				ps.setString(1, idArticulos);
				rs=ps.executeQuery();
				
				while(rs.next()) {
					int id=rs.getInt("id");
					String idArticulo=rs.getString("idArticulos");
					int cantidad=rs.getInt("cantidad");
					Date  fechaMovimiento=rs.getDate("fechaMovimiento");
					cl= new RegistroMovimiento(id,idArticulo,cantidad,fechaMovimiento);
					movimientos.add(cl);
				}
			
		} catch (KrakedevException e) {
			e.printStackTrace();
			throw e;
		}catch (SQLException e) {
				e.printStackTrace();
				throw new KrakedevException("Error al consultar el articulo"+e.getMessage());
			}
		
		return movimientos;
	}
}
