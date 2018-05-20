package com.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Log {
	protected Logger logger;

	public Log() {
		this.logger = LoggerFactory.getLogger(super.getClass());
	}
}
