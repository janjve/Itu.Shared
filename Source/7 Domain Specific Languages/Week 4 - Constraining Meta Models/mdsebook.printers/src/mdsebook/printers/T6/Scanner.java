/**
 */
package mdsebook.printers.T6;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scanner</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T6.Scanner#getPool <em>Pool</em>}</li>
 *   <li>{@link mdsebook.printers.T6.Scanner#isColor <em>Color</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T6.T6Package#getScanner()
 * @model
 * @generated
 */
public interface Scanner extends EObject {
	/**
	 * Returns the value of the '<em><b>Pool</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T6.PrinterPool#getScanner <em>Scanner</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Pool</em>' container reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Pool</em>' container reference.
	 * @see #setPool(PrinterPool)
	 * @see mdsebook.printers.T6.T6Package#getScanner_Pool()
	 * @see mdsebook.printers.T6.PrinterPool#getScanner
	 * @model opposite="scanner" required="true" transient="false"
	 * @generated
	 */
	PrinterPool getPool();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T6.Scanner#getPool <em>Pool</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Pool</em>' container reference.
	 * @see #getPool()
	 * @generated
	 */
	void setPool(PrinterPool value);

	/**
	 * Returns the value of the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Color</em>' attribute.
	 * @see #setColor(boolean)
	 * @see mdsebook.printers.T6.T6Package#getScanner_Color()
	 * @model required="true"
	 * @generated
	 */
	boolean isColor();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T6.Scanner#isColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #isColor()
	 * @generated
	 */
	void setColor(boolean value);

} // Scanner
