/**
 * 
 */
package org.javabuilders.handler;

import java.util.Set;

import org.javabuilders.BuildProcess;
import org.javabuilders.Builder;
import org.javabuilders.BuilderConfig;
import org.javabuilders.ITypeAsValueSupport;
import org.javabuilders.InvalidPropertyException;
import org.javabuilders.Node;
import org.javabuilders.util.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Default class for handling type properties
 * @author Jacek Furmankiewicz
 */
public class DefaultPropertyHandler extends AbstractPropertyHandler implements ITypeAsValueSupport {

	private static final Logger logger = LoggerFactory.getLogger(DefaultPropertyHandler.class);
	
	private static final DefaultPropertyHandler singleton = new DefaultPropertyHandler();
	
	/**
	 * @return Singleton
	 */
	public static DefaultPropertyHandler getInstance() {return singleton;}
	
	/**
	 * List of consumed keys
	 * @param consumedKeys
	 */
	private DefaultPropertyHandler(Set<String> consumedKeys) {
		super(consumedKeys);
	}
	
	/**
	 * Constructor
	 * @param consumedKeys List of consumed keys
	 */
	private DefaultPropertyHandler(String... consumedKeys) {
		super(consumedKeys);
	}

	/* (non-Javadoc)
	 * @see org.javabuilders.handler.IPropertyHandler#handle(org.javabuilders.BuilderConfig, org.javabuilders.BuildProcess, org.javabuilders.Node, java.lang.String)
	 */
	public void handle(BuilderConfig config, BuildProcess result, Node node,
			String key) throws InvalidPropertyException {
		
		if (key != Builder.CONTENT) {
			
			Object mainObject = node.getMainObject();
			Object value = node.getProperties().get(key);
		
			//ignore default 'name' property if object does not have it
			if (config.getNamePropertyName().equals(key) && !PropertyUtils.isValid(mainObject, config.getNamePropertyName())) {
				return;
			}
			
			try {
				PropertyUtils.setProperty(mainObject, key, value);
			} catch (Exception e) {
				
				//try to display more info on which properties are allowed
				StringBuilder builder = new StringBuilder();
				try {
					builder.append("The known property names for " + mainObject.getClass().getSimpleName() + " are:");
					Set<String> names = PropertyUtils.getPropertyNames(mainObject);
					for (String name : names) {
						if (builder.length() > 0) {
							builder.append("\n");
						}
						builder.append(name);
					}
				} catch (Exception ex) {
					logger.error(ex.getMessage(),ex);
				}
				
				logger.error(e.getMessage() + "\n" + builder.toString(),e);
				
				throw new InvalidPropertyException(e, node.getKey(),key, value, builder.toString());
			}
			
		}
	}

	/**
	 * Can handle any object
	 */
	public Class<?> getApplicableClass() {
		return Object.class;
	}
	
}
