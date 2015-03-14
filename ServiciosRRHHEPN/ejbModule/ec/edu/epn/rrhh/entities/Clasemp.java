package ec.edu.epn.rrhh.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the clasemp database table.
 * 
 */
@Entity
@Table(name="clasemp", catalog = "bddcorpepn", schema = "`Rrhh`")
public class Clasemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="CLASEMP_CODCLASE_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLASEMP_CODCLASE_GENERATOR")
	@Column(name="cod_clase", unique=true, nullable=false, length=2)
	private String codClase;

	@Column(name="desc_clase", nullable=false, length=40)
	private String descClase;

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="clasemp")
	private List<Emp> emps;

    public Clasemp() {
    }

	public String getCodClase() {
		return this.codClase;
	}

	public void setCodClase(String codClase) {
		this.codClase = codClase;
	}

	public String getDescClase() {
		return this.descClase;
	}

	public void setDescClase(String descClase) {
		this.descClase = descClase;
	}

	public List<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	
}