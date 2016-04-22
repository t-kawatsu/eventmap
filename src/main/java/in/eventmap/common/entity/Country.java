package in.eventmap.common.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "countries")
public class Country implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String code;
	private String name;

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

	// http://docs.jboss.org/hibernate/orm/4.1/manual/en-US/html_single/#persistent-classes-equalshashcode
	public boolean equals(Country other) {
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
