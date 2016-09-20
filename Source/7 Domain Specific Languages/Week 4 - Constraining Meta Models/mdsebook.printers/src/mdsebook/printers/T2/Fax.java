/**
 */
package mdsebook.printers.T2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fax</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T2.Fax#getPool <em>Pool</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T2.T2Package#getFax()
 * @model
 * @generated
 */
public interface Fax extends EObject {
	/**
	 * Returns the value of the '<em><b>Pool</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T2.PrinterPool#getFax <em>Fax</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pool</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' container reference.
	 * @see #setPool(PrinterPool)
	 * @see mdsebook.printers.T2.T2Package#getFax_Pool()
	 * @see mdsebook.printers.T2.PrinterPool#getFax
	 * @model opposite="fax" required="true" transient="false" derived="true"
	 * @generated
	 */
	PrinterPool getPool();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T2.Fax#getPool <em>Pool</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' container reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(PrinterPool value);

} // Fax
