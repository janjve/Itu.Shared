/**
 */
package mdsebook.printers.T3;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T3.T3Package
 * @generated
 */
public interface T3Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T3Factory eINSTANCE = mdsebook.printers.T3.impl.T3FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Printer Pool</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Printer Pool</em>'.
	 * @generated
	 */
	PrinterPool createPrinterPool();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	T3Package getT3Package();

} //T3Factory
