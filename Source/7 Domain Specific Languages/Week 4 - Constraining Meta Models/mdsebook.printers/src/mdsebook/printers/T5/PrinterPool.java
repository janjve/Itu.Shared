/**
 */
package mdsebook.printers.T5;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Printer Pool</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link mdsebook.printers.T5.PrinterPool#getPrinter <em>Printer</em>}</li>
 *   <li>{@link mdsebook.printers.T5.PrinterPool#isColor <em>Color</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T5.T5Package#getPrinterPool()
 * @model
 * @generated
 */
public interface PrinterPool extends EObject {
	/**
	 * Returns the value of the '<em><b>Printer</b></em>' containment reference list.
	 * The list contents are of type {@link mdsebook.printers.T5.Printer}.
	 * It is bidirectional and its opposite is '{@link mdsebook.printers.T5.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Printer</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Printer</em>' containment reference list.
	 * @see mdsebook.printers.T5.T5Package#getPrinterPool_Printer()
	 * @see mdsebook.printers.T5.Printer#getPool
	 * @model opposite="pool" containment="true" required="true"
	 * @generated
	 */
	EList<Printer> getPrinter();

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
	 * @see mdsebook.printers.T5.T5Package#getPrinterPool_Color()
	 * @model required="true"
	 * @generated
	 */
	boolean isColor();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T5.PrinterPool#isColor <em>Color</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Color</em>' attribute.
	 * @see #isColor()
	 * @generated
	 */
	void setColor(boolean value);

} // PrinterPool
