/**
 */
package mdsebook.printers.T6;

import org.eclipse.emf.ecore.EAttribute;
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
 * @see mdsebook.printers.T6.T6Factory
 * @model kind="package"
 * @generated
 */
public interface T6Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "T6";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.itu.dk/people/wasowski/mdsebook.printers.T6";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "mdsebook.printers.T6";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	T6Package eINSTANCE = mdsebook.printers.T6.impl.T6PackageImpl.init();

	/**
	 * The meta object id for the '{@link mdsebook.printers.T6.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T6.impl.PrinterPoolImpl
	 * @see mdsebook.printers.T6.impl.T6PackageImpl#getPrinterPool()
	 * @generated
	 */
	int PRINTER_POOL = 0;

	/**
	 * The feature id for the '<em><b>Printer</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL__PRINTER = 0;

	/**
	 * The feature id for the '<em><b>Scanner</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_POOL__SCANNER = 1;

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
	 * The meta object id for the '{@link mdsebook.printers.T6.impl.PrinterImpl <em>Printer</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T6.impl.PrinterImpl
	 * @see mdsebook.printers.T6.impl.T6PackageImpl#getPrinter()
	 * @generated
	 */
	int PRINTER = 1;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__POOL = 0;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER__COLOR = 1;

	/**
	 * The number of structural features of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Printer</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRINTER_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link mdsebook.printers.T6.impl.ScannerImpl <em>Scanner</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see mdsebook.printers.T6.impl.ScannerImpl
	 * @see mdsebook.printers.T6.impl.T6PackageImpl#getScanner()
	 * @generated
	 */
	int SCANNER = 2;

	/**
	 * The feature id for the '<em><b>Pool</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCANNER__POOL = 0;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCANNER__COLOR = 1;

	/**
	 * The number of structural features of the '<em>Scanner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCANNER_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Scanner</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCANNER_OPERATION_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T6.PrinterPool <em>Printer Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer Pool</em>'.
	 * @see mdsebook.printers.T6.PrinterPool
	 * @generated
	 */
	EClass getPrinterPool();

	/**
	 * Returns the meta object for the containment reference list '{@link mdsebook.printers.T6.PrinterPool#getPrinter <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Printer</em>'.
	 * @see mdsebook.printers.T6.PrinterPool#getPrinter()
	 * @see #getPrinterPool()
	 * @generated
	 */
	EReference getPrinterPool_Printer();

	/**
	 * Returns the meta object for the containment reference list '{@link mdsebook.printers.T6.PrinterPool#getScanner <em>Scanner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Scanner</em>'.
	 * @see mdsebook.printers.T6.PrinterPool#getScanner()
	 * @see #getPrinterPool()
	 * @generated
	 */
	EReference getPrinterPool_Scanner();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T6.Printer <em>Printer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Printer</em>'.
	 * @see mdsebook.printers.T6.Printer
	 * @generated
	 */
	EClass getPrinter();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T6.Printer#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pool</em>'.
	 * @see mdsebook.printers.T6.Printer#getPool()
	 * @see #getPrinter()
	 * @generated
	 */
	EReference getPrinter_Pool();

	/**
	 * Returns the meta object for the attribute '{@link mdsebook.printers.T6.Printer#isColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see mdsebook.printers.T6.Printer#isColor()
	 * @see #getPrinter()
	 * @generated
	 */
	EAttribute getPrinter_Color();

	/**
	 * Returns the meta object for class '{@link mdsebook.printers.T6.Scanner <em>Scanner</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Scanner</em>'.
	 * @see mdsebook.printers.T6.Scanner
	 * @generated
	 */
	EClass getScanner();

	/**
	 * Returns the meta object for the container reference '{@link mdsebook.printers.T6.Scanner#getPool <em>Pool</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Pool</em>'.
	 * @see mdsebook.printers.T6.Scanner#getPool()
	 * @see #getScanner()
	 * @generated
	 */
	EReference getScanner_Pool();

	/**
	 * Returns the meta object for the attribute '{@link mdsebook.printers.T6.Scanner#isColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see mdsebook.printers.T6.Scanner#isColor()
	 * @see #getScanner()
	 * @generated
	 */
	EAttribute getScanner_Color();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	T6Factory getT6Factory();

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
		 * The meta object literal for the '{@link mdsebook.printers.T6.impl.PrinterPoolImpl <em>Printer Pool</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T6.impl.PrinterPoolImpl
		 * @see mdsebook.printers.T6.impl.T6PackageImpl#getPrinterPool()
		 * @generated
		 */
		EClass PRINTER_POOL = eINSTANCE.getPrinterPool();

		/**
		 * The meta object literal for the '<em><b>Printer</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER_POOL__PRINTER = eINSTANCE.getPrinterPool_Printer();

		/**
		 * The meta object literal for the '<em><b>Scanner</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PRINTER_POOL__SCANNER = eINSTANCE.getPrinterPool_Scanner();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T6.impl.PrinterImpl <em>Printer</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T6.impl.PrinterImpl
		 * @see mdsebook.printers.T6.impl.T6PackageImpl#getPrinter()
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

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PRINTER__COLOR = eINSTANCE.getPrinter_Color();

		/**
		 * The meta object literal for the '{@link mdsebook.printers.T6.impl.ScannerImpl <em>Scanner</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see mdsebook.printers.T6.impl.ScannerImpl
		 * @see mdsebook.printers.T6.impl.T6PackageImpl#getScanner()
		 * @generated
		 */
		EClass SCANNER = eINSTANCE.getScanner();

		/**
		 * The meta object literal for the '<em><b>Pool</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCANNER__POOL = eINSTANCE.getScanner_Pool();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SCANNER__COLOR = eINSTANCE.getScanner_Color();

	}

} //T6Package
