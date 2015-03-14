package ec.edu.epn.rrhh.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ec.edu.epn.articulos.entities.Articulo;


/**
 * The persistent class for the emp database table.
 * 
 */
@Entity

@Table(name="emp",catalog = "bddcorpepn", schema = "`Rrhh`" )
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String nced;

	private String apel;

	@Column(name="apel_con")
	private String apelCon;

	@Column(name="ciudad_nac")
	private String ciudadNac;

	@Column(name="cod_clase")
	private String codClase;

	@Column(name="\"COD_DEDICACION\"")
	private String codDedicacion;

	@Column(name="cod_dep")
	private String codDep;

	@Column(name="cod_depa")
	private String codDepa;

	@Column(name="cod_ecivil")
	private String codEcivil;

	@Column(name="cod_est")
	private String codEst;

	@Column(name="cod_nac")
	private String codNac;

	@Column(name="cod_pindmin")
	private String codPindmin;

	@Column(name="cod_prov")
	private String codProv;

	@Column(name="cod_sexo")
	private String codSexo;

	@Column(name="cod_tiporelacionlab")
	private String codTiporelacionlab;

	private String codtim;

	private String direc;

	private String email1;

	private String email2;

	private String ext;

    @Temporal( TemporalType.DATE)
	@Column(name="fec_ingepn")
	private Date fecIngepn;

    @Temporal( TemporalType.DATE)
	@Column(name="fec_ingsp")
	private Date fecIngsp;

    @Temporal( TemporalType.DATE)
	@Column(name="fec_sis")
	private Date fecSis;

    @Temporal( TemporalType.DATE)
	private Date fnac;

	private String foto;

	private String funciontipica;

	private String investiga;

	private String lnac;

	@Column(name="ltrab_espec")
	private String ltrabEspec;

	private String lugtim;

	private String mbienes;

	private String nafil;

	private Integer ncargas;

	private String ncedm;

	private String nom;

	@Column(name="nom_con")
	private String nomCon;

	@Column(name="nom_jefe")
	private String nomJefe;

	private String nrocarnetconadis;

	@Column(name="ocup_con")
	private String ocupCon;

	private String ordjer;

	private String porcendiscap;

	private String tcedm;

	private String tdoc;

	private String telefcel;

	private String telf;

	private String telf1;

	private String tipodiscap;

	private String tipsan;

    @Temporal( TemporalType.DATE)
	@Column(name="upfsis_emp")
	private Date upfsisEmp;

	@Column(name="upusr_emp")
	private String upusrEmp;

	private String usr;

	@OneToMany(mappedBy = "emp_1")
	private List<Articulo> articulo_1;

    public Emp() {
    }

	public String getNced() {
		return this.nced;
	}

	public void setNced(String nced) {
		this.nced = nced;
	}

	public String getApel() {
		return this.apel;
	}

	public void setApel(String apel) {
		this.apel = apel;
	}

	public String getApelCon() {
		return this.apelCon;
	}

	public void setApelCon(String apelCon) {
		this.apelCon = apelCon;
	}

	public String getCiudadNac() {
		return this.ciudadNac;
	}

	public void setCiudadNac(String ciudadNac) {
		this.ciudadNac = ciudadNac;
	}

	public String getCodClase() {
		return this.codClase;
	}

	public void setCodClase(String codClase) {
		this.codClase = codClase;
	}

	public String getCodDedicacion() {
		return this.codDedicacion;
	}

	public void setCodDedicacion(String codDedicacion) {
		this.codDedicacion = codDedicacion;
	}

	public String getCodDep() {
		return this.codDep;
	}

	public void setCodDep(String codDep) {
		this.codDep = codDep;
	}

	public String getCodDepa() {
		return this.codDepa;
	}

	public void setCodDepa(String codDepa) {
		this.codDepa = codDepa;
	}

	public String getCodEcivil() {
		return this.codEcivil;
	}

	public void setCodEcivil(String codEcivil) {
		this.codEcivil = codEcivil;
	}

	public String getCodEst() {
		return this.codEst;
	}

	public void setCodEst(String codEst) {
		this.codEst = codEst;
	}

	public String getCodNac() {
		return this.codNac;
	}

	public void setCodNac(String codNac) {
		this.codNac = codNac;
	}

	public String getCodPindmin() {
		return this.codPindmin;
	}

	public void setCodPindmin(String codPindmin) {
		this.codPindmin = codPindmin;
	}

	public String getCodProv() {
		return this.codProv;
	}

	public void setCodProv(String codProv) {
		this.codProv = codProv;
	}

	public String getCodSexo() {
		return this.codSexo;
	}

	public void setCodSexo(String codSexo) {
		this.codSexo = codSexo;
	}

	public String getCodTiporelacionlab() {
		return this.codTiporelacionlab;
	}

	public void setCodTiporelacionlab(String codTiporelacionlab) {
		this.codTiporelacionlab = codTiporelacionlab;
	}

	public String getCodtim() {
		return this.codtim;
	}

	public void setCodtim(String codtim) {
		this.codtim = codtim;
	}

	public String getDirec() {
		return this.direc;
	}

	public void setDirec(String direc) {
		this.direc = direc;
	}

	public String getEmail1() {
		return this.email1;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public String getEmail2() {
		return this.email2;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public String getExt() {
		return this.ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}

	public Date getFecIngepn() {
		return this.fecIngepn;
	}

	public void setFecIngepn(Date fecIngepn) {
		this.fecIngepn = fecIngepn;
	}

	public Date getFecIngsp() {
		return this.fecIngsp;
	}

	public void setFecIngsp(Date fecIngsp) {
		this.fecIngsp = fecIngsp;
	}

	public Date getFecSis() {
		return this.fecSis;
	}

	public void setFecSis(Date fecSis) {
		this.fecSis = fecSis;
	}

	public Date getFnac() {
		return this.fnac;
	}

	public void setFnac(Date fnac) {
		this.fnac = fnac;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getFunciontipica() {
		return this.funciontipica;
	}

	public void setFunciontipica(String funciontipica) {
		this.funciontipica = funciontipica;
	}

	public String getInvestiga() {
		return this.investiga;
	}

	public void setInvestiga(String investiga) {
		this.investiga = investiga;
	}

	public String getLnac() {
		return this.lnac;
	}

	public void setLnac(String lnac) {
		this.lnac = lnac;
	}

	public String getLtrabEspec() {
		return this.ltrabEspec;
	}

	public void setLtrabEspec(String ltrabEspec) {
		this.ltrabEspec = ltrabEspec;
	}

	public String getLugtim() {
		return this.lugtim;
	}

	public void setLugtim(String lugtim) {
		this.lugtim = lugtim;
	}

	public String getMbienes() {
		return this.mbienes;
	}

	public void setMbienes(String mbienes) {
		this.mbienes = mbienes;
	}

	public String getNafil() {
		return this.nafil;
	}

	public void setNafil(String nafil) {
		this.nafil = nafil;
	}

	public Integer getNcargas() {
		return this.ncargas;
	}

	public void setNcargas(Integer ncargas) {
		this.ncargas = ncargas;
	}

	public String getNcedm() {
		return this.ncedm;
	}

	public void setNcedm(String ncedm) {
		this.ncedm = ncedm;
	}

	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNomCon() {
		return this.nomCon;
	}

	public void setNomCon(String nomCon) {
		this.nomCon = nomCon;
	}

	public String getNomJefe() {
		return this.nomJefe;
	}

	public void setNomJefe(String nomJefe) {
		this.nomJefe = nomJefe;
	}

	public String getNrocarnetconadis() {
		return this.nrocarnetconadis;
	}

	public void setNrocarnetconadis(String nrocarnetconadis) {
		this.nrocarnetconadis = nrocarnetconadis;
	}

	public String getOcupCon() {
		return this.ocupCon;
	}

	public void setOcupCon(String ocupCon) {
		this.ocupCon = ocupCon;
	}

	public String getOrdjer() {
		return this.ordjer;
	}

	public void setOrdjer(String ordjer) {
		this.ordjer = ordjer;
	}

	public String getPorcendiscap() {
		return this.porcendiscap;
	}

	public void setPorcendiscap(String porcendiscap) {
		this.porcendiscap = porcendiscap;
	}

	public String getTcedm() {
		return this.tcedm;
	}

	public void setTcedm(String tcedm) {
		this.tcedm = tcedm;
	}

	public String getTdoc() {
		return this.tdoc;
	}

	public void setTdoc(String tdoc) {
		this.tdoc = tdoc;
	}

	public String getTelefcel() {
		return this.telefcel;
	}

	public void setTelefcel(String telefcel) {
		this.telefcel = telefcel;
	}

	public String getTelf() {
		return this.telf;
	}

	public void setTelf(String telf) {
		this.telf = telf;
	}

	public String getTelf1() {
		return this.telf1;
	}

	public void setTelf1(String telf1) {
		this.telf1 = telf1;
	}

	public String getTipodiscap() {
		return this.tipodiscap;
	}

	public void setTipodiscap(String tipodiscap) {
		this.tipodiscap = tipodiscap;
	}

	public String getTipsan() {
		return this.tipsan;
	}

	public void setTipsan(String tipsan) {
		this.tipsan = tipsan;
	}

	public Date getUpfsisEmp() {
		return this.upfsisEmp;
	}

	public void setUpfsisEmp(Date upfsisEmp) {
		this.upfsisEmp = upfsisEmp;
	}

	public String getUpusrEmp() {
		return this.upusrEmp;
	}

	public void setUpusrEmp(String upusrEmp) {
		this.upusrEmp = upusrEmp;
	}

	public String getUsr() {
		return this.usr;
	}

	public void setUsr(String usr) {
		this.usr = usr;
	}

	public List<Articulo> getArticulo_1() {
	    return articulo_1;
	}

	public void setArticulo_1(List<Articulo> param) {
	    this.articulo_1 = param;
	}

}