package ec.edu.epn.rrhh.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the estemp database table.
 * 
 */
@Entity
@Table(name="estemp", catalog = "bddcorpepn", schema = "`Rrhh`")
public class Estemp implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
//	@SequenceGenerator(name="ESTEMP_CODEST_GENERATOR" )
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESTEMP_CODEST_GENERATOR")
	@Column(name="cod_est", unique=true, nullable=false, length=2)
	private String codEst;

	@Column(name="desc_est", length=20)
	private String descEst;

	//bi-directional many-to-one association to Emp
	@OneToMany(mappedBy="estemp")
	private List<Emp> emps;

    public Estemp() {
    }

	public String getCodEst() {
		return this.codEst;
	}

	public void setCodEst(String codEst) {
		this.codEst = codEst;
	}

	public String getDescEst() {
		return this.descEst;
	}

	public void setDescEst(String descEst) {
		this.descEst = descEst;
	}

	public List<Emp> getEmps() {
		return this.emps;
	}

	public void setEmps(List<Emp> emps) {
		this.emps = emps;
	}
	
}