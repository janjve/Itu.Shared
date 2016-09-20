/**
 */
package mdsebook.printers.T1;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Printer</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T1.Printer#getPool <em>Pool</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T1.T1Package#getPrinter()
 * @model
 * @generated
 */
public interface Printer extends EObject {
	/**
	 * Returns the value of the '<em><b>Pool</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T1.PrinterPool#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pool</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' container reference.
	 * @see #setPool(PrinterPool)
	 * @see mdsebook.printers.T1.T1Package#getPrinter_Pool()
	 * @see mdsebook.printers.T1.PrinterPool#getPrinter
	 * @model opposite="printer" required="true" transient="false"
	 * @generated
	 */
	PrinterPool getPool();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T1.Printer#getPool <em>Pool</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' container reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(PrinterPool value);

} // Printer
