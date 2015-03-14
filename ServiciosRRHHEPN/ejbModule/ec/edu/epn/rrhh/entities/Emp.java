package ec.edu.epn.rrhh.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the emp database table.
 * 
 */
@Entity
@Table(name="emp", catalog = "bddcorpepn", schema = "`Rrhh`")
public class Emp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="EMP_NCED_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="EMP_NCED_GENERATOR")
	@Column(unique=true, nullable=false, length=10)
	private String nced;

	@Column(nullable=false, length=60)
	private String apel;

	@Column(name="apel_con", length=60)
	private String apelCon;

	@Column(name="ciudad_nac", length=50)
	private String ciudadNac;

	@Column(name="`COD_DEDICACION`", length=2)
	private String codDedicacion;

	@Column(name="cod_dep", nullable=false, length=15)
	private String codDep;

	@Column(name="cod_depa", length=15)
	private String codDepa;

	@Column(name="cod_ecivil", nullable=false, length=2)
	private String codEcivil;

	@Column(name="cod_nac", nullable=false, length=2)
	private String codNac;

	@Column(name="cod_pindmin", length=50)
	private String codPindmin;

	@Column(name="cod_prov", length=2)
	private String codProv;

	@Column(name="cod_sexo", nullable=false, length=1)
	private String codSexo;

	@Column(name="cod_tiporelacionlab", length=2)
	private String codTiporelacionlab;

	@Column(length=4)
	private String codtim;

	@Column(length=100)
	private String direc;

	@Column(length=50)
	private String email1;

	@Column(length=50)
	private String email2;

	@Column(length=11)
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

	@Column(length=254)
	private String foto;

	@Column(length=100)
	private String funciontipica;

	@Column(length=25)
	private String lnac;

	@Column(name="ltrab_espec", length=100)
	private String ltrabEspec;

	@Column(length=5)
	private String lugtim;

	@Column(length=1)
	private String mbienes;

	@Column(length=14)
	private String nafil;

	private Integer ncargas;

	@Column(length=12)
	private String ncedm;

	@Column(nullable=false, length=60)
	private String nom;

	@Column(name="nom_con", length=60)
	private String nomCon;

	@Column(name="nom_jefe", length=50)
	private String nomJefe;

	@Column(length=50)
	private String nrocarnetconadis;

	@Column(name="ocup_con", length=30)
	private String ocupCon;

	@Column(length=2)
	private String ordjer;

	@Column(length=50)
	private String porcendiscap;

	@Column(length=20)
	private String tcedm;

	@Column(length=2)
	private String tdoc;

	@Column(length=9)
	private String telefcel;

	@Column(length=9)
	private String telf;

	@Column(length=9)
	private String telf1;

	@Column(length=50)
	private String tipodiscap;

	@Column(length=6)
	private String tipsan;

    @Temporal( TemporalType.DATE)
	@Column(name="upfsis_emp")
	private Date upfsisEmp;

	@Column(name="upusr_emp", length=30)
	private String upusrEmp;

	@Column(length=30)
	private String usr;

	//bi-directional many-to-one association to Clasemp
    @ManyToOne
	@JoinColumn(name="cod_clase", nullable=false)
	private Clasemp clasemp;

	//bi-directional many-to-one association to Estemp
    @ManyToOne
	@JoinColumn(name="cod_est", nullable=false)
	private Estemp estemp;

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

	public Clasemp getClasemp() {
		return this.clasemp;
	}

	public void setClasemp(Clasemp clasemp) {
		this.clasemp = clasemp;
	}
	
	public Estemp getEstemp() {
		return this.estemp;
	}

	public void setEstemp(Estemp estemp) {
		this.estemp = estemp;
	}
	
}