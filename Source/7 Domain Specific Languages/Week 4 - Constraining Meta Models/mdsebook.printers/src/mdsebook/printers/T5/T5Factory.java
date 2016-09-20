/**
 */
package mdsebook.printers.T5;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T5.T5Package
 * @generated
 */
public interface T5Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T5Factory eINSTANCE = mdsebook.printers.T5.impl.T5FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Printer Pool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer Pool</em>'.
	 * @generated
	 */
	PrinterPool createPrinterPool();

	/**
	 * Returns a new object of class '<em>Printer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer</em>'.
	 * @generated
	 */
	Printer createPrinter();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	T5Package getT5Package();

} //T5Factory
