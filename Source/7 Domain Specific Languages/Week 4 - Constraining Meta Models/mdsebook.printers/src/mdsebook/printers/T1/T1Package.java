/**
 */
package mdsebook.printers.T1;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see mdsebook.printers.T1.T1Factory
 * @model kind="package"
 * @generated
 */
public interface T1Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "T1";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.itu.dk/people/wasowski/mdsebook.printers.T1";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mdsebook.printers.T1";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T1Package eINSTANCE = mdsebook.printers.T1.impl.T1PackageImpl.init();

	/**
	 * The meta object id for the '{@link mdsebook.printers.T1.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T1.impl.PrinterPoolImpl
	 * @see mdsebook.printers.T1.impl.T1PackageImpl#getPrinterPool()
	 * @generated
	 */
	int PRINTER_POOL = 0;

	/**
	 * The feature id for the '<em><b>Printer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL__PRINTER = 0;

	/**
	 * The feature id for the '<em><b>Fax</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL__FAX = 1;

	/**
	 * The number of structural features of the '<em>Printer Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Printer Pool</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mdsebook.printers.T1.impl.FaxImpl <em>Fax</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T1.impl.FaxImpl
	 * @see mdsebook.printers.T1.impl.T1PackageImpl#getFax()
	 * @generated
	 */
	int FAX = 1;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAX__POOL = 0;

	/**
	 * The number of structural features of the '<em>Fax</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAX_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Fax</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FAX_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mdsebook.printers.T1.impl.PrinterImpl <em>Printer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T1.impl.PrinterImpl
	 * @see mdsebook.printers.T1.impl.T1PackageImpl#getPrinter()
	 * @generated
	 */
	int PRINTER = 2;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__POOL = 0;

	/**
	 * The number of structural features of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_FEATURE_COUNT = 1;

	/**
	 * The number of operations of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T1.PrinterPool <em>Printer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer Pool</em>'.
	 * @see mdsebook.printers.T1.PrinterPool
	 * @generated
	 */
	EClass getPrinterPool();

	/**
	 * Returns the meta object for the containment reference '{@link mdsebook.printers.T1.PrinterPool#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Printer</em>'.
	 * @see mdsebook.printers.T1.PrinterPool#getPrinter()
	 * @see #getPrinterPool()
	 * @generated
	 */
	EReference getPrinterPool_Printer();

	/**
	 * Returns the meta object for the containment reference '{@link mdsebook.printers.T1.PrinterPool#getFax <em>Fax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Fax</em>'.
	 * @see mdsebook.printers.T1.PrinterPool#getFax()
	 * @see #getPrinterPool()
	 * @generated
	 */
	EReference getPrinterPool_Fax();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T1.Fax <em>Fax</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Fax</em>'.
	 * @see mdsebook.printers.T1.Fax
	 * @generated
	 */
	EClass getFax();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T1.Fax#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pool</em>'.
	 * @see mdsebook.printers.T1.Fax#getPool()
	 * @see #getFax()
	 * @generated
	 */
	EReference getFax_Pool();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T1.Printer <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer</em>'.
	 * @see mdsebook.printers.T1.Printer
	 * @generated
	 */
	EClass getPrinter();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T1.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pool</em>'.
	 * @see mdsebook.printers.T1.Printer#getPool()
	 * @see #getPrinter()
	 * @generated
	 */
	EReference getPrinter_Pool();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	T1Factory getT1Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link mdsebook.printers.T1.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T1.impl.PrinterPoolImpl
		 * @see mdsebook.printers.T1.impl.T1PackageImpl#getPrinterPool()
		 * @generated
		 */
		EClass PRINTER_POOL = eINSTANCE.getPrinterPool();

		/**
		 * The meta object literal for the '<em><b>Printer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER_POOL__PRINTER = eINSTANCE.getPrinterPool_Printer();

		/**
		 * The meta object literal for the '<em><b>Fax</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER_POOL__FAX = eINSTANCE.getPrinterPool_Fax();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T1.impl.FaxImpl <em>Fax</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T1.impl.FaxImpl
		 * @see mdsebook.printers.T1.impl.T1PackageImpl#getFax()
		 * @generated
		 */
		EClass FAX = eINSTANCE.getFax();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FAX__POOL = eINSTANCE.getFax_Pool();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T1.impl.PrinterImpl <em>Printer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T1.impl.PrinterImpl
		 * @see mdsebook.printers.T1.impl.T1PackageImpl#getPrinter()
		 * @generated
		 */
		EClass PRINTER = eINSTANCE.getPrinter();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER__POOL = eINSTANCE.getPrinter_Pool();

	}

} //T1Package
