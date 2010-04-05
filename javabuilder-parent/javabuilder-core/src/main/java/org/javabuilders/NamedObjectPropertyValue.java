package org.javabuilders;

import org.javabuilders.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple class to keep track of properties whose value is a named object.
 * These need to be executed at the end, when all the named objects are created.
 * Typical example is JLabel.setLabelFor(Component)
 * @author Jacek Furmankiewicz
 *
 */
public class NamedObjectPropertyValue {

	private static final Logger logger = LoggerFactory.getLogger(NamedObjectPropertyValue.class);
	
	private Object source = null;
	private String propertyName = null;
	private String targetObjectName = null;
	
	/**
	 * @param source  Source
	 * @param propertyName Property on the source that needs to be set
	 * @param targetObjectName Named object
	 */
	public NamedObjectPropertyValue(Object source, String propertyName, String targetObjectName) {
		this.source = source;
		this.propertyName = propertyName;
		this.targetObjectName = targetObjectName;
	}
	
	/**
	 * Sets the actual reference
	 * @param result Build result
	 * @throws BuildException
	 */
	void setReference(BuildProcess result) throws BuildException {
	
		Object namedObject = result.getByName(targetObjectName);
		if (namedObject == null) {
			throw new BuildException(targetObjectName + " is not a valid named object");
		}
		
		try {
			PropertyUtils.setProperty(source, propertyName, namedObject);
		} catch (Exception e) {
			throw new BuildException(e,"Unable to set property {0}.{1} to named object \"{2}\"",
					source.getClass().getSimpleName(),propertyName,targetObjectName);
		} 
		
		if (logger.isDebugEnabled()) {
			logger.debug("Set reference on property %s to %s", propertyName, targetObjectName);
		}
		
	}
	
}
