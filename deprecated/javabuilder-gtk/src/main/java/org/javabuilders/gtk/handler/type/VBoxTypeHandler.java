/**
 * 
 */
package org.javabuilders.gtk.handler.type;

import static org.javabuilders.gtk.GtkConstants.*;

import java.util.Map;

import org.gnome.gtk.VBox;
import org.javabuilders.BuildException;
import org.javabuilders.BuildProcess;
import org.javabuilders.BuilderConfig;
import org.javabuilders.Node;
import org.javabuilders.gtk.GtkConstants;
import org.javabuilders.handler.AbstractTypeHandler;

/**
 * VBox type handler
 * @author Jacek Furmankiewicz
 */
public class VBoxTypeHandler extends AbstractTypeHandler {

	/**
	 * Constructor
	 */
	public VBoxTypeHandler() {
		super(HOMOGENOUS, SPACING);
	}
	
	/* (non-Javadoc)
	 * @see org.javabuilders.handler.ITypeHandler#createNewInstance(org.javabuilders.BuilderConfig, org.javabuilders.BuildProcess, org.javabuilders.Node, java.lang.String, java.util.Map)
	 */
	public Node createNewInstance(BuilderConfig config, BuildProcess process,
			Node parent, String key, Map<String, Object> typeDefinition)
			throws BuildException {

		Boolean homogenous = (Boolean) typeDefinition.get(GtkConstants.HOMOGENOUS);
		if (homogenous == null) {
			homogenous = Boolean.FALSE;
		}
		Integer spacing = (Integer) typeDefinition.get(GtkConstants.SPACING);
		if (spacing == null) {
			spacing = 4;
		}
		VBox instance = new VBox(homogenous,spacing);
		return useExistingInstance(config, process, parent, key, typeDefinition, instance);
	}

	/* (non-Javadoc)
	 * @see org.javabuilders.handler.ITypeHandler#useExistingInstance(org.javabuilders.BuilderConfig, org.javabuilders.BuildProcess, org.javabuilders.Node, java.lang.String, java.util.Map, java.lang.Object)
	 */
	public Node useExistingInstance(BuilderConfig config, BuildProcess process,
			Node parent, String key, Map<String, Object> typeDefinition,
			Object instance) throws BuildException {
		
		Node node = new Node(parent, key, typeDefinition);
		node.setMainObject(instance);
		
		return node;
	}

	/* (non-Javadoc)
	 * @see org.javabuilders.IApplicable#getApplicableClass()
	 */
	public Class<VBox> getApplicableClass() {
		return VBox.class;
	}

}
