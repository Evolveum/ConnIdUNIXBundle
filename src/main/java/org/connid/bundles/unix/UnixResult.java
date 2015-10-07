package org.connid.bundles.unix;

import org.identityconnectors.common.logging.Log;
import org.identityconnectors.framework.common.exceptions.AlreadyExistsException;
import org.identityconnectors.framework.common.exceptions.ConfigurationException;
import org.identityconnectors.framework.common.exceptions.ConnectionBrokenException;
import org.identityconnectors.framework.common.exceptions.ConnectorException;
import org.identityconnectors.framework.common.exceptions.PermissionDeniedException;
import org.identityconnectors.framework.common.exceptions.UnknownUidException;

public class UnixResult {

	public enum Operation {
		USERADD, USERMOD, USERDEL, PASSWD, MV, GROUPADD, GROUPMOD, GROUPDEL, GETENET
	}

	private int exitStatus;
	private String errorMessage;
	private String output;

	public UnixResult(int exitStatus, String errorMessage, String output) {
		this.exitStatus = exitStatus;
		this.errorMessage = errorMessage;
		this.output = output;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public int getExitStatus() {
		return exitStatus;
	}

	public String getOutput() {
		return output;
	}

	public void checkResult(Operation operation, String message, Log log) {
		if (getExitStatus() == 0) {
			return;
		}
		String errorDescription = message + ": " + getErrorMessage();
		switch (getExitStatus()) {
		case 4:
			if (operation == Operation.PASSWD) {
				log.error(errorDescription);
				throw new ConnectorException(errorDescription);
			} else if (operation == Operation.GROUPMOD) {
				log.error(errorDescription);
				throw new UnknownUidException(errorDescription);
			}
			break;
		case 9:
			log.error(errorDescription);
			throw new AlreadyExistsException(errorDescription);
		case 2:
			if (operation == Operation.GETENET) {
				log.error(errorDescription);
				throw new UnknownUidException(errorDescription);
			}
			break;
		case 3:
			log.error(errorDescription);
			throw new ConfigurationException(errorDescription);
		case 1:
			switch (operation) {
			case PASSWD:
			case USERMOD:
				log.error(errorDescription);
				throw new PermissionDeniedException(errorDescription);
			case GETENET:
			case USERDEL:
				if (!errorDescription.contains("mail spool")) {
					log.error(errorDescription);
					throw new ConfigurationException(errorDescription);
				}

			}
			break;
		case 6:
			switch (operation) {
			case PASSWD:
				log.error(errorDescription);
				throw new ConfigurationException(errorDescription);
			case USERMOD:
			case GROUPMOD:
			case GROUPDEL:
			case USERDEL:
				log.error(errorDescription);
				throw new UnknownUidException(errorDescription);
			}
			break;
		case 8:
			log.error(errorDescription);
			throw new PermissionDeniedException(errorDescription); // USERDEL
		case 5:
			if (operation == Operation.PASSWD) {
				log.error(errorDescription);
				throw new ConnectionBrokenException(errorDescription); // PASSWD
			}
			break;
		case 10:
		case 12:
		case 14:
			log.error(errorDescription);
			throw new ConnectorException(errorDescription);
		}
	}

}