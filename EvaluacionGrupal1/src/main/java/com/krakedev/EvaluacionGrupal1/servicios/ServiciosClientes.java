package com.krakedev.EvaluacionGrupal1.servicios;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.krakedev.EvaluacionGrupal1.entidades.Grupo;
import com.krakedev.EvaluacionGrupal1.entidades.RegistroMovimiento;
import com.krakedev.EvaluacionGrupal1.exepcion.KrakedevException;
import com.krakedev.EvaluacionGrupal1.metodos.Consultas;
import com.krakedev.EvaluacionGrupal1.metodos.EliminarGrupo;
import com.krakedev.EvaluacionGrupal1.metodos.Grupos;




@Path("servicios")

public class ServiciosClientes {
	@Path("insertarGrupo")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertarGrupo(Grupo grupos) {
		Grupos grupo=new Grupos();
		try {
			grupo.insertar(grupos);
			return Response.ok("Grupo insertado").build();
		} catch (KrakedevException e) {	
			e.printStackTrace();
			return Response.serverError().build();
		}

	}


	
	@Path("eliminarGrupo/{idParam}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarGrupo(@ PathParam("idParam")String id)  {
		EliminarGrupo gru = new EliminarGrupo();
	
		
		try {
			gru.EliminarGrupos(id);
			return Response.ok("Grupo eliminado").build();
		} catch (KrakedevException e) {
			
			e.printStackTrace();
			return Response.serverError().build();
		}
		}
		
		@Path("consultas/{idParam}")
		@GET
		@Produces(MediaType.APPLICATION_JSON)
		public Response buscarConMasHijos(@ PathParam("idParam")String codigo) {
				Consultas con = new Consultas();
		
			ArrayList<RegistroMovimiento> RM = null;
			
			try {
				RM = con.recuperarTodos(codigo);
				return Response.ok(RM).build();
			} catch (KrakedevException e) {
				e.printStackTrace();
				return Response.serverError().build();

			}

			
	}

	
	

}
