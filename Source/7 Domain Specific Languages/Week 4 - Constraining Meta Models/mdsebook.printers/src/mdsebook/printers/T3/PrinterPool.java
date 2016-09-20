/**
 */
package mdsebook.printers.T3;

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
 *   <li>{@link mdsebook.printers.T3.PrinterPool#getMinSpeed <em>Min Speed</em>}</li>
 *   <li>{@link mdsebook.printers.T3.PrinterPool#getSpeed <em>Speed</em>}</li>
 * </ul>
 *
 * @see mdsebook.printers.T3.T3Package#getPrinterPool()
 * @model
 * @generated
 */
public interface PrinterPool extends EObject {
	/**
	 * Returns the value of the '<em><b>Min Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Min Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Min Speed</em>' attribute.
	 * @see #setMinSpeed(int)
	 * @see mdsebook.printers.T3.T3Package#getPrinterPool_MinSpeed()
	 * @model
	 * @generated
	 */
	int getMinSpeed();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T3.PrinterPool#getMinSpeed <em>Min Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Min Speed</em>' attribute.
	 * @see #getMinSpeed()
	 * @generated
	 */
	void setMinSpeed(int value);

	/**
	 * Returns the value of the '<em><b>Speed</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Speed</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Speed</em>' attribute.
	 * @see #setSpeed(int)
	 * @see mdsebook.printers.T3.T3Package#getPrinterPool_Speed()
	 * @model
	 * @generated
	 */
	int getSpeed();

	/**
	 * Sets the value of the '{@link mdsebook.printers.T3.PrinterPool#getSpeed <em>Speed</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Speed</em>' attribute.
	 * @see #getSpeed()
	 * @generated
	 */
	void setSpeed(int value);

} // PrinterPool
