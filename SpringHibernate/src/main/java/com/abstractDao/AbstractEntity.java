package com.abstractDao;

import java.io.Serializable;

import javax.persistence.Transient;

public abstract class AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Transient
	public abstract Integer getId();

}
