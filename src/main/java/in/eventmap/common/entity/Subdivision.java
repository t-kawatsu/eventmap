package in.eventmap.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "subdivisions")
public class Subdivision implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;
	private String countyCode;

	@Id
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "country_code")
	public String getCountyCode() {
		return countyCode;
	}

	public void setCountyCode(String countyCode) {
		this.countyCode = countyCode;
	}

	// http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html_single/#persistent-classes-equalshashcode
	public boolean equals(Subdivision other) {
		if (this == other)
			return true;

		if (getCode() != other.getCode()) {
			return false;
		}

		if (getCode() == null) {
			return other.getCode() == null;
		} else {
			return getCode().equals(other.getCode());
		}
	}

	public int hashCode() {
		return getCode() == null ? 0 : getCode().hashCode();
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}
}
