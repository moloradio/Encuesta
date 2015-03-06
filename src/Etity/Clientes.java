package Etity;

import java.sql.Date;

public class Clientes {

	private int idClientes;
	private String nombre;
	private String aMaterno;
	private String aPaterno;
	private String calle;
	private String numero_casa;
	private String colonia;
	private String tel_casa;
	private String tel_celular;
	private String email;
	private Date fecha_alta;
	private Date fecha_baja;
	private char activo;
	private int idMunicipio;
	private int idEstado;
	
	public Clientes(){}
	
	public Clientes(String nombre, String aMaterno,
			String aPaterno, String tel_casa, String tel_celular, String email) {
		super();
		this.nombre = nombre;
		this.aMaterno = aMaterno;
		this.aPaterno = aPaterno;
		this.tel_casa = tel_casa;
		this.tel_celular = tel_celular;
		this.email = email;
	}
	
	public int getIdClientes() {
		return idClientes;
	}
	public void setIdClientes(int idClientes) {
		this.idClientes = idClientes;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getaMaterno() {
		return aMaterno;
	}
	public void setaMaterno(String aMaterno) {
		this.aMaterno = aMaterno;
	}
	public String getaPaterno() {
		return aPaterno;
	}
	public void setaPaterno(String aPaterno) {
		this.aPaterno = aPaterno;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public String getNumero_casa() {
		return numero_casa;
	}
	public void setNumero_casa(String numero_casa) {
		this.numero_casa = numero_casa;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getTel_casa() {
		return tel_casa;
	}
	public void setTel_casa(String tel_casa) {
		this.tel_casa = tel_casa;
	}
	public String getTel_celular() {
		return tel_celular;
	}
	public void setTel_celular(String tel_celular) {
		this.tel_celular = tel_celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Date fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Date getFecha_baja() {
		return fecha_baja;
	}
	public void setFecha_baja(Date fecha_baja) {
		this.fecha_baja = fecha_baja;
	}
	public char getActivo() {
		return activo;
	}
	public void setActivo(char activo) {
		this.activo = activo;
	}
	public int getIdMunicipio() {
		return idMunicipio;
	}
	public void setIdMunicipio(int idMunicipio) {
		this.idMunicipio = idMunicipio;
	}
	public int getIdEstado() {
		return idEstado;
	}
	public void setIdEstado(int idEstado) {
		this.idEstado = idEstado;
	}
	
}
