package com.gestion.tasking.entity;



import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter                    
@Entity
@Table(name = "TM_Usuarios")
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_usuario") 
		private  long id_usuario;
		
		
		private String nombre;
		private String email;
		private String password;
		private LocalDateTime fecha_creacion;
		public boolean isPresent() {
	
			return false;
		}
}
